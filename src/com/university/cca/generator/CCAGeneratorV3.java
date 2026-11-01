package com.university.cca.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.CCAConstants;
import com.university.cca.entities.Ambient;
import com.university.cca.entities.Message;
import com.university.cca.enums.CommunicationType;
import com.university.cca.exceptions.AmbientMessageNotFoundException;
import com.university.cca.files.cca.AmbientCCAWriter;
import com.university.cca.files.csv.AmbientCSVReader;
import com.university.cca.repositories.AmbientRepository;

/**
 * Version 3 of the CCA file generator.
 *
 * Improvements over V1/V2:
 * - Supports parent-to-child communication (n#send / n#recv)
 * - Supports child-to-parent communication (@send / @recv)
 * - Supports message forwarding via passMessageTo (A → B → C → B → A)
 * - Auto-detects communication type from the ambient hierarchy
 * - Generates nested ambient blocks (child ambients inside parent ambients)
 * - Continues to support sibling communication (n::send / n::recv)
 *
 * @author Konstantin Rusev
 * @version 3.0
 */
public class CCAGeneratorV3 {

	private static final Logger logger = LoggerFactory.getLogger(CCAGeneratorV3.class);

	private static final String NEWLINE = "\n";
	private static final String TAB = "\t";

	private CCAGeneratorV3() {
		// Prevent instantiation
	}

	// =========================================================================
	// ForwardingChain — groups the 4 messages of a forwarding relay
	// =========================================================================

	/**
	 * Represents a message forwarding chain: A → B → C → B → A.
	 *
	 * B is the relay ambient that receives a request from A, forwards it to C,
	 * receives the response from C, and forwards the response back to A.
	 *
	 * In ccaPL, this generates a 4-step sequential process at B:
	 * <pre>
	 * recv_from_A(msg).send_to_C(fwd).recv_from_C(resp_to,resp).send_to_A(orig,resp).0
	 * </pre>
	 */
	static class ForwardingChain {
		final Message originalRequest;     // msg1: A → B (original request, respondTo=null)
		final Message forwardToTarget;     // msg2: B → C (forward, passMsgTo=A)
		final Message responseFromTarget;  // msg3: C → B (response, respondTo=msg2.message)
		final Message forwardBackToOrigin; // msg4: B → A (forward response back, respondTo=msg1.message)

		ForwardingChain(Message originalRequest, Message forwardToTarget,
						Message responseFromTarget, Message forwardBackToOrigin) {
			this.originalRequest = originalRequest;
			this.forwardToTarget = forwardToTarget;
			this.responseFromTarget = responseFromTarget;
			this.forwardBackToOrigin = forwardBackToOrigin;
		}
	}

	// =========================================================================
	// Main generation entry point
	// =========================================================================

	/**
	 * Generates a ccaPL file supporting sibling, parent-to-child, child-to-parent
	 * communication and message forwarding (passMessageTo).
	 *
	 * @return CCA_STATUS_SUCCESS (0) on success, CCA_STATUS_FAILURE (1) on error
	 */
	public static int generate() {
		try {
			List<Ambient> allAmbients = AmbientCSVReader.getAllAmbients();
			List<Message> allMessages = AmbientCSVReader.getAllMessages();
			List<Message> activeMessages = GeneratorUtil.getMessagesActiveAmbients(allMessages, allAmbients);

			if (activeMessages.isEmpty()) {
				logger.warn("CCA Generator V3: No active messages found. Nothing to generate.");
				return CCAConstants.CCA_STATUS_FAILURE;
			}

			// Phase 1: Detect which children must be nested inside their parents
			Set<String> nestedChildNames = findNestedChildren(activeMessages, allAmbients);

			// Phase 2: Build map of parent -> nested children
			Map<String, List<String>> parentToChildren = buildParentToChildrenMap(nestedChildNames, allAmbients);

			// Phase 3: Determine top-level ambients
			List<String> topLevelAmbients = findTopLevelAmbients(activeMessages, nestedChildNames);

			// Phase 4: Generate ccaPL code for each top-level ambient
			List<String> ambientBlocks = new ArrayList<>();
			for (String ambientName : topLevelAmbients) {
				String block = generateAmbientBlock(ambientName, activeMessages, parentToChildren, allAmbients, 0);
				if (block != null && !block.trim().isEmpty()) {
					ambientBlocks.add(block);
				}
			}

			if (ambientBlocks.isEmpty()) {
				logger.warn("CCA Generator V3: No ambient blocks generated.");
				return CCAConstants.CCA_STATUS_FAILURE;
			}

			String ccaScenario = String.join(NEWLINE + "|" + NEWLINE, ambientBlocks);

			logger.info("CCA Generator V3: Generation result:\n{}", ccaScenario);
			AmbientCCAWriter.write(ccaScenario);

			return CCAConstants.CCA_STATUS_SUCCESS;

		} catch (Exception ex) {
			logger.error("CCA Generator V3: Unexpected error during generation: {}", ex.getMessage(), ex);
			return CCAConstants.CCA_STATUS_FAILURE;
		}
	}

	// =========================================================================
	// Hierarchy analysis
	// =========================================================================

	/**
	 * Finds all child ambients that must be nested inside their parent's block
	 * because there is a parent-child message between them.
	 */
	static Set<String> findNestedChildren(List<Message> activeMessages, List<Ambient> allAmbients) {
		Set<String> nestedChildren = new HashSet<>();

		for (Message msg : activeMessages) {
			CommunicationType type = GeneratorUtil.detectCommunicationType(
				msg.getSenderAmbient(), msg.getRecipientAmbient(), allAmbients
			);

			if (type == CommunicationType.PARENT_TO_CHILD) {
				nestedChildren.add(msg.getRecipientAmbient());
			} else if (type == CommunicationType.CHILD_TO_PARENT) {
				nestedChildren.add(msg.getSenderAmbient());
			}
		}

		logger.info("CCA Generator V3: Nested children detected: {}", nestedChildren);
		return nestedChildren;
	}

	/**
	 * Builds a map from parent ambient name to its list of nested child names.
	 */
	static Map<String, List<String>> buildParentToChildrenMap(Set<String> nestedChildNames,
															  List<Ambient> allAmbients) {
		Map<String, List<String>> parentToChildren = new LinkedHashMap<>();

		for (String childName : nestedChildNames) {
			String parentName = AmbientRepository.findParentName(allAmbients, childName);

			if (parentName != null) {
				parentToChildren
					.computeIfAbsent(parentName, k -> new ArrayList<>())
					.add(childName);
			} else {
				logger.warn("CCA Generator V3: Nested child '{}' has no parent. Skipping nesting.", childName);
			}
		}

		return parentToChildren;
	}

	/**
	 * Determines the top-level ambients: those that participate in messages
	 * and are NOT nested inside another ambient's block.
	 */
	static List<String> findTopLevelAmbients(List<Message> activeMessages, Set<String> nestedChildNames) {
		Set<String> participatingAmbients = new HashSet<>();

		for (Message msg : activeMessages) {
			participatingAmbients.add(msg.getSenderAmbient());
			participatingAmbients.add(msg.getRecipientAmbient());
		}

		return participatingAmbients.stream()
			.filter(name -> !nestedChildNames.contains(name))
			.sorted()
			.collect(Collectors.toList());
	}

	// =========================================================================
	// Ambient block generation (recursive, handles nesting)
	// =========================================================================

	/**
	 * Generates the complete ccaPL block for one ambient, including nested children.
	 */
	static String generateAmbientBlock(String ambientName, List<Message> allMessages,
										Map<String, List<String>> parentToChildren,
										List<Ambient> allAmbients, int depth)
			throws AmbientMessageNotFoundException {

		String indent = repeatTab(depth + 1);
		String baseIndent = repeatTab(depth);
		String sanitizedName = GeneratorUtil.replaceUnsupportedChars(ambientName);

		// Phase A: Generate this ambient's own capability lines
		List<String> capLines = generateCapabilityLines(ambientName, allMessages, allAmbients);

		// Phase B: Generate nested child blocks (fully formatted with absolute indentation)
		List<String> childBlockStrings = new ArrayList<>();
		List<String> children = parentToChildren.getOrDefault(ambientName, new ArrayList<>());
		for (String childName : children) {
			String childBlock = generateAmbientBlock(childName, allMessages, parentToChildren, allAmbients, depth + 1);
			if (childBlock != null && !childBlock.trim().isEmpty()) {
				childBlockStrings.add(childBlock);
			}
		}

		int totalItems = capLines.size() + childBlockStrings.size();
		if (totalItems == 0) {
			return null;
		}

		// Phase C: Build the ambient block
		StringBuilder sb = new StringBuilder();
		sb.append(sanitizedName).append("[").append(NEWLINE);

		int itemIndex = 0;

		for (String capItem : capLines) {
			String[] visualLines = capItem.split(NEWLINE);
			for (int j = 0; j < visualLines.length; j++) {
				sb.append(indent).append(visualLines[j]);
				if (j < visualLines.length - 1) {
					sb.append(NEWLINE);
				}
			}
			itemIndex++;
			if (itemIndex < totalItems) {
				sb.append("|");
			}
			sb.append(NEWLINE);
		}

		for (String childBlock : childBlockStrings) {
			sb.append(indent).append(childBlock);
			itemIndex++;
			if (itemIndex < totalItems) {
				sb.append("|");
			}
			sb.append(NEWLINE);
		}

		sb.append(baseIndent).append("]");

		return sb.toString();
	}

	// =========================================================================
	// Capability generation (per ambient)
	// =========================================================================

	/**
	 * Generates all ccaPL capability items for a single ambient, including
	 * forwarding relay chains.
	 *
	 * Processing order:
	 * 1. Detect forwarding chains where this ambient is the relay
	 * 2. Generate relay items for each chain
	 * 3. Process remaining messages as normal initiator/responder
	 *    (skipping messages already handled by a forwarding chain)
	 */
	static List<String> generateCapabilityLines(String ambientName, List<Message> allMessages,
												 List<Ambient> allAmbients)
			throws AmbientMessageNotFoundException {

		List<Message> ambientMessages = GeneratorUtil.getMessagesByAmbient(ambientName, allMessages);
		List<String> items = new ArrayList<>();

		// Step 1: Find forwarding chains where this ambient is the relay (B)
		List<ForwardingChain> chains = findForwardingChains(ambientName, allMessages);

		// Step 2: Collect all messages that are part of a forwarding chain at this ambient
		Set<Message> chainMessages = new HashSet<>();
		for (ForwardingChain chain : chains) {
			chainMessages.add(chain.forwardToTarget);     // msg2: B is sender
			chainMessages.add(chain.forwardBackToOrigin);  // msg4: B is sender
		}

		// Step 3: Generate relay items for forwarding chains
		for (ForwardingChain chain : chains) {
			String relayItem = generateRelayItem(ambientName, chain, allAmbients);
			if (relayItem != null) {
				items.add(relayItem);
			}
		}

		// Step 4: Process remaining messages normally (skip chain messages)
		for (Message message : ambientMessages) {
			if (chainMessages.contains(message)) {
				continue;
			}

			CommunicationType senderToRecipientType = GeneratorUtil.detectCommunicationType(
				message.getSenderAmbient(), message.getRecipientAmbient(), allAmbients
			);

			if (senderToRecipientType == CommunicationType.UNSUPPORTED) {
				logger.warn("CCA Generator V3: Unsupported relationship between '{}' and '{}'. Skipping.",
					message.getSenderAmbient(), message.getRecipientAmbient());
				continue;
			}

			if (GeneratorUtil.isInitiator(ambientName, message)) {
				String[] initiatorLines = generateInitiatorLines(
					ambientName, message, allMessages, senderToRecipientType, allAmbients
				);
				if (initiatorLines != null) {
					items.add(initiatorLines[0]);
					items.add(initiatorLines[1]);
				}

			} else if (GeneratorUtil.isResponder(ambientName, message)) {
				String responderItem = generateResponderItem(
					ambientName, message, senderToRecipientType
				);
				if (responderItem != null) {
					items.add(responderItem);
				}
			}
		}

		return items;
	}

	// =========================================================================
	// Forwarding chain detection
	// =========================================================================

	/**
	 * Finds all forwarding chains where the given ambient acts as the relay (B).
	 *
	 * A forwarding chain is identified by a message with passMsgTo != null.
	 * That message is msg2 (B → C, passMsgTo=A). From it, we trace:
	 * - msg3: response from C → B (via findMessageRecipient)
	 * - msg4: forward-back from B → A (matching msg3.message)
	 * - msg1: original request from A → B (matching msg4.respondTo)
	 *
	 * @param relayAmbient the ambient that acts as the relay (B)
	 * @param allMessages  all active messages
	 * @return list of complete forwarding chains
	 */
	static List<ForwardingChain> findForwardingChains(String relayAmbient, List<Message> allMessages) {
		List<ForwardingChain> chains = new ArrayList<>();

		for (Message msg : allMessages) {
			if (!GeneratorUtil.isForwarder(relayAmbient, msg)) {
				continue;
			}

			// msg2: B → C, passMsgTo = A
			Message forwardMsg = msg;
			String originalSender = forwardMsg.getPassMessageTo();

			try {
				// Find msg3: C → B, respondTo = msg2.message
				Message responseFromTarget = GeneratorUtil.findMessageRecipient(allMessages, forwardMsg);

				// Find msg4: B → A, where msg4.message = msg3.message and msg4.respondTo != null
				Message forwardBack = findForwardBack(
					allMessages, relayAmbient, originalSender, responseFromTarget.getMessage()
				);

				if (forwardBack == null) {
					logger.warn("CCA Generator V3: Forward-back message not found for relay '{}' to '{}'.",
						relayAmbient, originalSender);
					continue;
				}

				// Find msg1: A → B, where msg1.message = msg4.respondTo
				Message originalRequest = findOriginalRequest(
					allMessages, originalSender, relayAmbient, forwardBack.getRespondToMessage()
				);

				if (originalRequest == null) {
					logger.warn("CCA Generator V3: Original request not found from '{}' to '{}'.",
						originalSender, relayAmbient);
					continue;
				}

				chains.add(new ForwardingChain(originalRequest, forwardMsg, responseFromTarget, forwardBack));
				logger.info("CCA Generator V3: Forwarding chain detected: {} → {} → {} → {} → {}",
					originalSender, relayAmbient, forwardMsg.getRecipientAmbient(), relayAmbient, originalSender);

			} catch (AmbientMessageNotFoundException ex) {
				logger.warn("CCA Generator V3: Incomplete forwarding chain at relay '{}': {}",
					relayAmbient, ex.getMessage());
			}
		}

		return chains;
	}

	/**
	 * Finds the forward-back message: B → A, where the response message matches.
	 *
	 * @param allMessages     all messages
	 * @param relay           the relay ambient (B)
	 * @param originalSender  the original sender (A)
	 * @param responseMessage the message content from C's response (msg3.message)
	 * @return the forward-back message (msg4), or null if not found
	 */
	static Message findForwardBack(List<Message> allMessages, String relay,
									String originalSender, String responseMessage) {
		return allMessages.stream()
			.filter(m -> m.getSenderAmbient().equals(relay))
			.filter(m -> m.getRecipientAmbient().equals(originalSender))
			.filter(m -> !m.getRespondToMessage().equalsIgnoreCase(CCAConstants.MSG_RESPOND_TO_NULL))
			.filter(m -> m.getMessage().equals(responseMessage))
			.findFirst()
			.orElse(null);
	}

	/**
	 * Finds the original request message: A → B, where msg.message matches.
	 *
	 * @param allMessages    all messages
	 * @param originalSender the original sender (A)
	 * @param relay          the relay ambient (B)
	 * @param messageContent the expected message content (msg4.respondTo)
	 * @return the original request message (msg1), or null if not found
	 */
	static Message findOriginalRequest(List<Message> allMessages, String originalSender,
										String relay, String messageContent) {
		return allMessages.stream()
			.filter(m -> m.getSenderAmbient().equals(originalSender))
			.filter(m -> m.getRecipientAmbient().equals(relay))
			.filter(m -> m.getRespondToMessage().equalsIgnoreCase(CCAConstants.MSG_RESPOND_TO_NULL))
			.filter(m -> m.getMessage().equals(messageContent))
			.findFirst()
			.orElse(null);
	}

	// =========================================================================
	// Relay capability generation
	// =========================================================================

	/**
	 * Generates the 4-step sequential relay capability for a forwarding chain.
	 *
	 * Pattern: recv_from_A . send_to_C . recv_from_C . send_to_A . 0
	 *
	 * Each leg uses its own location specifier based on the hierarchy relationship:
	 * - A ↔ B: may be sibling (::), parent-child (#), or child-parent (@)
	 * - B ↔ C: may be a different relationship than A ↔ B
	 *
	 * Example output:
	 * <pre>
	 * STUDENT_PA::recv(FMI_rooms).
	 * FMI#send(FMI_rooms_from_uni_to_fmi).
	 * FMI#recv(FMI_rooms_from_uni_to_fmi,fmi_to_uni_543).
	 * STUDENT_PA::send(FMI_rooms,fmi_to_uni_543).0
	 * </pre>
	 */
	static String generateRelayItem(String relayAmbient, ForwardingChain chain,
									  List<Ambient> allAmbients) {

		String originalSender = chain.originalRequest.getSenderAmbient();
		String target = chain.forwardToTarget.getRecipientAmbient();

		CommunicationType typeToOriginal = GeneratorUtil.detectCommunicationType(
			relayAmbient, originalSender, allAmbients
		);
		CommunicationType typeToTarget = GeneratorUtil.detectCommunicationType(
			relayAmbient, target, allAmbients
		);

		if (typeToOriginal == CommunicationType.UNSUPPORTED || typeToTarget == CommunicationType.UNSUPPORTED) {
			logger.warn("CCA Generator V3: Unsupported relationship in forwarding chain at '{}'. Skipping.",
				relayAmbient);
			return null;
		}

		String sanitizedOriginal = GeneratorUtil.replaceUnsupportedChars(originalSender);
		String sanitizedTarget = GeneratorUtil.replaceUnsupportedChars(target);

		Message convMsg1 = GeneratorUtil.replaceUnsupportedChars(chain.originalRequest);
		Message convMsg2 = GeneratorUtil.replaceUnsupportedChars(chain.forwardToTarget);
		Message convMsg3 = GeneratorUtil.replaceUnsupportedChars(chain.responseFromTarget);
		Message convMsg4 = GeneratorUtil.replaceUnsupportedChars(chain.forwardBackToOrigin);

		// Step 1: recv from A — receive the original request
		String step1 = CCACapabilityBuilder.buildRecv(typeToOriginal, sanitizedOriginal, convMsg1.getMessage());

		// Step 2: send to C — forward the request
		String step2 = CCACapabilityBuilder.buildSend(typeToTarget, sanitizedTarget, convMsg2.getMessage());

		// Step 3: recv from C — receive the response
		String recvFromTargetParams = GeneratorUtil.toCSVFormat(
			convMsg3.getRespondToMessage(), convMsg3.getMessage()
		);
		String step3 = CCACapabilityBuilder.buildRecv(typeToTarget, sanitizedTarget, recvFromTargetParams);

		// Step 4: send to A — forward the response back
		String sendBackParams = GeneratorUtil.toCSVFormat(
			convMsg4.getRespondToMessage(), convMsg4.getMessage()
		);
		String step4 = CCACapabilityBuilder.buildSend(typeToOriginal, sanitizedOriginal, sendBackParams);

		return step1 + "." + NEWLINE
			+ step2 + "." + NEWLINE
			+ step3 + "." + NEWLINE
			+ step4 + ".0";
	}

	// =========================================================================
	// Initiator / Responder capability generation
	// =========================================================================

	/**
	 * Generates the two parallel capability lines for an initiator ambient.
	 */
	private static String[] generateInitiatorLines(String ambientName, Message message,
													List<Message> allMessages,
													CommunicationType senderToRecipientType,
													List<Ambient> allAmbients)
			throws AmbientMessageNotFoundException {

		CommunicationType myType = resolveMyType(ambientName, message, senderToRecipientType);
		String otherAmbient = resolveOtherAmbient(ambientName, message);

		String sanitizedOther = GeneratorUtil.replaceUnsupportedChars(otherAmbient);
		Message convertedMsg = GeneratorUtil.replaceUnsupportedChars(message);

		String sendCap = CCACapabilityBuilder.buildSend(myType, sanitizedOther, convertedMsg.getMessage());
		String sendLine = sendCap + ".0";

		Message recipientMsg = GeneratorUtil.findMessageRecipient(allMessages, message);
		String recvParams = GeneratorUtil.toCSVFormat(
			GeneratorUtil.replaceUnsupportedChars(recipientMsg.getRespondToMessage()),
			GeneratorUtil.replaceUnsupportedChars(recipientMsg.getMessage())
		);
		String recvCap = CCACapabilityBuilder.buildRecv(myType, sanitizedOther, recvParams);
		String recvLine = recvCap + ".0";

		return new String[] { sendLine, recvLine };
	}

	/**
	 * Generates a single sequential capability item for a responder ambient.
	 */
	private static String generateResponderItem(String ambientName, Message message,
												 CommunicationType senderToRecipientType) {

		CommunicationType myType = resolveMyType(ambientName, message, senderToRecipientType);
		String otherAmbient = resolveOtherAmbient(ambientName, message);

		String sanitizedOther = GeneratorUtil.replaceUnsupportedChars(otherAmbient);
		Message convertedMsg = GeneratorUtil.replaceUnsupportedChars(message);

		String recvParams = convertedMsg.getRespondToMessage();
		String recvCap = CCACapabilityBuilder.buildRecv(myType, sanitizedOther, recvParams);

		String sendParams = GeneratorUtil.toCSVFormat(
			convertedMsg.getRespondToMessage(),
			convertedMsg.getMessage()
		);
		String sendCap = CCACapabilityBuilder.buildSend(myType, sanitizedOther, sendParams);

		return recvCap + "." + NEWLINE + sendCap + ".0";
	}

	// =========================================================================
	// Helpers
	// =========================================================================

	private static CommunicationType resolveMyType(String ambientName, Message message,
													CommunicationType senderToRecipientType) {
		if (ambientName.equals(message.getSenderAmbient())) {
			return senderToRecipientType;
		}
		return senderToRecipientType.inverse();
	}

	private static String resolveOtherAmbient(String ambientName, Message message) {
		if (ambientName.equals(message.getSenderAmbient())) {
			return message.getRecipientAmbient();
		}
		return message.getSenderAmbient();
	}

	private static String repeatTab(int count) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append(TAB);
		}
		return sb.toString();
	}
}

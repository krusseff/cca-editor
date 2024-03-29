package com.university.cca.generator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.university.cca.constants.CCAConstants;
import com.university.cca.entities.Message;
import com.university.cca.exceptions.AmbientMessageNotFoundException;
import com.university.cca.repositories.AmbientRepository;

/**
 * Utility class related to the generation of the CCA file
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class GeneratorUtil {
	
	private static final String CSV_DELIMITER = ",";
	private static final String AMBIENT_WORD_DELIMITER 	  = "_";
	private static final String ALPHA_NUMERIC_CHARS_REGEX = "[^A-Za-z0-9]";

	private GeneratorUtil() {
		// Prevent creating an object of type GeneratorUtil class
	}

	/**
	 * Returns a list with messages that are sent or received from/to active ambient.
	 * The parent of the sender and recipient should be active as well.
	 * If it's not - the ambient message will NOT be included at the result set.
	 */
	protected static List<Message> getMessagesActiveAmbients(List<Message> messages) {
		List<String> activeAmbients = Arrays.asList(AmbientRepository.getActiveAmbientNamesSorted());
		
		return messages.stream()
			.filter(msg -> areActiveAmbients(activeAmbients, msg))
			.collect(Collectors.toList());
	}
	
	/**
	 * Returns messages by given ambient name
	 * Messages where the ambient name is sender or recipient
	 */
	protected static List<Message> getMessagesByAmbient(String ambient, List<Message> messages) {
		Predicate<Message> senderPredicate = msg -> ambient.equals(msg.getSenderAmbient());
		Predicate<Message> recipientPredicate = msg -> ambient.equals(msg.getRecipientAmbient());
		
		return messages
			.stream()
			.filter(senderPredicate.or(recipientPredicate))
			.collect(Collectors.toList());
	}
	
	/**
	 * Checks whether an ambient is sender or recipient of at least one message.
	 * 
	 * @return <code>true</code>  - if the ambient has messages.
	 * 		   <code>false</code> - if the ambient does not have messages.
	 */
	protected static boolean hasMessages(List<Message> messages, String ambient) {
		List<Message> ambientMessages = getMessagesByAmbient(ambient, messages);
		
		return !ambientMessages.isEmpty();
	}
	
	/**
	 * Finds recipient ambient message from the file with all available messages
	 */
	protected static Message findMessageRecipient(List<Message> ambientMessages,
									 			  Message message) throws AmbientMessageNotFoundException {
		String errorMsg = "Ambient message not found! Message: " + message.toString();
		
		return ambientMessages
			.stream()
			.filter(msg ->
				msg.getSenderAmbient().equals(message.getRecipientAmbient()) &&
				msg.getRecipientAmbient().equals(message.getSenderAmbient()) &&
				msg.getRespondToMessage().equals(message.getMessage())
			)
			.findFirst()
			.orElseThrow(() -> new AmbientMessageNotFoundException(errorMsg));
	}
	
	/**
	 * Converts two string values in one in CSV format.
	 */
	protected static String toCSVFormat(String firstValue,
										String secondValue) {
		
		StringBuilder stringBuilder = new StringBuilder();  
		stringBuilder.append(firstValue);
		stringBuilder.append(CSV_DELIMITER); 
		stringBuilder.append(secondValue); 
		
		return stringBuilder.toString();
	}
	
	/**
	 * Replaces all non-alphanumeric characters with underscore from the message object.
	 */
	protected static Message replaceUnsupportedChars(Message message) {
		return new Message(
			replaceUnsupportedChars(message.getSenderAmbient()), 
			replaceUnsupportedChars(message.getRecipientAmbient()), 
			replaceUnsupportedChars(message.getPassMessageTo()),
			replaceUnsupportedChars(message.getRespondToMessage()), 
			replaceUnsupportedChars(message.getMessage())
		);
	}

	/**
	 * Replaces all non-alphanumeric characters with underscore.
	 */
	protected static String replaceUnsupportedChars(String originalStringValue) {
		return originalStringValue.replaceAll(
			ALPHA_NUMERIC_CHARS_REGEX, 
			AMBIENT_WORD_DELIMITER
		);
	}
	
	/**
	 * Checks for the last element of the ambient messages collection
	 */
	protected static boolean isLastAmbientMessage(List<Message> ambientMessages, 
												  Message currentMessage) {
		
		return currentMessage != ambientMessages.get(ambientMessages.size() - 1);
	}

	/**
	 * Remove the specified last character from given string,
	 * if it's present on the last position of the provided string.
	 * The provided string is trimmed before and after the remove operation.
	 */
	protected static String removeLastChar(String expression, String charToRemove) {
		
		return Optional.ofNullable(expression)
			.filter(str -> str.length() != 0)
			.map(String::trim)
			.filter(str -> str.charAt(str.length() - 1) == charToRemove.charAt(0))
			.map(str -> str.substring(0, str.length() - 1))
			.map(String::trim)
			.orElse(expression);
	}
	
	/**
	 * Checks whether a specific ambient (senderKey) is sender of the given message,
	 * and pass message to field is with value of <code>null</code>
	 */
	protected static boolean isSender(String sender, String senderKey, 
									  String respondTo, String passMsgTo) {
		
		return sender.equals(senderKey) && 
			   respondTo.equalsIgnoreCase(CCAConstants.MSG_RESPOND_TO_NULL) &&
			   passMsgTo.equalsIgnoreCase(CCAConstants.PASS_MESSAGE_TO_NULL);
	}
	
	/**
	 * Checks whether a specific ambient (senderKey) needs to respond to a message directly,
	 * and pass message to field is with value of <code>null</code>
	 */
	protected static boolean isRecipient(String sender, String senderKey, 
										 String respondTo, String passMsgTo) {
		
		return sender.equals(senderKey) &&
			   !respondTo.equalsIgnoreCase(CCAConstants.MSG_RESPOND_TO_NULL) &&
			   passMsgTo.equalsIgnoreCase(CCAConstants.PASS_MESSAGE_TO_NULL);
	}
	
	/**
	 * Checks whether a specific ambient (senderKey) has a pass message to field for specific message
	 */
	protected static boolean hasPassMsgTo(String sender, String senderKey, String passMsgTo) {
		
		return sender.equals(senderKey) &&
			   !passMsgTo.equalsIgnoreCase(CCAConstants.PASS_MESSAGE_TO_NULL);
	}
	
	/**
	 * Checks for active sender and recipient of given ambient message from the active ambients collection.
	 * The parent of the sender and recipient should be active as well.
	 * If it's not - the method will return a value of <code>false</code>.
	 */
	private static boolean areActiveAmbients(List<String> activeAmbients, Message msg) {
		boolean isActiveSender = activeAmbients.contains(msg.getSenderAmbient());
		boolean isActiveRecipient = activeAmbients.contains(msg.getRecipientAmbient());
		
		String senderParent = AmbientRepository.getParentAmbientName(msg.getSenderAmbient());
		String recipientParent = AmbientRepository.getParentAmbientName(msg.getRecipientAmbient());
		
		boolean isActiveSenderParent = senderParent != null && activeAmbients.contains(senderParent);
		boolean isActiveRecipientParent = recipientParent != null && activeAmbients.contains(recipientParent);
		
		return isActiveSender && isActiveRecipient && 
			   isActiveSenderParent && isActiveRecipientParent;
	}
}

package com.university.cca.generator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.university.cca.entities.Message;
import com.university.cca.exceptions.AmbientMessageNotFound;
import com.university.cca.files.csv.AmbientCSVReader;
import com.university.cca.repositories.AmbientRepository;

/**
 * Utility class related to the generation of the CCA file
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class GeneratorUtil {
	
	private static final String CSV_DELIMITER = ",";
	private static final String AMBIENT_WORD_DELIMITER = "_";
	private static final String ALPHA_NUMERIC_CHARS_REGEX = "[^A-Za-z0-9]";

	private GeneratorUtil() {
		// Prevent creating an object of type GeneratorUtil
	}

	/**
	 * Returns a list with messages that are sent or received from/to active ambient.
	 * The parent of the sender and recipient should be active as well.
	 * If it's not - the ambient message will NOT be included at the result set.
	 */
	protected static List<Message> getMessagesActiveAmbients(List<Message> messages) {
		List<String> activeAmbients = Arrays.asList(AmbientCSVReader.getActiveAmbientNamesSorted());
		
		return messages.stream()
			.filter(msg -> areActiveAmbients(activeAmbients, msg))
			.collect(Collectors.toList());
	}
	
	/**
	 * Finds recipient ambient message from the file with all available messages
	 */
	protected static Message findMessageRecipient(List<Message> ambientMessages,
									 			  Message message) throws AmbientMessageNotFound {
		String errorMsg = "Ambient message not found! Message: " + message.toString();
		
		return ambientMessages
			.stream()
			.filter(msg ->
				msg.getSenderAmbient().equals(message.getRecipientAmbient()) &&
				msg.getRecipientAmbient().equals(message.getSenderAmbient()) &&
				msg.getRespondToMessage().equals(message.getMessage())
			)
			.findFirst()
			.orElseThrow(() -> new AmbientMessageNotFound(errorMsg));
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

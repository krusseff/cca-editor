package com.university.cca.generator;

import java.util.List;

import com.university.cca.entities.Message;
import com.university.cca.exceptions.AmbientMessageNotFound;

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
}

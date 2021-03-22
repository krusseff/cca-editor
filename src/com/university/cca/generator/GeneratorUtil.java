package com.university.cca.generator;

import java.util.List;

import com.university.cca.entities.Message;
import com.university.cca.exceptions.AmbientMessageNotFound;

public class GeneratorUtil {
	
	private static final String CSV_DELIMITER = ",";

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
	 * Replaces all occurrences of the old value with the new value.
	 */
	protected static String replace(String originalString, 
									String stringOldValue, 
									String stringNewValue) {
		
		return originalString.replace(stringOldValue, stringNewValue);
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
}

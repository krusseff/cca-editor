package com.university.cca.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.entities.Message;

/**
 * Holds methods for fetching and validating ambient messages.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class MessageService {
	
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
    
	private static final int MESSAGE_MIN_LENGTH = 0;
	private static final int MESSAGE_MAX_LENGTH = 500;
	
	private MessageService() {
		// Prevent creating an object of type MessageService
	}
	
	/**
	 * Creates and returns a message object from given data
	 */
	public static Message constructMessage(Object senderAmbient, 
										   Object recipientAmbient,
										   Object passMessageTo,
										   Object respondToMessage,
										   String ambientMessage) {
		String sender = String.valueOf(senderAmbient);
		String recipient = String.valueOf(recipientAmbient);
		String passMsgTo = String.valueOf(passMessageTo);
		String respondToMsg = String.valueOf(respondToMessage);
		
		Message message = new Message(sender, recipient, passMsgTo, respondToMsg, ambientMessage);
		
		logger.info("Ambient Message constructed: {}", message);
		
		return message;
	}
	
	/**
	 * Validates the fields of the newly created ambient message object
	 */
	public static boolean isValidMessageInfo(Object senderAmbient,
											 Object recipientAmbient,
											 Object passMessageTo,
											 Object respondToMessage,
											 String ambientMessage) {
		
		boolean isValidSender = (senderAmbient != null);
		boolean isValidRecipient = (recipientAmbient != null);
		boolean isValidPassMessageTo = (passMessageTo != null);
		boolean isValidRespondToMessage = isValidRespondToMsg(respondToMessage);
		boolean isValidMessage = (ambientMessage != null && !ambientMessage.isEmpty());
		
		return isValidSender && isValidRecipient && 
			   isValidPassMessageTo && isValidRespondToMessage && isValidMessage;
	}
	
	/**
	 * Validates the message of the newly created ambient message
	 */
	public static boolean isValidMessageLength(String ambientMessage) {
		int messageLength = ambientMessage.length();
		
	    return messageLength > MESSAGE_MIN_LENGTH && messageLength <= MESSAGE_MAX_LENGTH;
	}
	
	/**
	 * Validates the respond to message of the newly created ambient message
	 */
	private static boolean isValidRespondToMsg(Object respondToMessage) {
		if (respondToMessage == null) {
			return true;
		} else {
			return respondToMessage.toString().length() > MESSAGE_MIN_LENGTH;
		}
	}
}

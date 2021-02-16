package com.university.cca.util;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.CreateAmbientMsgDialog;
import com.university.cca.entities.Message;

/**
 * Utility methods related to the create ambient message functionality
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CreateMessageUtil {
	
    private static final Logger logger = LoggerFactory.getLogger(CreateMessageUtil.class);
	
	private static final int MESSAGE_MIN_LENGTH = 0;
	private static final int MESSAGE_MAX_LENGTH = 500;

	private CreateMessageUtil() {
		// Prevent creating an object of type CreateMessageUtil
	}

	/**
	 * Method that creates and returns create ambient message button.
	 */
	public static JButton createMessageButton() {
		JButton createMessageButton = new JButton();
		
    	ImageIcon successIcon = new ImageIcon(Constants.SUCCESS_ICON_PATH);
    	createMessageButton.setIcon(successIcon);
    	createMessageButton.setText("Create Message");
    	
    	return createMessageButton;
	}
	
	/**
	 * Method that shows a success dialog if a message is created successfully.
	 */
	public static void createSuccessDialog(CreateAmbientMsgDialog parentDialog,
										   Object ambientSender,
										   Object ambientRecipient,
										   String ambientMessage) {
		JOptionPane.showMessageDialog(
			parentDialog,
			"The message is created and sent successfully! \n"
			+ "Ambient Sender: " + ambientSender + " \n"
			+ "Ambient Recipient: " + ambientRecipient + " \n"
			+ "Message: " + ambientMessage + " \n", 
			"Successful Operation",
            JOptionPane.INFORMATION_MESSAGE
		);
	}
	
	/**
	 * Method that shows an error dialog if the fields of an ambient message are invalid.
	 */
	public static void createErrorDialog(CreateAmbientMsgDialog parentDialog,
										 String errorMessage) {
		JOptionPane.showMessageDialog(
			parentDialog,
			errorMessage, 
			"Validation Error",
            JOptionPane.ERROR_MESSAGE
		);
	}
	
	/**
	 * Method that creates and returns a message object.
	 */
	public static Message constructAmbient(Object senderAmbient, 
										   Object recipientAmbient, 
										   String ambientMessage) {
		String sender = String.valueOf(senderAmbient);
		String recipient = String.valueOf(recipientAmbient);
		Message message = new Message(sender, recipient, ambientMessage);
		
		logger.info("Ambient Message constructed: {}", message);
		
		return message;
	}
	
	/**
	 * Method that validates the fields of the newly created ambient message object.
	 */
	public static boolean isValidMessageInfo(Object senderAmbient,
											 Object recipientAmbient,
											 String ambientMessage) {
		boolean isValidSender = (senderAmbient != null);
		boolean isValidRecipient = (recipientAmbient != null);
		boolean isValidMessage = (ambientMessage != null && !ambientMessage.isEmpty());
		
		return isValidSender && isValidRecipient && isValidMessage;
	}
	
	/**
	 * Method that validates the message of the newly created ambient message.
	 */
	public static boolean isValidMessageLength(String ambientMessage) {
		int messageLength = ambientMessage.length();
		
	    return messageLength > MESSAGE_MIN_LENGTH && messageLength <= MESSAGE_MAX_LENGTH;
	}
}

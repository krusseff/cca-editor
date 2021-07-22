package com.university.cca.util;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.CreateAmbientMsgDialog;

/**
 * Utility methods related to the create ambient message functionality of the application.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CreateMessageUtil {
	
	private static final String BUTTON_NAME = "Create Message";

	private CreateMessageUtil() {
		// Prevent creating an object of type CreateMessageUtil
	}

	/**
	 * Method that creates and returns create ambient message button.
	 * 
	 */
	public static JButton createMessageButton() {
		JButton createMessageButton = new JButton();
		
		createMessageButton.setText(BUTTON_NAME);
    	createMessageButton.setIcon(new ImageIcon(Constants.SUCCESS_ICON_PATH));
    	createMessageButton.setCursor(MouseCursorUtil.getMouseHand());
    	createMessageButton.setIconTextGap(Constants.ICON_GAP_SIZE);
    	
    	return createMessageButton;
	}
	
	/**
	 * Method that shows a success dialog if a message is created successfully.
	 * 
	 */
	public static void createSuccessDialog(CreateAmbientMsgDialog parentDialog,
										   Object ambientSender,
										   Object ambientRecipient,
										   Object respondToMessage,
										   String ambientMessage) {
		String respondToMsg = respondToMessage != null ? respondToMessage.toString() : "-";
		
		JOptionPane.showMessageDialog(
			parentDialog,
			"The message is created and sent successfully! \n"
			+ "Ambient Sender: " + ambientSender + " \n"
			+ "Ambient Recipient: " + ambientRecipient + " \n"
			+ "Respond To: " + respondToMsg + " \n" 
			+ "Message: " + ambientMessage + " \n", 
			"Successful Operation",
            JOptionPane.INFORMATION_MESSAGE
		);
	}
	
	/**
	 * Method that shows an error dialog if the fields of an ambient message are invalid.
	 * 
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
}

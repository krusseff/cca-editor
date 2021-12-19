package com.university.cca.util;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.CreateAmbientDialog;

/**
 * Utility methods related to the create ambient functionality of the CCA Editor.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CreateAmbientUtil {
	
	private static final String BUTTON_NAME 	= "Create Ambient";
	private static final String BUTTON_TOOL_TIP = "Click to create an ambient";

	private static final String ERROR_DIALOG_TITLE 	 = "Validation Error";
	private static final String SUCCESS_DIALOG_TITLE = "Successful Operation";
	
	private CreateAmbientUtil() {
		// Prevent creating an object of type CreateAmbientUtil
	}
	
	/**
	 * Method that creates and returns create ambient button.
	 * 
	 */
	public static JButton createAmbientButton() {
		JButton createAmbientButton = new JButton();
		
    	ImageIcon successIcon = new ImageIcon(Constants.SUCCESS_ICON_PATH);
    	createAmbientButton.setIcon(successIcon);
    	createAmbientButton.setText(BUTTON_NAME);
    	createAmbientButton.setToolTipText(BUTTON_TOOL_TIP);
    	createAmbientButton.setCursor(MouseCursorUtil.getMouseHand());
    	createAmbientButton.setIconTextGap(Constants.ICON_GAP_SIZE);
    	
    	return createAmbientButton;
	}
	
	/**
	 * Method that shows a success dialog if an ambient is created successfully.
	 * 
	 */
	public static void createSuccessDialog(CreateAmbientDialog parentDialog) {
		JOptionPane.showMessageDialog(
			parentDialog,
			"The ambient is created successfully!", 
			SUCCESS_DIALOG_TITLE,
            JOptionPane.INFORMATION_MESSAGE
		);
	}
	
	/**
	 * Method that shows an error dialog if the fields of an ambient are invalid.
	 * 
	 */
	public static void createErrorDialog(CreateAmbientDialog parentDialog,
										 String errorMessage) {
		JOptionPane.showMessageDialog(
			parentDialog,
			errorMessage, 
			ERROR_DIALOG_TITLE,
            JOptionPane.ERROR_MESSAGE
		);
	}
}

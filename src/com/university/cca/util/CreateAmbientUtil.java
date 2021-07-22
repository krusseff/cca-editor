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
    	createAmbientButton.setText("Create Ambient");
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
			"Successful Operation",
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
			"Validation Error",
            JOptionPane.ERROR_MESSAGE
		);
	}
}

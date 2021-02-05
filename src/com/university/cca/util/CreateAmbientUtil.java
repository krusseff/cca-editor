package com.university.cca.util;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.university.caa.entities.Ambient;
import com.university.cca.constants.Constants;

/**
 * Utility methods related to the create ambient functionality
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CreateAmbientUtil {
	
	private CreateAmbientUtil() {
		// Prevent creating an object of type CreateAmbientUtil
	}
	
	public static JButton createAmbientButton() {
		JButton createAmbientButton = new JButton();
		
    	ImageIcon successIcon = new ImageIcon(Constants.SUCCESS_ICON_PATH);
    	createAmbientButton.setIcon(successIcon);
    	createAmbientButton.setText("Create Ambient");
    	
    	return createAmbientButton;
	}
	
	public static void showErrorDialog() {
		// TODO
	}
	
	public static boolean isValidAmbient(String name, 
										 String location, 
										 Object parentAmbient) {
		
		boolean isValidName = (name != null && !name.isEmpty());
		boolean isValidLocation = (location != null && !location.isEmpty());
		boolean isValidParent = (parentAmbient != null);
		
		return isValidName && isValidLocation && isValidParent;
	}
	
	public static Ambient constructAmbient(String name, 
										   String location, 
										   boolean isStatic, 
										   Object parentAmbient) {
		
		String parent = String.valueOf(parentAmbient);
		
		return new Ambient(name, location, isStatic, parent);
	}
}

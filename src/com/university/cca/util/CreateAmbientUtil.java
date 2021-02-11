package com.university.cca.util;

import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.CreateAmbientDialog;
import com.university.cca.entities.Ambient;
import com.university.cca.enums.AmbientType;

/**
 * Utility methods related to the create ambient functionality
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CreateAmbientUtil {
	
    private static final String LATITUDE_REGEX ="^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$";
    private static final String LONGITUDE_REGEX ="^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$";
	
	private CreateAmbientUtil() {
		// Prevent creating an object of type CreateAmbientUtil
	}
	
	/**
	 * Method that creates and returns create ambient button.
	 */
	public static JButton createAmbientButton() {
		JButton createAmbientButton = new JButton();
		
    	ImageIcon successIcon = new ImageIcon(Constants.SUCCESS_ICON_PATH);
    	createAmbientButton.setIcon(successIcon);
    	createAmbientButton.setText("Create Ambient");
    	
    	return createAmbientButton;
	}
	
	/**
	 * Method that shows a success dialog if an ambient is created successfully.
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
	 */
	public static void createErrorDialog(CreateAmbientDialog parentDialog) {
		JOptionPane.showMessageDialog(
			parentDialog,
			"Please, enter valid values for the input fields!", 
			"Validation Error",
            JOptionPane.ERROR_MESSAGE
		);
	}
	
	/**
	 * Method that creates and returns an ambient object.
	 */
	public static Ambient constructAmbient(String name, 
										   String location, 
										   String latitude, 
										   String longitude,
										   boolean isStatic, 
										   Object parentAmbient,
										   AmbientType ambientType) {
		
		String parent = String.valueOf(parentAmbient);
		Ambient ambient = new Ambient(name, location, latitude, longitude, isStatic, parent, ambientType);
		
		System.out.println("Ambient: " + ambient);
		
		return ambient;
	}
	
	/**
	 * Method that validates the fields of the newly created ambient object.
	 */
	public static boolean isValidAmbient(String name, 
										 String location, 
										 String latitude, 
										 String longitude,
										 Object parentAmbient) {
		
		boolean isValidName = (name != null && !name.isEmpty());
		boolean isValidLocation = (location != null && !location.isEmpty());
		boolean isValidLatitude = isValidCoordinate(latitude, LATITUDE_REGEX);
		boolean isValidLongitude = isValidCoordinate(longitude, LONGITUDE_REGEX);
		boolean isValidParent = (parentAmbient != null);
		
		return isValidName && isValidLocation && 
			   isValidLatitude && isValidLongitude && 
			   isValidParent;
	}
	
	/**
	 * Method that validates the coordinates of the newly created ambient object.
	 */
	private static boolean isValidCoordinate(String coordinate, 
											 String coordinateRegEx) {
		
		Pattern coordinatePattern = Pattern.compile(coordinateRegEx);
		
	    return coordinate != null && 
	    	   !coordinate.isEmpty() && 
	    	   coordinatePattern.matcher(coordinate).matches();
	}
}

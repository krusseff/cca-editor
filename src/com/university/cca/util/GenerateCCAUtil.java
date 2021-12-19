package com.university.cca.util;

import javax.swing.JOptionPane;

import com.university.cca.constants.Constants;
import com.university.cca.frames.AppMainFrame;

/**
 * Utility methods related to the generate CCA file functionality of the application.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class GenerateCCAUtil {

	private static final String SUCCESS_DIALOG_TITLE = "Successful CCA Generation";
	private static final String ERROR_DIALOG_TITLE   = "CCA Generation Error";

	private GenerateCCAUtil() {
		// Prevent creating an object of type GenerateCCAUtil class
	}
	
	/**
	 * Method that shows a success dialog if the CCA file is generated successfully.
	 */
	public static void createSuccessDialog(AppMainFrame parentFrame) {
		JOptionPane.showMessageDialog(
			parentFrame,
			"The CCA file is generated successfully! \n" +
			"The file could be found at: " + Constants.AMBIENTS_CCA_FILE_PATH + " \n",
			SUCCESS_DIALOG_TITLE,
            JOptionPane.INFORMATION_MESSAGE
		);
	}
	
	/**
	 * Method that shows an error dialog if there is an error related to the CCA file generation.
	 */
	public static void createErrorDialog(AppMainFrame parentFrame) {
		JOptionPane.showMessageDialog(
			parentFrame,
			"The CCA file is NOT generated! \n" +
			"An error occurred during the generation of the CCA file! \n" +
			"Please, contact the CCA Editor Team for more information! \n",
			ERROR_DIALOG_TITLE,
            JOptionPane.ERROR_MESSAGE
		);
	}
}

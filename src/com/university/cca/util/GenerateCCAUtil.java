package com.university.cca.util;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.university.cca.constants.Constants;

/**
 * Utility methods related to the generate CCA file functionality of the application.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class GenerateCCAUtil {

	private GenerateCCAUtil() {
		// Prevent creating an object of type GenerateCCAUtil
	}
	
	/**
	 * Method that shows a success dialog if the CCA file is generated successfully.
	 * 
	 */
	public static void createSuccessDialog(JFrame parentFrame) {
		JOptionPane.showMessageDialog(
			parentFrame,
			"The CCA file is generated successfully! \n" +
			"The file could be found at: " + Constants.AMBIENTS_CCA_FILE_PATH + " \n",
			"Successful CCA Generation",
            JOptionPane.INFORMATION_MESSAGE
		);
	}
	
	/**
	 * Method that shows an error dialog if there is an error related to the CCA file generation.
	 * 
	 */
	public static void createErrorDialog(JFrame parentFrame) {
		JOptionPane.showMessageDialog(
			parentFrame,
			"The CCA file is NOT generated! \n" +
			"An error occurred during the generation of the CCA file! \n" +
			"Please, contact the CCA Editor Team for more information! \n",
			"CCA Generation Error",
            JOptionPane.ERROR_MESSAGE
		);
	}
}

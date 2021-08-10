package com.university.cca.util;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Utility class with methods for hepling working with files.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class FilesUtil {

	private FilesUtil() {
		// Prevent creating an object of type FilesUtil
	}
	
	public static void createSuccessDialog(JDialog parentDialog, String filePath) {
		JOptionPane.showMessageDialog(
			parentDialog,
			"The file is saved successfully! \n"
			+ "You can find it on the following location: \n"
			+ filePath + " \n",
			"File saved successfully",
            JOptionPane.INFORMATION_MESSAGE
		);
	}
	
	public static void createErrorDialog(JDialog parentDialog) {
		JOptionPane.showMessageDialog(
			parentDialog,
			"File with that name already exists! \n"
			+ "Please, try again with another name! \n", 
			"File already exists",
			JOptionPane.ERROR_MESSAGE
		);
	}
}

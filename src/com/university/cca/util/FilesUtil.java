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
		// Prevent creating an object of type FilesUtil class
	}
	
	public static void createFileSavedDialog(JDialog parentDialog, String filePath) {
		JOptionPane.showMessageDialog(
			parentDialog,
			"The file is saved successfully! \n"
			+ "You can find it on the following location: \n"
			+ filePath + " \n",
			"File Saved Successfully",
            JOptionPane.INFORMATION_MESSAGE
		);
	}
	
	public static void createFileExistsDialog(JDialog parentDialog) {
		JOptionPane.showMessageDialog(
			parentDialog,
			"File with that name already exists! \n"
			+ "Please, try again with another name! \n", 
			"File Already Exists",
			JOptionPane.ERROR_MESSAGE
		);
	}
	
	public static void createFileNotFoundDialog(JDialog parentDialog, String message) {
		JOptionPane.showMessageDialog(
			parentDialog,
			message, 
			"Not Found",
			JOptionPane.ERROR_MESSAGE
		);
	}
	
	public static void createUnexpectedErrorDialog(JDialog parentDialog) {
		JOptionPane.showMessageDialog(
			parentDialog,
			"Something went wrong! Please, try again later!", 
			"Unexpected Error",
			JOptionPane.ERROR_MESSAGE
		);
	}
}

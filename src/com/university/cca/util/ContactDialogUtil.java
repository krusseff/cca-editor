package com.university.cca.util;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.dialogs.menu.about.ContactDialog;

public class ContactDialogUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ContactDialogUtil.class);

	private static final int EMAIL_SUCCESS_STATUS = 0;
	private static final int EMAIL_VALIDATION_FAILED_STATUS = 1;
	private static final int EMAIL_FAILED_STATUS = 2;

	private ContactDialogUtil() {
		// Prevent creating an object of type ContactDialogUtil
	}
	
	public static void showEmailSentDialog(ContactDialog parentDialog, int emailResult) {
		switch (emailResult) {
			case EMAIL_SUCCESS_STATUS:
				createSuccessDialog(parentDialog);
				parentDialog.dispose();
				break;
	
			case EMAIL_VALIDATION_FAILED_STATUS:
				createValidationErrorDialog(parentDialog);
				break;
				
			case EMAIL_FAILED_STATUS:
				createErrorDialog(parentDialog);
				break;
				
			default:
				logger.error("Unexpected value was sent to the showEmailSentDialog() method: {}", emailResult);
				break;
		}
	}
	
	private static void createSuccessDialog(ContactDialog parentDialog) {
		JOptionPane.showMessageDialog(
			parentDialog,
			"Thank you for reaching us out! \n"
			+ "We will contact you via email soon! \n",
			"Successful Operation",
            JOptionPane.INFORMATION_MESSAGE
		);
	}
	
	public static void createValidationErrorDialog(ContactDialog parentDialog) {
		JOptionPane.showMessageDialog(
			parentDialog,
			"You enterred invalid values! \n"
			+ "Please, check the data into the input fields and try again! \n", 
			"Validation Error",
			JOptionPane.ERROR_MESSAGE
		);
	}
	
	public static void createErrorDialog(ContactDialog parentDialog) {
		JOptionPane.showMessageDialog(
			parentDialog,
			"Unexpected error occurred! \n"
			+ "Please, try to send the information again! \n", 
			"Unexpected Error",
			JOptionPane.ERROR_MESSAGE
		);
	}
}

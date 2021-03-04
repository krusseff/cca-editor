package com.university.cca.dialogs.menu.about;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class ContactDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final String TITLE_DIALOG = "Contact Us";
	
	public ContactDialog(JFrame parentFrame) {
		super(parentFrame, TITLE_DIALOG, true);
		
		// addDialogContent();
	
		// this.setSize(WIDHT_DIALOG, HEIGHT_DIALOG);
		this.setLocationRelativeTo(parentFrame);
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
	}
	
	// TODO: Add the exact implementation here
    // 6. Contact us: Form for contact and an email should be sent: Java mail API or GMAIL API
}

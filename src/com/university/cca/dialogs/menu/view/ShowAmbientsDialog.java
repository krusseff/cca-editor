package com.university.cca.dialogs.menu.view;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The dialog that holds the information for the ambients into a table with an option to update their information.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class ShowAmbientsDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ShowAmbientsDialog.class);

	private static final String TITLE = "Show All Ambients Dialog";
	
	private JFrame parentFrame;
	
	// [IN PROGRESS] Show ambients information: name, location, parents, and etc. 
	// IN TABLE: add description	

	public ShowAmbientsDialog(JFrame parentFrame) {
        super(parentFrame, TITLE, true);
        this.parentFrame = parentFrame;
        
        // TODO
        // addDialogContent();
        
        // TODO
        // this.setSize(WIDHT_DIALOG, HEIGHT_DIALOG);
        
        this.setLocationRelativeTo(parentFrame);

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
	}

	// Getters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

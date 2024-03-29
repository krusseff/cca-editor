package com.university.cca.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.util.MouseCursorUtil;

public class CancelDialogButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CancelDialogButton.class);
	
	private static final String BUTTON_NAME 	= "Cancel";
	private static final String BUTTON_TOOL_TIP = "Cancel Operation";
	private static final int YES_OPTION = 0;
	
	private JDialog parentDialog;
	
    public CancelDialogButton(JDialog parentDialog) {
    	this.parentDialog = parentDialog;
    	
        this.setText(BUTTON_NAME);
        this.setIcon(new ImageIcon(Constants.CANCEL_ICON_PATH));

        this.setCursor(MouseCursorUtil.getMouseHand());
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
        this.setToolTipText(BUTTON_TOOL_TIP);
        
        this.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Cancel Dialog button is clicked");
		
		int confirmationResult = createConfirmationDialog();
		
		if (confirmationResult == YES_OPTION) {
			this.parentDialog.dispose();
		} 

		logger.info("Cancel button confirmation dialog is closed with result: {}", confirmationResult);
	}
	
	/**
	 * Method that creates a confirmation dialog.
	 */
	private int createConfirmationDialog() {
		// 0=yes, 1=no, 2=cancel
		return JOptionPane.showConfirmDialog(
			getParentDialog(), 
			"Are you sure you want to permanently close and lose the entered information?", 
			"Confirm Cancel Operation",
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.ERROR_MESSAGE
        );
	}

	// Getters and Setters
	public JDialog getParentDialog() {
		return this.parentDialog;
	}
}

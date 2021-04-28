package com.university.cca.buttons;

import java.awt.Component;
import java.awt.Dimension;
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

public class CloseDialogButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CloseDialogButton.class);
	private static final String BUTTON_TOOL_TIP = "Close this window";
	private static final int YES_OPTION = 0;
	
	private JDialog parentDialog;
	
    public CloseDialogButton(JDialog parentDialog) {
    	this.parentDialog = parentDialog;
    	
    	this.setText("Close");
        this.setIcon(new ImageIcon(Constants.CLOSE_ICON_PATH));
        
        this.setToolTipText("Click to close the window");
        this.setCursor(MouseCursorUtil.getMouseHand());
        
        this.setPreferredSize(new Dimension(200, 35));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        this.setToolTipText(BUTTON_TOOL_TIP);
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
        
        this.addActionListener(this);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Close Dialog button is clicked");

		int confirmationResult = createConfirmationDialog();
		
		if (confirmationResult == YES_OPTION) {
			this.parentDialog.dispose();
		} 

		logger.info("Close button confirmation dialog is closed with result: {}", confirmationResult);
	}
	
	/**
	 * Method that creates a confirmation dialog
	 * 
	 * @return 0 = yes, 1 = no, 2 = cancel
	 */
	private int createConfirmationDialog() {
		return JOptionPane.showConfirmDialog(
			this.parentDialog, 
			"Are you sure you want to permanently close this window?", 
			"Confirm Close Dialog Operation",
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.ERROR_MESSAGE
        );
	}
	
	// Getters and Setters
	public JDialog getParentDialog() {
		return this.parentDialog;
	}
}

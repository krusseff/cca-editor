package com.university.cca.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.university.cca.constants.Constants;

public class CancelDialogButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static final int YES_OPTION = 0;
	
	private JDialog parentDialog;
	
    public CancelDialogButton(JDialog parentDialog) {
    	this.parentDialog = parentDialog;
    	
    	ImageIcon cancelIcon = new ImageIcon(Constants.CANCEL_ICON_PATH);
        this.setIcon(cancelIcon);
        this.setText("Cancel");
        
        this.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Cancel Dialog Button Clicked");
		
		int confirmationResult = createConfirmationDialog();
		
		if (confirmationResult == YES_OPTION) {
			parentDialog.dispose();
		} 

	    System.out.println("Cancel button confirmation dialog is closed with result: " + confirmationResult);
	}
	
	private int createConfirmationDialog() {
		// 0=yes, 1=no, 2=cancel
		return JOptionPane.showConfirmDialog(
			parentDialog, 
			"Are you sure you want to permanently close and lose the entered information?", 
			"Confirm Cancel Operation",
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.ERROR_MESSAGE
        );
	}

	public JDialog getParentDialog() {
		return parentDialog;
	}
}

package com.university.cca.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.CreateAmbientDialog;

public class CreateAmbientButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private CreateAmbientDialog parentDialog;
	
    public CreateAmbientButton(CreateAmbientDialog parentDialog) {
    	this.parentDialog = parentDialog;
    	
    	ImageIcon successIcon = new ImageIcon(Constants.SUCCESS_ICON_PATH);
        this.setIcon(successIcon);
        this.setText("Create Ambient");
        
        this.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Create Ambient Button Clicked");
		
		// TODO: Implement writing to files here
		// 1. File with ambient names only
		// 2. CCA file
		
		parentDialog.dispose();
		System.out.println("Create ambient operation finished successfully");
	}
	
	public CreateAmbientDialog getParentDialog() {
		return parentDialog;
	}
}

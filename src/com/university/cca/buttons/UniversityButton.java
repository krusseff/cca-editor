package com.university.cca.buttons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.CreateAmbientDialog;

public class UniversityButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JFrame parentFrame;
	
    public UniversityButton(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
    	ImageIcon icon = new ImageIcon(Constants.UNIVERSITY_ICON_PATH);

        this.setText("University");
        this.setIcon(icon);
        this.setPreferredSize(new Dimension(190, 80));
        
        this.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("University Button Clicked");
		
		new CreateAmbientDialog(
			parentFrame, 
			"Create University", 
			"Please, enter a valid university information"
		);
	}
	
	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}

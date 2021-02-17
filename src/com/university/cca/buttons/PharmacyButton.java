package com.university.cca.buttons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.CreateAmbientDialog;
import com.university.cca.enums.AmbientType;

public class PharmacyButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PharmacyButton.class);

	private JFrame parentFrame;
	
    public PharmacyButton(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
    	ImageIcon icon = new ImageIcon(Constants.PHARMACY_ICON_PATH);

        this.setText("Pharmacy");
        this.setIcon(icon);
        this.setPreferredSize(new Dimension(190, 80));
        
        this.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Pharmacy Button is clicked");
		
		new CreateAmbientDialog(
			parentFrame, 
			"Create Pharmacy", 
			"Please, enter a valid pharmacy information",
			AmbientType.PHARMACY
		);
	}
	
	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}
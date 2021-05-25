package com.university.cca.buttons.ambients;

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
import com.university.cca.util.MouseCursorUtil;

public class MotorbikeButton extends JButton implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(MotorbikeButton.class);
	
	private JFrame parentFrame;
	
    public MotorbikeButton(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
        this.setText("Motorbike");
        this.setIcon(new ImageIcon(Constants.MOTORBIKE_ICON_PATH));
        this.setPreferredSize(new Dimension(80, 40));
        this.setCursor(MouseCursorUtil.getMouseHand());
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
        
        this.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Motorbike Button is clicked");
		
		new CreateAmbientDialog(
			this.parentFrame, 
			"Create Motorbike", 
			"Please, enter a valid motorbike information",
			AmbientType.MOTORBIKE
		);
	}
	
	// Getters and Setters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

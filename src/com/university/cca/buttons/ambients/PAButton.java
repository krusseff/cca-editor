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

public class PAButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PAButton.class);
	
	private JFrame parentFrame;
	
    public PAButton(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
        this.setText("Personal Assistant");
        this.setIcon(new ImageIcon(Constants.PA_ICON_PATH));
        this.setPreferredSize(new Dimension(80, 40));
        this.setCursor(MouseCursorUtil.getMouseHand());
        
        this.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Personal Assitant Ambient Button is clicked");
		
		new CreateAmbientDialog(
			this.parentFrame, 
			"Create Personal Assistant", 
			"Please, enter a valid personal assistant information",
			AmbientType.PERSONAL_ASSISTANT
		);
	}
	
	// Getters and Setters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

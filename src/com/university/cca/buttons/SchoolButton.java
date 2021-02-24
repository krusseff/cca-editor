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
import com.university.cca.util.MouseCursorUtil;

public class SchoolButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SchoolButton.class);

	private JFrame parentFrame;
	
    public SchoolButton(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
        this.setText("School");
        this.setIcon(new ImageIcon(Constants.SCHOOL_ICON_PATH));
        this.setPreferredSize(new Dimension(135, 40));
        this.setCursor(MouseCursorUtil.getMouseHand());
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
        
        this.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("School Button is clicked");
		
		new CreateAmbientDialog(
			parentFrame, 
			"Create School", 
			"Please, enter a valid school information",
			AmbientType.SCHOOL
		);
	}
	
	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}

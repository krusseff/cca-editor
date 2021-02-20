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

public class HarbourButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(HarbourButton.class);
	
	private JFrame parentFrame;
	
    public HarbourButton(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
    	ImageIcon icon = new ImageIcon(Constants.HARBOUR_ICON_PATH);

        this.setText("Harbour");
        this.setIcon(icon);
        this.setPreferredSize(new Dimension(190, 80));
        this.setCursor(MouseCursorUtil.getMouseHand());
        
        this.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Harbour Button is clicked");
		
		new CreateAmbientDialog(
			parentFrame, 
			"Create Harbour", 
			"Please, enter a valid harbour information",
			AmbientType.HARBOUR
		);
	}
	
	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}

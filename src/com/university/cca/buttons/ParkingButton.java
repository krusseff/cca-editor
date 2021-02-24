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

public class ParkingButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ParkingButton.class);
	
	private JFrame parentFrame;
	
    public ParkingButton(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
        this.setText("Car Parking");
        this.setIcon(new ImageIcon(Constants.PARKING_ICON_PATH));
        this.setPreferredSize(new Dimension(135, 40));
        this.setCursor(MouseCursorUtil.getMouseHand());
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
        
        this.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Car Parking Button is clicked");
		
		new CreateAmbientDialog(
			parentFrame, 
			"Create Car Parking", 
			"Please, enter a valid car parking information",
			AmbientType.PARKING
		);
	}
	
	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}

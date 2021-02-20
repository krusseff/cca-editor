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

public class BusStationButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(BusStationButton.class);
	
	private JFrame parentFrame;
	
    public BusStationButton(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
    	ImageIcon icon = new ImageIcon(Constants.BUS_STATION_ICON_PATH);

        this.setText("Bus Station");
        this.setIcon(icon);
        this.setPreferredSize(new Dimension(190, 80));
        this.setCursor(MouseCursorUtil.getMouseHand());
        
        this.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Bus Station button is clicked");
		
		new CreateAmbientDialog(
			parentFrame, 
			"Create Bus Station", 
			"Please, enter a valid bus station information",
			AmbientType.BUS_STATION
		);
	}
	
	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}

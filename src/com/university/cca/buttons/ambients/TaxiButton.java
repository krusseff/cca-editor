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

public class TaxiButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(TaxiButton.class);
	
	private JFrame parentFrame;
	
    public TaxiButton(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
        this.setText("Taxi");
        this.setIcon(new ImageIcon(Constants.TAXI_ICON_PATH));
        this.setPreferredSize(new Dimension(80, 40));
        this.setCursor(MouseCursorUtil.getMouseHand());
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
        
        this.addActionListener(this);
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Taxi button is clicked");
		
		new CreateAmbientDialog(
			parentFrame, 
			"Create Taxi", 
			"Please, enter a valid taxi information",
			AmbientType.TAXI
		);
	}
	
	// Getters and Setters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

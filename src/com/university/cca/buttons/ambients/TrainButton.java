package com.university.cca.buttons.ambients;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.CreateAmbientDialog;
import com.university.cca.enums.AmbientType;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.MouseCursorUtil;

public class TrainButton extends JButton implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(TrainButton.class);
	
	private AppMainFrame parentFrame;
	
    public TrainButton(AppMainFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
        this.setText("Train");
        this.setIcon(new ImageIcon(Constants.TRAIN_ICON_PATH));
        this.setPreferredSize(new Dimension(80, 40));
        this.setCursor(MouseCursorUtil.getMouseHand());
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
        
        this.addActionListener(this);
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Train button is clicked");
		
		new CreateAmbientDialog(
			this.parentFrame, 
			"Create Train", 
			"Please, enter a valid train information",
			AmbientType.TRAIN
		);
	}
	
	// Getters and Setters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

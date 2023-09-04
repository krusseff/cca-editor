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

public class AudioSensorButton extends JButton implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AudioSensorButton.class);

	private static final String BUTTON_NAME 	= "Audio Sensor";
	private static final String BUTTON_TOOL_TIP = "Create Audio Sensor";
	
	private AppMainFrame parentFrame;
	
    public AudioSensorButton(AppMainFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
        this.setText(BUTTON_NAME);
        this.setToolTipText(BUTTON_TOOL_TIP);
        this.setIcon(new ImageIcon(Constants.AUDIO_SENSOR_ICON_PATH));
        this.setPreferredSize(new Dimension(80, 40));
        this.setCursor(MouseCursorUtil.getMouseHand());
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
        
        this.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Audio Sensor Button is clicked");
		
		new CreateAmbientDialog(
			getParentFrame(), 
			"Create Audio Sensor", 
			"Please, enter a valid audio sensor information",
			AmbientType.AUDIO_SENSOR
		);
	}
	
	// Getters and Setters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

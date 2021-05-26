package com.university.cca.buttons.cca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.UnderConstructionDialog;
import com.university.cca.util.MouseCursorUtil;

/**
 * The button that starts the CCA scenario from the generated CCA file via the CCA Animator
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class StartAnimatedScenarioButton extends JButton implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(StartAnimatedScenarioButton.class);

	private static final String BUTTON_NAME = "Start Animated Scenario";
	private static final String BUTTON_TOOL_TIP = "Start the CCA Scenario via the animator";
	
	private JFrame parentFrame;
	
    public StartAnimatedScenarioButton(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
        this.setText(BUTTON_NAME);
        this.setIcon(new ImageIcon(Constants.START_ANIMATED_CCA_ICON_PATH));
        
        this.setCursor(MouseCursorUtil.getMouseHand());
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
        this.setToolTipText(BUTTON_TOOL_TIP);
        
        this.setEnabled(false);
        
        this.addActionListener(this);
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Start the CCA Animated Scenario Button is clicked");

		new UnderConstructionDialog(this.parentFrame);
	}
	
	// Getters and Setters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

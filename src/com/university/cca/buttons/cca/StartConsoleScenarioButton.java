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
 * The button that starts the CCA scenario from the generated CCA file
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class StartConsoleScenarioButton extends JButton implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(StartConsoleScenarioButton.class);
	
	private JFrame parentFrame;
	
    public StartConsoleScenarioButton(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
        this.setText("Start Console Scenario");
        this.setIcon(new ImageIcon(Constants.START_CCA_ICON_PATH));
        
        this.setCursor(MouseCursorUtil.getMouseHand());
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
        
        this.setEnabled(false);
        
        this.addActionListener(this);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Start the CCA Scenario button is clicked");

		// TODO: Exact implementation here
		new UnderConstructionDialog(parentFrame);
	}
	
	// Getters and Setters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

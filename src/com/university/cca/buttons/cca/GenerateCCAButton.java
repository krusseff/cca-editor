package com.university.cca.buttons.cca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.generator.CCAGenerator;
import com.university.cca.util.GenerateCCAUtil;
import com.university.cca.util.MouseCursorUtil;

/**
 * The button that starts the generation of the CCA file
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class GenerateCCAButton extends JButton implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(GenerateCCAButton.class);
	
	private static final int CCA_STATUS_SUCCESS = 0;
	private static final int CCA_STATUS_FAILURE = 1;
	private static final String BUTTON_NAME = "Generate CCA";
	private static final String BUTTON_TOOL_TIP = "Create the whole CCA Model and as a last step generate the CCA file";
	
	private AppMainFrame parentFrame;
	
    public GenerateCCAButton(AppMainFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
        this.setText(BUTTON_NAME);
        this.setIcon(new ImageIcon(Constants.GENERATE_CCA_ICON_PATH));
        
        this.setCursor(MouseCursorUtil.getMouseHand());
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
        this.setToolTipText(BUTTON_TOOL_TIP);
        
        this.addActionListener(this);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Generate CCA Button is clicked and generating CCA file is triggered");
		
		triggerCCAGeneration();
	}
	
	/**
	 * Triggers the generation of the CCA File and returns a result of that
	 */
	private void triggerCCAGeneration() {
		int generationResult = CCAGenerator.generate();
		
		switch (generationResult) {
			case CCA_STATUS_SUCCESS:
				GenerateCCAUtil.createSuccessDialog(this.parentFrame);
				logger.info("The CCA file is generated successfully! Exit code: {}", generationResult);
				break;
				
			case CCA_STATUS_FAILURE:
				GenerateCCAUtil.createErrorDialog(this.parentFrame);
				logger.error("The CCA file is NOT generated! Exit code: {}", generationResult);
				break;
	
			default:
				GenerateCCAUtil.createErrorDialog(this.parentFrame);
				logger.error("The CCA file is NOT generated! Unexpected error occurred!");
				break;
		}
	}
	
	// Getters and Setters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

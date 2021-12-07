package com.university.cca.buttons.cca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.CCAConstants;
import com.university.cca.constants.Constants;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.generator.CCAGeneratorV2;
import com.university.cca.util.GenerateCCAUtil;
import com.university.cca.util.MouseCursorUtil;

/**
 * Starts the generation of the CCA file with version 2 of the implementation.
 * 
 * @author Konstantin Rusev
 * @version 2.0
 */
public class GenerateCCAButtonV2 extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(GenerateCCAButtonV2.class);

	private static final String BUTTON_NAME 	= "Generate CCA (Version 2)";
	private static final String BUTTON_TOOL_TIP = "Create the whole CCA Model and as a last step generate the CCA file (Version 2)";
	
	private AppMainFrame parentFrame;
	
    public GenerateCCAButtonV2(AppMainFrame parentFrame) {
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
		logger.info("Generate CCA Button (Version 2) is clicked and generating CCA file is triggered");
		
		triggerCCAGenerationV2();
	}
	
	/**
	 * Triggers the generation of the CCA File (Version 2)
	 */
	private void triggerCCAGenerationV2() {
		int generationResult = CCAGeneratorV2.generate();
		
		switch (generationResult) {
			case CCAConstants.CCA_STATUS_SUCCESS:
				GenerateCCAUtil.createSuccessDialog(getParentFrame());
				logger.info("The CCA file is generated successfully! Exit code: {}", generationResult);
				break;
				
			case CCAConstants.CCA_STATUS_FAILURE:
				GenerateCCAUtil.createErrorDialog(getParentFrame());
				logger.error("The CCA file is NOT generated! Exit code: {}", generationResult);
				break;
	
			default:
				GenerateCCAUtil.createErrorDialog(getParentFrame());
				logger.error("The CCA file is NOT generated! Unexpected error occurred!");
				break;
		}
	}
	
	// Getters and Setters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

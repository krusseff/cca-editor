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
import com.university.cca.generator.CCAGeneratorV1;
import com.university.cca.util.GenerateCCAUtil;
import com.university.cca.util.MouseCursorUtil;

/**
 * The button that starts the generation of the CCA file (Version 1)
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class GenerateCCAButtonV1 extends JButton implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(GenerateCCAButtonV1.class);
	
	private static final String BUTTON_NAME 	= "Generate CCA (Version 1)";
	private static final String BUTTON_TOOL_TIP = "Create the whole CCA Model and as a last step generate the CCA file (Version 1)";
	
	private AppMainFrame parentFrame;
	
    public GenerateCCAButtonV1(AppMainFrame parentFrame) {
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
		logger.info("Generate CCA Button (Version 1) is clicked and generating CCA file is triggered");
		
		triggerCCAGenerationV1();
	}
	
	/**
	 * Triggers the generation of the CCA File (Version 1) and returns a result of that
	 */
	private void triggerCCAGenerationV1() {
		int generationResult = CCAGeneratorV1.generate();
		
		switch (generationResult) {
			case CCAConstants.CCA_STATUS_SUCCESS:
				GenerateCCAUtil.createSuccessDialog(this.parentFrame);
				logger.info("The CCA file is generated successfully! Exit code: {}", generationResult);
				break;
				
			case CCAConstants.CCA_STATUS_FAILURE:
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

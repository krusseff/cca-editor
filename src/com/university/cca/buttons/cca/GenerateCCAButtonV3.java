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
import com.university.cca.generator.CCAGeneratorV3;
import com.university.cca.util.GenerateCCAUtil;
import com.university.cca.util.MouseCursorUtil;

/**
 * Starts the generation of the CCA file with version 3 of the implementation.
 * V3 supports sibling, parent-to-child, and child-to-parent communication.
 * 
 * @author Konstantin Rusev
 * @version 3.0
 */
public class GenerateCCAButtonV3 extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(GenerateCCAButtonV3.class);

	private static final String BUTTON_NAME     = "Generate CCA (Version 3)";
	private static final String BUTTON_TOOL_TIP = "Generate CCA file with support for parent-child and sibling communication (Version 3)";
	
	private AppMainFrame parentFrame;
	
	public GenerateCCAButtonV3(AppMainFrame parentFrame) {
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
		logger.info("Generate CCA Button (Version 3) is clicked and generating CCA file is triggered");
		triggerCCAGenerationV3();
	}

	private void triggerCCAGenerationV3() {
		int generationResult = CCAGeneratorV3.generate();
		
		switch (generationResult) {
			case CCAConstants.CCA_STATUS_SUCCESS:
				GenerateCCAUtil.createSuccessDialog(getParentFrame());
				logger.info("CCA V3: The CCA file is generated successfully! Exit code: {}", generationResult);
				break;
				
			case CCAConstants.CCA_STATUS_FAILURE:
				GenerateCCAUtil.createErrorDialog(getParentFrame());
				logger.error("CCA V3: The CCA file is NOT generated! Exit code: {}", generationResult);
				break;

			default:
				GenerateCCAUtil.createErrorDialog(getParentFrame());
				logger.error("CCA V3: The CCA file is NOT generated! Unexpected error occurred!");
				break;
		}
	}

	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

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
import com.university.cca.generator.CCAGenerator;
import com.university.cca.util.GenerateCCAUtil;
import com.university.cca.util.MouseCursorUtil;

/**
 * The button that triggers generation of the CCA file
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class GenerateCCAButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(GenerateCCAButton.class);
	
	private static final int CCA_STATUS_SUCCESS = 0;
	private static final int CCA_STATUS_FAILURE = 1;
	
	private JFrame parentFrame;
	
    public GenerateCCAButton(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
        this.setText("CCA Generator");
        this.setIcon(new ImageIcon(Constants.GENERATE_CCA_ICON_PATH));
        
        this.setPreferredSize(new Dimension(155, 35));
        this.setCursor(MouseCursorUtil.getMouseHand());
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
        
        this.addActionListener(this);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Generate CCA Button is clicked and generating CCA file is triggered");
		
		triggerCCAGeneration();
	}
	
	private void triggerCCAGeneration() {
		int generationResult = CCAGenerator.generate();
		
		switch (generationResult) {
			case CCA_STATUS_SUCCESS:
				GenerateCCAUtil.createSuccessDialog(parentFrame);
				logger.info("The CCA file is generated successfully! Exit code: {}", generationResult);
				break;
				
			case CCA_STATUS_FAILURE:
				GenerateCCAUtil.createErrorDialog(parentFrame);
				logger.error("The CCA file is NOT generated! Exit code: {}", generationResult);
				break;
	
			default:
				GenerateCCAUtil.createErrorDialog(parentFrame);
				logger.error("The CCA file is NOT generated! Unexpected error occurred!");
				break;
		}
	}
	
	// Getters and Setters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

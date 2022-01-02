package com.university.cca.buttons.cca;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.cca.generation.CCAGenerationDialog;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.MouseCursorUtil;

/**
 * The button that opens the CCA Generator Dialog
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CCAGeneratorButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CCAGeneratorButton.class);
	
	private static final String BUTTON_NAME     = "CCA Generator";
	private static final String BUTTON_TOOL_TIP = "Create the whole CCA Model and as a last step generate the CCA file";
	
	private AppMainFrame parentFrame;
	
    public CCAGeneratorButton(AppMainFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
        this.setText(BUTTON_NAME);
        this.setIcon(new ImageIcon(Constants.GENERATOR_CCA_ICON_PATH));
        
        this.setPreferredSize(new Dimension(155, 35));
        this.setCursor(MouseCursorUtil.getMouseHand());
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
        this.setToolTipText(BUTTON_TOOL_TIP);
        
        this.addActionListener(this);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("CCA Generator Button is clicked");
		
		new CCAGenerationDialog(getParentFrame());
	}
	
	// Getters and Setters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

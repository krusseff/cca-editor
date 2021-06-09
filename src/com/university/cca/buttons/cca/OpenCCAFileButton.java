package com.university.cca.buttons.cca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.cca.generation.OpenCCAFileDialog;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.MouseCursorUtil;

/**
 * The button that opens the generated CCA file
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class OpenCCAFileButton extends JButton implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(OpenCCAFileButton.class);
	
	private static final String BUTTON_NAME = "Open CCA File";
	private static final String BUTTON_TOOL_TIP = "Notice that you will edit the CCA file";
	
	private AppMainFrame parentFrame;
	
    public OpenCCAFileButton(AppMainFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
        this.setText(BUTTON_NAME);
        this.setIcon(new ImageIcon(Constants.OPEN_CCA_ICON_PATH));
        
        this.setCursor(MouseCursorUtil.getMouseHand());
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
        this.setToolTipText(BUTTON_TOOL_TIP);
        
        this.addActionListener(this);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Open the CCA file Button is clicked");

		new OpenCCAFileDialog(getParentFrame());
	}
	
	// Getters and Setters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

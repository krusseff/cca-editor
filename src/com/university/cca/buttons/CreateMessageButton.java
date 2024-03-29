package com.university.cca.buttons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.CreateAmbientMsgDialog;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.MouseCursorUtil;

/**
 * The button that shows the dialog for creating a message between two ambients
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CreateMessageButton extends JButton implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CreateMessageButton.class);
	
	private static final String BUTTON_NAME 	= "Send Message";
	private static final String BUTTON_TOOL_TIP = "Create and Send Ambient Message";
	
	private AppMainFrame parentFrame;
	
    public CreateMessageButton(AppMainFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
        this.setText(BUTTON_NAME);
        this.setIcon(new ImageIcon(Constants.MESSAGE_ICON_PATH));

        this.setPreferredSize(new Dimension(155, 35));
        this.setCursor(MouseCursorUtil.getMouseHand());
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
        this.setToolTipText(BUTTON_TOOL_TIP);
        
        this.addActionListener(this);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Create Message Button is clicked and create ambient message dialog is opened");
		
		new CreateAmbientMsgDialog(getParentFrame());
	}
	
	// Getters and Setters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

package com.university.cca.buttons;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.util.MouseCursorUtil;

public class CloseDialogButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CloseDialogButton.class);
	
	private JDialog parentDialog;
	
    public CloseDialogButton(JDialog parentDialog) {
    	this.parentDialog = parentDialog;
    	
    	this.setText("Close");
        this.setIcon(new ImageIcon(Constants.CLOSE_ICON_PATH));
        
        this.setToolTipText("Click to close the window");
        this.setCursor(MouseCursorUtil.getMouseHand());
        
        this.setPreferredSize(new Dimension(150, 35));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
        
        this.addActionListener(this);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Close Dialog button is clicked");

		parentDialog.dispose();
	}
	
	// Getters and Setters
	public JDialog getParentDialog() {
		return parentDialog;
	}
}

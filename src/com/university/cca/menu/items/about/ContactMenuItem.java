package com.university.cca.menu.items.about;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.menu.about.ContactDialog;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.MouseCursorUtil;

public class ContactMenuItem extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ContactMenuItem.class);
    
    private AppMainFrame parentFrame;
    
	public ContactMenuItem(AppMainFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.ABOUT_ITEM_CONTACT);		
		this.setIcon(new ImageIcon(Constants.CONTACT_ICON_PATH));
		this.setCursor(MouseCursorUtil.getMouseHand());
		this.setIconTextGap(Constants.ICON_GAP_SIZE);
		
		// Open About Menu and click on Contact Us or press 'CTRL + 5'
	    this.setAccelerator(KeyStroke.getKeyStroke(Constants.CONTACT_US_SHORTCUT, Constants.CTRL_SHORTCUT));
		
		this.addActionListener(this);
	}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Contact Us About item menu button is clicked");

		new ContactDialog(getParentFrame());
	}

	// Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

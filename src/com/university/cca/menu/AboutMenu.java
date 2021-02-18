package com.university.cca.menu;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;

public class AboutMenu extends JMenu {

	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(AboutMenu.class);

    private JFrame parentFrame;
    
    public AboutMenu(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
    	this.setText(Constants.ABOUT);
		this.setIcon(new ImageIcon(Constants.ABOUT_ICON_PATH));

		// TODO
		this.setEnabled(false);
    	
    	logger.info("About option of the menu is initialized");
    }
    
    // Elements:
    // 1. About CCA
    // 2. About CCA Editor
    // 3. Team that works on this
    // 4. Useful links: delc, delc2, delc space, github repo, fmi plovdiv web site
    // 5. Technologies: Java 8, and all other libraries
    // 6. Contact us: Form for contact and an email should be sent: Java mail API or GMAIL API
    
	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}

package com.university.cca.menu;

import javax.swing.ImageIcon;
import javax.swing.JMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;

public class HelpMenu extends JMenu {

	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(HelpMenu.class);

    public HelpMenu() {
    	this.setText(Constants.HELP);
    	
    	ImageIcon icon = new ImageIcon(Constants.HELP_ICON_PATH);
		this.setIcon(icon);

		// TODO
		this.setEnabled(false);
    	
    	logger.info("Help option of the menu is initialized");
    }
    
    // TODO: Elements
    // 1. Questions -> FAQ (Frequently Answered Questions)
    // 2. Shortcuts of the application and keyboard combinations
}
package com.university.cca.menu;

import javax.swing.ImageIcon;
import javax.swing.JMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;

public class ViewMenu extends JMenu {

	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ViewMenu.class);

    public ViewMenu() {
    	this.setText(Constants.VIEW);
    	
    	ImageIcon icon = new ImageIcon(Constants.VIEW_ICON_PATH);
		this.setIcon(icon);
		
		// TODO
		this.setEnabled(false);
    	
    	logger.info("View option of the menu is initialized");
    }
    
    // Elements:
    // 1. Show ambients CSV file ???
    // 2. Show ambient messages CSV file ???
    // 3. Show ambient CCA file ???
    // 4. Show ambients information: name, location, parents, and etc.
}

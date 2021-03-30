package com.university.cca.menu;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.util.MouseCursorUtil;

public class ViewMenu extends JMenu {

	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ViewMenu.class);

    private JFrame parentFrame;
    
    public ViewMenu(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
    	this.setText(Constants.VIEW);
		this.setIcon(new ImageIcon(Constants.VIEW_ICON_PATH));
		this.setCursor(MouseCursorUtil.getMouseHand());
		
		// TODO
		this.setEnabled(false);
		
		addViewMenuItems();
    	
    	logger.info("View option of the menu is initialized");
    }
    
    // Elements:
    // 1. Show ambients CSV file ???
    // 2. Show ambient messages CSV file ???
    // 3. Show ambient CCA file ???
    // 4. Show ambients information: name, location, parents, and etc. IN TABLE: add description
    // 5. Show ambients messages: sender, recipient, message. IN TABLE
    
    private void addViewMenuItems() {
    	// this.add();
    	this.addSeparator();
    	// this.add();
    }
    
	// Getters and Setters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

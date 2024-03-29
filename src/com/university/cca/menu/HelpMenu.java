package com.university.cca.menu;

import javax.swing.ImageIcon;
import javax.swing.JMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.menu.items.help.FAQMenuItem;
import com.university.cca.menu.items.help.ShortcutsMenuItem;
import com.university.cca.menu.items.help.TermsMenuItem;
import com.university.cca.util.MouseCursorUtil;

public class HelpMenu extends JMenu {

	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(HelpMenu.class);

    private AppMainFrame parentFrame;
    
    public HelpMenu(AppMainFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
    	this.setText(Constants.HELP);
		this.setIcon(new ImageIcon(Constants.HELP_ICON_PATH));
		this.setCursor(MouseCursorUtil.getMouseHand());

		addHelpMenuItems();
    	
    	logger.info("Help option of the menu is initialized");
    }
    
    private void addHelpMenuItems() {
    	this.add(new FAQMenuItem(getParentFrame()));
    	this.add(new TermsMenuItem(getParentFrame()));
    	this.addSeparator();
    	this.add(new ShortcutsMenuItem(getParentFrame()));
    }
    
    // Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

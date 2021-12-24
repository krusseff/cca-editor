package com.university.cca.menu;

import javax.swing.ImageIcon;
import javax.swing.JMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.menu.items.edit.CopyMenuItem;
import com.university.cca.menu.items.edit.CutMenuItem;
import com.university.cca.menu.items.edit.DeleteMenuItem;
import com.university.cca.menu.items.edit.PasteMenuItem;
import com.university.cca.util.MouseCursorUtil;

public class EditMenu extends JMenu {

	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(EditMenu.class);
    
    private AppMainFrame parentFrame;

    public EditMenu(AppMainFrame parentFrame) {
    	this.parentFrame = parentFrame;

    	this.setText(Constants.EDIT);
		this.setIcon(new ImageIcon(Constants.EDIT_ICON_PATH));
		this.setCursor(MouseCursorUtil.getMouseHand());
		
    	addEditMenuItems();
    	
    	logger.info("Edit option of the menu is initialized");
    }
    
    private void addEditMenuItems() {
    	this.add(new CutMenuItem(getParentFrame()));
    	this.add(new CopyMenuItem(getParentFrame()));
    	this.add(new PasteMenuItem(getParentFrame()));
    	this.addSeparator();
    	this.add(new DeleteMenuItem(getParentFrame()));
    }

    // Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

package com.university.cca.menu;

import javax.swing.ImageIcon;
import javax.swing.JMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.menu.items.view.ShowAmbientMessages;
import com.university.cca.menu.items.view.ShowAmbients;
import com.university.cca.menu.items.view.ShowAmbientsCCAFile;
import com.university.cca.menu.items.view.ShowAmbientsCSVFile;
import com.university.cca.menu.items.view.ShowMessagesCSVFile;
import com.university.cca.util.MouseCursorUtil;

public class ViewMenu extends JMenu {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ViewMenu.class);

    private AppMainFrame parentFrame;
    
    public ViewMenu(AppMainFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
    	this.setText(Constants.VIEW);
		this.setIcon(new ImageIcon(Constants.VIEW_ICON_PATH));
		this.setCursor(MouseCursorUtil.getMouseHand());
		
		addViewMenuItems();
		
		logger.info("View menu option is initialized");
    }
    
    private void addViewMenuItems() {
    	this.add(new ShowAmbientsCSVFile(getParentFrame()));
    	this.add(new ShowMessagesCSVFile(getParentFrame()));
    	this.addSeparator();
    	this.add(new ShowAmbientsCCAFile(getParentFrame()));
    	this.addSeparator();
    	this.add(new ShowAmbients(getParentFrame()));
    	this.add(new ShowAmbientMessages(getParentFrame()));
    }
    
	// Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

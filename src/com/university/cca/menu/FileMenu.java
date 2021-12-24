package com.university.cca.menu;

import javax.swing.ImageIcon;
import javax.swing.JMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.menu.items.file.ExitMenuItem;
import com.university.cca.menu.items.file.ExportMenuItem;
import com.university.cca.menu.items.file.ImportMenuItem;
import com.university.cca.menu.items.file.NewMenuItem;
import com.university.cca.menu.items.file.OpenMenuItem;
import com.university.cca.menu.items.file.RefreshMenuItem;
import com.university.cca.menu.items.file.RestartMenuItem;
import com.university.cca.menu.items.file.SaveMenuItem;
import com.university.cca.menu.items.file.TerminalMenuItem;
import com.university.cca.util.MouseCursorUtil;

public class FileMenu extends JMenu {

	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(FileMenu.class);

    private AppMainFrame parentFrame;
    
    public FileMenu(AppMainFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
    	this.setText(Constants.FILE);
		this.setIcon(new ImageIcon(Constants.FILE_ICON_PATH));
		this.setCursor(MouseCursorUtil.getMouseHand());

		addFileMenuItems();
		
    	logger.info("File option of the menu is initialized");
    }
    
    private void addFileMenuItems() {
    	this.add(new NewMenuItem(getParentFrame()));
    	this.add(new OpenMenuItem(getParentFrame()));
    	this.add(new SaveMenuItem(getParentFrame()));
    	this.addSeparator();
    	this.add(new ImportMenuItem(getParentFrame()));
    	this.add(new ExportMenuItem(getParentFrame()));
    	this.addSeparator();
    	this.add(new RefreshMenuItem(getParentFrame()));
    	this.add(new RestartMenuItem(getParentFrame()));
    	this.addSeparator();
    	this.add(new TerminalMenuItem(getParentFrame()));
    	this.addSeparator();
    	this.add(new ExitMenuItem(getParentFrame()));
    }
    
	// Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

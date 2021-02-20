package com.university.cca.menu;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
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

    private JFrame parentFrame;
    
    public FileMenu(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
    	this.setText(Constants.FILE);
		this.setIcon(new ImageIcon(Constants.FILE_ICON_PATH));
		this.setCursor(MouseCursorUtil.getMouseHand());

		addFileMenuItems();
		
    	logger.info("File option of the menu is initialized");
    }
    
    private void addFileMenuItems() {
    	this.addSeparator();
    	this.add(new NewMenuItem(parentFrame));
    	this.add(new OpenMenuItem(parentFrame));
    	this.add(new SaveMenuItem(parentFrame));
    	this.addSeparator();
    	this.add(new ImportMenuItem(parentFrame));
    	this.add(new ExportMenuItem(parentFrame));
    	this.addSeparator();
    	this.add(new RefreshMenuItem(parentFrame));
    	this.add(new RestartMenuItem(parentFrame));
    	this.addSeparator();
    	this.add(new TerminalMenuItem(parentFrame));
    	this.addSeparator();
    	this.add(new ExitMenuItem(parentFrame));
    	this.addSeparator();
    }
    
	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}

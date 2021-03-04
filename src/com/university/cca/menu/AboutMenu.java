package com.university.cca.menu;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;

import com.university.cca.constants.Constants;
import com.university.cca.menu.items.about.CCAEditorMenuItem;
import com.university.cca.menu.items.about.CCAMenuItem;
import com.university.cca.menu.items.about.CCATeamMenuItem;
import com.university.cca.menu.items.about.ContactMenuItem;
import com.university.cca.menu.items.about.LinksMenuItem;
import com.university.cca.menu.items.about.TechnologiesMenuItem;
import com.university.cca.util.MouseCursorUtil;

public class AboutMenu extends JMenu {

	private static final long serialVersionUID = 1L;

    private JFrame parentFrame;
    
    public AboutMenu(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
    	this.setText(Constants.ABOUT);
		this.setIcon(new ImageIcon(Constants.ABOUT_ICON_PATH));
		this.setCursor(MouseCursorUtil.getMouseHand());
    	
		addAboutMenuItems();
    }
    
    private void addAboutMenuItems() {
    	this.add(new CCAMenuItem(parentFrame));
    	this.add(new CCAEditorMenuItem(parentFrame));
    	this.add(new CCATeamMenuItem(parentFrame));
    	this.addSeparator();
    	this.add(new LinksMenuItem(parentFrame));
    	this.addSeparator();
    	this.add(new TechnologiesMenuItem(parentFrame));
    	this.addSeparator();
    	this.add(new ContactMenuItem(parentFrame));
    }
    
	// Getters and Setters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

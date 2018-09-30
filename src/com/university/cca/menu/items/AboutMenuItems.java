package com.university.cca.menu.items;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.university.cca.util.CCAUtils;

/**
 * About Menu Items of CCA Editor application
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AboutMenuItems {
	
	private CCAUtils ccaUtils = new CCAUtils();
	
	public void addAboutCCAMenuItem(JMenu aboutMenu, JMenuItem aboutCCAMenuItem) {
		ccaUtils.setMenuItemFontSize(aboutCCAMenuItem);
		aboutMenu.add(aboutCCAMenuItem);
	}
	
	public void addAboutCCAEditorMenuItem(JMenu aboutMenu, JMenuItem aboutCCAEditorMenuItem) {
		ccaUtils.setMenuItemFontSize(aboutCCAEditorMenuItem);
		aboutMenu.add(aboutCCAEditorMenuItem);
	}
}

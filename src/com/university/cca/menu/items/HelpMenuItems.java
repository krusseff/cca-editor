package com.university.cca.menu.items;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.university.cca.util.CCAUtils;

/**
 * Help Menu Items of CCA Editor application
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class HelpMenuItems {
	
	private CCAUtils ccaUtils = new CCAUtils();

	public void addFAQMenuItem(JMenu helpMenu, JMenuItem faqMenuItem) {
		ccaUtils.setMenuItemFontSize(faqMenuItem);
		helpMenu.add(faqMenuItem);
	}
}

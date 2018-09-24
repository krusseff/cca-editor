package com.university.cca.menu;

import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import com.university.cca.constants.Constants;

/**
 * Main Menu of CCA Editor application
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class MainMenu {
	
	public void createMainMenu(JMenu file, JMenu edit, JMenu view,
							   JMenu help, JMenu about, JMenuBar jMenuBar) {
		// File menu element
		setMenuElementVisibility(file, true);
		setMenuElementLayout(file, null);
		setMenuElementLocation(file, 0, 0);
		setMenuElementSize(file, 60, 17);
		setMenuElementFont(file, Constants.FONT_ARIAL, Font.BOLD, 16);
		addElementToMainMenu(file, jMenuBar);
		
		// Edit menu element
		setMenuElementVisibility(edit, true);
		setMenuElementLayout(edit, null);
		setMenuElementLocation(edit, 65, 0);
		setMenuElementSize(edit, 60, 17);
		setMenuElementFont(edit, Constants.FONT_ARIAL, Font.BOLD, 16);
		addElementToMainMenu(edit, jMenuBar);
		
		// View menu element
		setMenuElementVisibility(view, true);
		setMenuElementLayout(view, null);
		setMenuElementLocation(view, 130, 0);
		setMenuElementSize(view, 60, 17);
		setMenuElementFont(view, Constants.FONT_ARIAL, Font.BOLD, 16);
		addElementToMainMenu(view, jMenuBar);
		
		// Help menu element
		setMenuElementVisibility(help, true);
		setMenuElementLayout(help, null);
		setMenuElementLocation(help, 195, 0);
		setMenuElementSize(help, 60, 17);
		setMenuElementFont(help, Constants.FONT_ARIAL, Font.BOLD, 16);
		addElementToMainMenu(help, jMenuBar);
		
		// About menu element
		setMenuElementVisibility(about, true);
		setMenuElementLayout(about, null);
		setMenuElementLocation(about, 260, 0);
		setMenuElementSize(about, 60, 17);
		setMenuElementFont(about, Constants.FONT_ARIAL, Font.BOLD, 16);
		addElementToMainMenu(about, jMenuBar);
	}
	
	public void addElementToMainMenu(JMenu jMenuElement, JMenuBar jMenuBar) {
		jMenuBar.add(jMenuElement);
	}
	
	public void setMenuElementFont(JMenu jMenuElement, String fontName, int fontStyle, int fontSize) {
		Font menuElementFont = new Font(fontName, fontStyle, fontSize);
		jMenuElement.setFont(menuElementFont);
	}
	
	public void setMenuElementSize(JMenu jMenuElement, int width, int height) {
		jMenuElement.setSize(width, height);
	}
	
	public void setMenuElementLocation(JMenu jMenuElement, int x, int y) {
		jMenuElement.setLocation(x, y);
	}
	
	public void setMenuElementVisibility(JMenu jMenuElement, boolean isVisible) {
		jMenuElement.setVisible(true);
	}
	
	public void setMenuElementLayout(JMenu jMenuElement, LayoutManager layoutManager) {
		jMenuElement.setLayout(layoutManager);
	}
}

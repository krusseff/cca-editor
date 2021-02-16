package com.university.cca.util;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JMenuItem;

import com.university.cca.constants.Constants;

/**
 * Base utility class with utility methods for CCA Editor application
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CCAUtils {
	
	public CCAUtils() {
		// Constructor that prevents creating an object of type CCAUtils
	}
	
	public Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
	public void setMenuItemFontSize(JMenuItem jMenuItemElement) {
		Font jMenuItemFont = new Font(Constants.FONT_ARIAL, Font.BOLD, 14);
		jMenuItemElement.setFont(jMenuItemFont);
	}
}

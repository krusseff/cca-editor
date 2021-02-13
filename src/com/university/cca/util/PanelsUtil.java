package com.university.cca.util;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * Utility methods related to the UI panels of the application
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class PanelsUtil {
	
	private PanelsUtil() {
		// Prevent to create an object from this class
	}
	
	/**
	 * Set the Grid Layout of the panels of the application
	 */
	public static GridLayout getGridLayout(int rows, int cols) {
		GridLayout layout = new GridLayout(rows, cols);
        layout.setHgap(10);
        layout.setVgap(10);
        
        return layout;
	}
	
	/**
	 * Set the size of the panels of the application
	 */
	public static void setPanelSize(JPanel panel) {
		CCAUtils ccaUtils = new CCAUtils();
	    Dimension screenSize = ccaUtils.getScreenSize();
	    panel.setBounds(1, 20, screenSize.width / 2, ((screenSize.height / 2) - 30));
	}
}

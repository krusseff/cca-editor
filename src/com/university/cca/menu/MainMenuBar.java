package com.university.cca.menu;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 * Main Menu Bar of CCA Editor application
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class MainMenuBar {
	
	public void createMainMenuBar(JMenuBar mainMenuBar, JFrame mainFrame) {
		mainMenuBar.setVisible(true);
		mainMenuBar.setLayout(null);
		mainMenuBar.setBounds(1, 1, mainFrame.getWidth(), 20);
		
		// put the menu bar on the frame
		mainFrame.setJMenuBar(mainMenuBar);
	}
}

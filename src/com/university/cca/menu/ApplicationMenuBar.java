package com.university.cca.menu;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 * Application Main Menu Bar of CCA Editor application
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class ApplicationMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	
	private JFrame parentFrame;

	public ApplicationMenuBar(JFrame mainFrame) {
		this.parentFrame = mainFrame;

		// this.setLayout(null);
		// this.setBounds(1, 1, mainFrame.getWidth(), 20);
		
		// add menu options to menubar
		addElementsToMenu();
		
		// put the menu bar on the main frame
		// mainFrame.setJMenuBar(this);
		this.parentFrame.setJMenuBar(this);
	}
	
	private void addElementsToMenu() {
		this.add(new FileMenu());
		this.add(new EditMenu(parentFrame));
		this.add(new ViewMenu());       
		this.add(new HelpMenu());
		this.add(new AboutMenu());
	}

	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}

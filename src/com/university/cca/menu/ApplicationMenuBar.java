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

		addElementsToMenu();
		
		this.parentFrame.setJMenuBar(this);
	}
	
	private void addElementsToMenu() {
		this.add(new FileMenu(parentFrame));
		this.add(new EditMenu(parentFrame));
		this.add(new ViewMenu(parentFrame));       
		this.add(new HelpMenu(parentFrame));
		this.add(new AboutMenu(parentFrame));
	}

	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}

package com.university.cca.menu;

import javax.swing.JMenuBar;

import com.university.cca.frames.AppMainFrame;

/**
 * Application Main Menu Bar of the CCA Editor application
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class ApplicationMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	
	private AppMainFrame parentFrame;

	public ApplicationMenuBar(AppMainFrame mainFrame) {
		this.parentFrame = mainFrame;

		addElementsToMenu();
		
		this.parentFrame.setJMenuBar(this);
	}
	
	private void addElementsToMenu() {
		this.add(new FileMenu(getParentFrame()));
		this.add(new EditMenu(getParentFrame()));
		this.add(new ViewMenu(getParentFrame()));       
		this.add(new HelpMenu(getParentFrame()));
		this.add(new AboutMenu(getParentFrame()));
	}

	// Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

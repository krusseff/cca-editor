package com.university.cca.dialogs;

import javax.swing.JOptionPane;

import com.university.cca.frames.AppMainFrame;

public class UnderConstructionDialog {

	private AppMainFrame parentFrame;
	
	public UnderConstructionDialog(AppMainFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		String dialogContent = 
			"<html>" +
				"<h3 style=\"text-align: center;\">Coming Soon!</h3>" +
				"<p style=\"text-align: center;\">We are Still Working on it!</p>" +
			"</html>";
		
		JOptionPane.showMessageDialog(
			this.parentFrame,
			dialogContent, 
			"Under Construction Dialog",
            JOptionPane.WARNING_MESSAGE
		);
	}

	// Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

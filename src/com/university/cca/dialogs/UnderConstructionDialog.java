package com.university.cca.dialogs;

import javax.swing.JOptionPane;

import com.university.cca.frames.AppMainFrame;

public class UnderConstructionDialog {
	
	private static final String TITLE_DIALOG = "Under Construction Dialog";
	
	private AppMainFrame parentFrame;
	
	public UnderConstructionDialog(AppMainFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		String dialogContent = 
			"<html>" +
				"<h3 style=\"text-align: center;\">Coming Soon!</h3>" +
				"<p style=\"text-align: center;\">We are Still Working on it!</p>" +
			"</html>";
		
		JOptionPane.showMessageDialog(
			getParentFrame(),
			dialogContent, 
			TITLE_DIALOG,
            JOptionPane.WARNING_MESSAGE
		);
	}

	// Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

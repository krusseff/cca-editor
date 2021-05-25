package com.university.cca.dialogs;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UnderConstructionDialog {

	private JFrame parentFrame;
	
	public UnderConstructionDialog(JFrame parentFrame) {
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

	// Getters and Setters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

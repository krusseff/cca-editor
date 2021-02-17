package com.university.cca.dialogs;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UnderConstructionDialog {

	public UnderConstructionDialog(JFrame parentFrame) {
		JOptionPane.showMessageDialog(
			parentFrame,
			"Coming Soon! \n\n We are Still Working on it!", 
			"Under Construction Dialog",
            JOptionPane.WARNING_MESSAGE
		);
	}
}

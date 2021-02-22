package com.university.cca.dialogs;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.university.cca.buttons.CloseDialogButton;
import com.university.cca.constants.Constants;
import com.university.cca.util.CCAUtils;

public class TermsDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final String TITLE_DIALOG = "Terms and Conditions";
	private static final int HEIGHT_DIALOG = CCAUtils.getScreenSize().height / 2 + 150;
	private static final int WIDHT_DIALOG = 800;
	private static final String CONTENT_DIALOG = 
		"<html>" + 
			"<head>" + 
				"<style>" +
					"body {" +
						"margin: 10;" + 
						"padding: 0;" +
						"font-family: Verdana, sans-serif;" +
					"}" +
					"i {" +
						"color: red;" +
					"}" +
				"</style>" +
			"</head>" +
			"<body>" +
				"<h1 style=\"text-align: center;\"> Terms and Conditions </h1>" + 
					
				"<h3 style=\"text-align: center;\"> List of terms and conditions that you need to keep in mind while using the CCA Editor </h3>" +
					
				// TODO
			"</body>" +
		"</html>";
	
	public TermsDialog(JFrame parentFrame) {
		super(parentFrame, TITLE_DIALOG, true);
		
		addDialogContent();
	
		this.setSize(WIDHT_DIALOG, HEIGHT_DIALOG);
		this.setLocationRelativeTo(parentFrame);
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
	}
	
	private void addDialogContent() {
		JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        
		dialogPanel.add(createTextPanel());
		dialogPanel.add(createButtonPanel());

        this.getContentPane().add(dialogPanel);
	}
	
	private JScrollPane createTextPanel() {
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new FlowLayout());
		textPanel.add(createLabel(CONTENT_DIALOG));
		
		return new JScrollPane(textPanel);
	}
	
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		buttonPanel.setBackground(Constants.LIGHT_GREY);
		buttonPanel.add(new CloseDialogButton(this));
		
		return buttonPanel;
	}
	
	private JLabel createLabel(String labelName) {
		return new JLabel(labelName, JLabel.CENTER);
	}
}

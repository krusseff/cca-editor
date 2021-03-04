package com.university.cca.dialogs.menu.about;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.university.cca.buttons.CloseDialogButton;
import com.university.cca.constants.Constants;
import com.university.cca.util.CCAUtils;
import com.university.cca.util.MouseCursorUtil;

public class CCADialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final String TITLE_DIALOG = "Calculus of Context-Aware Ambients";
	private static final int HEIGHT_DIALOG = CCAUtils.getScreenSize().height / 2 + 150;
	private static final int WIDHT_DIALOG = 800;
	private static final String TEXT_PANE_CONTENT_TYPE = "text/html";
	private static final String CONTENT_DIALOG = 
		"<html>" + 
			"<head>" + 
				"<style>" +
					"body {" +
						"margin: 10;" + 
						"padding: 0;" +
						"font-family: Verdana, sans-serif;" +
					"}" +
					"div {" +
						"margin-top: 10;" + 
						"width: " + (WIDHT_DIALOG - 250) + "px;" +
						"font-style: italic;" +
						"word-wrap: break-word;" +
					"}" +
					"li {" +
		    			"margin-bottom: 10px;" +
		    		"}" +
					"u {" +
						"color: blue;" +
						"font-style: italic;" +
					"}" +
				"</style>" +
			"</head>" +
			"<body>" +
				"<h1 style=\"text-align: center;\"> Calculus of Context-Aware Ambients </h1>" + 
					
				"<h3 style=\"text-align: center;\"> In order to have a better understanding about CCA you can read the following paragraphs </h3>" +
	
				"<div>" + 
					"<p> The Calculus of Context-aware Ambients (in short - CCA) is a paradigm for modelling and verification of different kind of systems that are context-aware. </p>" +
				"</div>" +
					
				"<div>" + 
					"<p> This process calculus is built upon the calculus of different ambients and introduces new constructs to enable ambients and processes to be aware of the environment in which they are being executed. </p>" +
				"</div>" +
				
				"<div>" + 
					"<p> This results in a powerful calculus where both mobility and context-awareness are first-class citizens. </p>" +
				"</div>" +
				
				"<div>" + 
					"<p> There are not so trivial syntax and a formal semantics of the calculus. There's a proposal for a new theory of equivalence of processes, which allows the identification of systems that have the same context-aware behaviours. </p>" +
				"</div>" +
				
				"<div>" + 
					"<p> It's proved that CCA encodes the calculus, which is known to be a universal model of computation. </p>" +
				"</div>" +
					
				"<div>" + 
					"<p> Finally, CCA illustrated the pragmatics of the calculus through many examples and a real-world case study of a context-aware smart university, smart city and etc. </p>" +
				"</div>" +
				
				"<div>" + 
					"<p> References: </p>" +
				
					"<ul>" +
					  "<li>" +
					  	"<u>http://www.cse.dmu.ac.uk/~fsiewe/papers/sdarticle.pdf</u>" +
				  	  "</li>" +
						
					  "<li>" +
						"<u>https://people.idsia.ch//~ngo/_files/mrc04.pdf</u>" +
					  "</li>" +
						
					  "<li>" +
					    "For more references and information about the CCA paradigm, please contact the CCA Editor Team." +
					  "</li>" +
				  	"</ul>" + 
				"</div>" +
			"</body>" +
		"</html>";
	
	public CCADialog(JFrame parentFrame) {
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
		JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		textPanel.add(createTextPane(CONTENT_DIALOG));
		
		return new JScrollPane(textPanel);
	}
	
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		buttonPanel.setBackground(Constants.LIGHT_GREY);
		buttonPanel.add(new CloseDialogButton(this));
		
		return buttonPanel;
	}
	
	private JTextPane createTextPane(String textPaneContent) {
		JTextPane textPane = new JTextPane();
		textPane.setCursor(MouseCursorUtil.getMouseText());
		textPane.setContentType(TEXT_PANE_CONTENT_TYPE);
		textPane.setText(textPaneContent);
		textPane.setEditable(false);
		textPane.setOpaque(false);
		textPane.setBorder(null);
		
		return textPane;
	}
}

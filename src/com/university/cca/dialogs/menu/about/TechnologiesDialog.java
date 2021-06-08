package com.university.cca.dialogs.menu.about;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.university.cca.buttons.CloseDialogButton;
import com.university.cca.constants.Constants;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.CCAUtils;
import com.university.cca.util.MouseCursorUtil;

public class TechnologiesDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static final String TITLE_DIALOG = "Used Technologies";
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
				"<h1 style=\"text-align: center;\"> Used Technologies </h1>" + 
					
				"<h3 style=\"text-align: center;\"> A list with used technologies into the CCA Editor Application </h3>" +
					
				"<ul>" +
				  "<li>" +
				  	"Java 8: " + 
				  	"<u>https://www.oracle.com/java/technologies/java8.html</u>" +
			  	  "</li>" +
				  
				  "<li>" +
					"Java Swing and Java AWT: " + 
					"<u>https://docs.oracle.com/javase/tutorial/uiswing/</u>" +
				  "</li>" +
					
				  "<li>" +
				    "Java Mail API 1.6.2: " + 
				    "<u>https://javadoc.io/doc/javax.mail/javax.mail-api/latest/index.html</u>" +
				  "</li>" +
					
				  "<li>" +
					"Logback 1.2.3: " + 
					"<u>http://logback.qos.ch/</u>" +
				  "</li>" +
				  
				  "<li>" +
					"SLF4J 1.7.30: " + 
					"<u>http://www.slf4j.org/</u>" +
				  "</li>" +
				  
				  "<li>" +
					"OpenCSV 5.3: " + 
					"<u>http://opencsv.sourceforge.net</u>" +
				  "</li>" +
					
				  "<li>" +
					"Jackson 2.12.2: " + 
					"<u>https://en.wikipedia.org/wiki/Jackson_(API)</u>" +
				  "</li>" +
					
				  "<li>" +
					"JUnit 4.13.1: " + 
					"<u>https://junit.org/junit4</u>" +
				  "</li>" +
					
				  "<li>" +
					"Maven: " + 
					"<u>https://maven.apache.org/</u>" +
				  "</li>" +
					
				  "<li>" +
					"Eclipse: " + 
					"<u>https://www.eclipse.org/</u>" +
				  "</li>" +
					
				  "<li>" +
					"SonarLint: " + 
					"<u>https://www.sonarlint.org/</u>" +
				  "</li>" +
					
				  "<li>" +
					"Calculus of Context-Aware paradigm" + 
				  "</li>" +
					
				  "<li>" +
					"Calculus of Context-Aware Modelling Language: <b><i>ccaPL</i></b>" + 
				  "</li>" +
			  	"</ul>" + 
			"</body>" +
		"</html>";
	
	public TechnologiesDialog(AppMainFrame parentFrame) {
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
		textPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
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

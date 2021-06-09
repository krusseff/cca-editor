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

public class LinksDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private static final String TITLE_DIALOG = "Useful Links";
	private static final int HEIGHT_DIALOG = CCAUtils.getScreenSize().height / 2 + 150;
	private static final int WIDHT_DIALOG = 800;
	private static final String TEXT_PANE_CONTENT_TYPE = "text/html; charset=UTF-8";
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
				    	"margin-bottom: 15px;" +
					"}" +
					"u {" +
						"color: blue;" +
						"font-style: italic;" +
					"}" +
				"</style>" +
			"</head>" +
			"<body>" +
				"<h1 style=\"text-align: center;\"> Useful Links </h1>" + 
					
				"<h3 style=\"text-align: center;\"> A list with useful links that you can use for better understanding </h3>" +
					
				"<ul>" +
				  "<li>" +
				  	"Distributed eLerning Center (DeLC): " + 
				  	"<u> http://delc.fmi.uni-plovdiv.net </u>" +
			  	  "</li>" +
				  
				  "<li>" +
					"Distributed eLerning Center 2 (DeLC2): " + 
					"<u> https://delc2.fmi.uni-plovdiv.net </u>" +
				  "</li>" +
					
				  "<li>" +
					"Distributed eLerning Center Science (DeLC Science): " + 
					"<u> http://delc.space </u>" +
				  "</li>" +
				  
				  "<li>" +
					"Faculty at Mathematics and Informatics at Plovdiv University: " + 
					"<u> http://fmi-plovdiv.org </u>" +
				  "</li>" +
				  
				  "<li>" +
					"Calculus of Context-Aware Editor (CCA Editor): " + 
					"<u> http://delc.space/projects/cca-editor </u>" +
				  "</li>" +
				  
				  "<li>" +
					"CCA Editor Code Base: " + 
					"<u> https://github.com/krusseff/cca-editor </u>" +
				  "</li>" +
					
				  "<li>" +
					"CCA Editor Developer: " + 
					"<u> http://delc.space/team/докторант-константин-русев/ </u>" +
				  "</li>" +
					
				  "<li>" +
					"CCA Editor Supervisor: " + 
					"<u> http://delc.space/team/проф-стоянов/ </u>" +
				  "</li>" +
			  	"</ul>" + 
			"</body>" +
		"</html>";

	public LinksDialog(AppMainFrame parentFrame) {
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

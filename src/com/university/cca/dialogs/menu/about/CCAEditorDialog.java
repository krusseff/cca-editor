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

public class CCAEditorDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private static final String TITLE_DIALOG = "About CCA Editor";
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
					"i {" +
						"color: red;" +
					"}" +
				"</style>" +
			"</head>" +
			"<body>" +
				"<h1 style=\"text-align: center;\"> More about the CCA Editor </h1>" + 
					
				"<h3 style=\"text-align: center;\"> In order to have a better understanding about CCA Editor read the following paragraphs </h3>" +
	
				"<div>" + 
					"<p> The Calculus of Context-aware Ambients (CCA) Editor is a software and paradigm through which any object of the real world can be modeled. </p>" +
				"</div>" +
					
				"<div>" + 
					"<p> Each object is presented and described as a kind of an <i>ambient</i>, as part of an Ambient-oriented model. </p>" +
				"</div>" +
				
				"<div>" + 
					"<p> The description of each ambient (an object from the real world, for example bus station, hotel, hospital and etc.) is done by using the formal <i>ccaPL language</i>. It provides the possiblity to model different environments like smart cities, smart education, smart agriculture and so on. </p>" +
				"</div>" +
				
				"<div>" + 
					"<p> On the other hand, the CCA Editor provides a graphical environment to model these environments and ambients with providing different kind of predefind ambients (objects from the real world), depending from the use cases and the environments that we want to model. With the editor, it also possible to, model the communication, create and send messages from one ambient to another. And in addition to that, it's also possible to implement the movements, between the different ambients - yes, we can have ambients that can move from one ambient to another and we call them - <i>dynamic ambients</i>. We can also describe and model other two types of ambients - <i>static</i> and <i>non-static</i>. They are not able to make that kind of movements from one ambient to another as part of the current model of the smart environment. </p>" +
				"</div>" +
				
				"<div>" + 
					"<p> The exact generation of the ccaPL source code remains hidden from the end-user of the CCA Editor application (we can call them 'modeller'). They facilitates their work, and also they do not need any additional knowledge about the ccaPL formal language to construct a conceptual model. They just need to be familiar with the CCA Editor and how to work with it, effectively. </p>" +
				"</div>" +
				
				"<div>" + 
					"<p> You can find more information about the CCA Editor and the team that works on it on the following web pages. </p>" +
				
					"<ul>" +
					  "<li>" +
					  	"CCA Editor: " +
					  	"<u> http://delc.space/projects/cca-editor/ </u>" +
				  	  "</li>" +
						
					  "<li>" +
						"Supervisors: " + 
						"<u> http://delc.space/team/ </u>" +
					  "</li>" +
					  
					  "<li>" +
					    "Developers: " + 
					    "<u> http://delc.space/team/ </u>" +
					  "</li>" +
				  	"</ul>" + 
				"</div>" +
			"</body>" +
		"</html>";
	
	public CCAEditorDialog(AppMainFrame parentFrame) {
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
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
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

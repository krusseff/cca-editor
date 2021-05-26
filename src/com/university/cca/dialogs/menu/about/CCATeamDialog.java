package com.university.cca.dialogs.menu.about;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;

import com.university.cca.buttons.CloseDialogButton;
import com.university.cca.constants.Constants;
import com.university.cca.util.CCAUtils;
import com.university.cca.util.MouseCursorUtil;

public class CCATeamDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final String TITLE_DIALOG = "About CCA Editor Team";
	private static final String CCA_TEAM_TITLE = "<h1 style=\"text-align: center;\"> More About the CCA Editor Team </h1>";
	private static final int HEIGHT_DIALOG = CCAUtils.getScreenSize().height / 2 + 150;
	private static final int WIDHT_DIALOG = 800;
	private static final String TEXT_PANE_CONTENT_TYPE = "text/html";
	private static final String STOYANOV_INFO = 
		"<html>" + 
			"<head>" + 
				"<style>" +
					"body {" +
						"margin: 0;" + 
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
				"<h2 style=\"text-align: center;\"> Professor Ph.D. Stanimir Stoyanov </h2>" + 
					
				"<ul>" +
				  "<li>" +
				  	"Director and Supervisor of the CCA Editor Project and DeLC Lab" +
			  	  "</li>" +
					
				  "<li>" +
					"Web page: " + 
					"<u> http://web.uni-plovdiv.bg/stoyanov/ </u>" +
				  "</li>" +
				  
				  "<li>" +
					"Email: " + 
					"<u> stani@uni-plovdiv.net </u>" +
				  "</li>" +
					
				  "<li>" +
				    "More information: " + 
				    "<u> http://delc.space/team/ </u>" +
				  "</li>" +
			  	"</ul>" + 
			"</body>" +
		"</html>";
	
	private static final String GLUSHKOVA_INFO = 
		"<html>" + 
			"<head>" + 
				"<style>" +
					"body {" +
						"margin: 0;" + 
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
				"<h2 style=\"text-align: center;\"> Associate Professor Ph.D. Todorka Glushkova </h2>" + 
					
				"<ul>" +
				  "<li>" +
				  	"Direct Supervisor of the CCA Editor Project" +
			  	  "</li>" +
					
				  "<li>" +
					"Web page: " + 
					"<u> https://bg.linkedin.com/in/todorka-glushkova-27092a42 </u>" +
				  "</li>" +
				  
				  "<li>" +
					"Email: " + 
					"<u> glushkova@uni-plovdiv.bg </u>" +
				  "</li>" +
			  	"</ul>" + 
			"</body>" +
		"</html>";
	
	private static final String RUSEV_INFO = 
		"<html>" + 
			"<head>" + 
				"<style>" +
					"body {" +
						"margin: 0;" + 
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
				"<h2 style=\"text-align: center;\"> Ph.D. Candidate Konstantin Rusev </h2>" + 
					
				"<ul>" +
				  "<li>" +
				  	"Developer of the CCA Editor Project" +
			  	  "</li>" +
					
				  "<li>" +
					"Web page: " + 
					"<u> https://www.linkedin.com/in/konstantin-rusev-26ab87b7 </u>" +
				  "</li>" +
				  
				  "<li>" +
					"Email: " + 
					"<u> k.russeff@uni-plovdiv.bg </u>" +
				  "</li>" +
					
				  "<li>" +
				    "More information: " + 
				    "<u> http://delc.space/team/ </u>" +
				  "</li>" +
			  	"</ul>" + 
			"</body>" +
		"</html>";
	
	public CCATeamDialog(JFrame parentFrame) {
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
        
		dialogPanel.add(createContentPanel());
		dialogPanel.add(createButtonPanel());

        this.getContentPane().add(dialogPanel);
	}
	
	private JScrollPane createContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel headerPanel = new JPanel(new FlowLayout());
		headerPanel.add(createTextPane(CCA_TEAM_TITLE));
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.add(createPersonPanel(Constants.STOYANOV_ICON_PATH, STOYANOV_INFO));
		contentPanel.add(new JSeparator());
		contentPanel.add(createPersonPanel(Constants.GLUSHKOVA_ICON_PATH, GLUSHKOVA_INFO));
		contentPanel.add(new JSeparator());
		contentPanel.add(createPersonPanel(Constants.RUSEV_ICON_PATH, RUSEV_INFO));
		
		panel.add(headerPanel);
		panel.add(contentPanel);
		
		return new JScrollPane(panel);
	}
	
	private JPanel createPersonPanel(String personImagePath, String personInformation) {
		JPanel personPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
		personPanel.add(new JLabel(new ImageIcon(personImagePath)));
		personPanel.add(createTextPane(personInformation));
		
		return personPanel;
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
	
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		buttonPanel.setBackground(Constants.LIGHT_GREY);
		
		buttonPanel.add(new CloseDialogButton(this));
		
		return buttonPanel;
	}
}

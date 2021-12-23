package com.university.cca.dialogs.menu.help;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.university.cca.buttons.CloseDialogButton;
import com.university.cca.constants.Constants;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.CCAUtils;

public class FAQDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private static final String TITLE_DIALOG = "Frequently Asked Questions";
	private static final int HEIGHT_DIALOG = CCAUtils.getScreenSize().height / 2 + 150;
	private static final int WIDHT_DIALOG  = 800;
	
	private static final String CONTENT_DIALOG = 
		"<html>" + 
			"<head>" + 
				"<style>" +
					"body {" +
						"margin: 10;" + 
						"padding: 0;" +
						"font-family: Verdana, sans-serif;" +
					"}" +
					"ul {" +
				    	"margin-bottom: 10px;" +
					"}" +
					"i {" +
						"color: red;" +
					"}" +
				"</style>" +
			"</head>" +
			"<body>" +
				"<h1 style=\"text-align: center;\"> Frequently Asked Questions </h1>" + 
					
				"<h3 style=\"text-align: center;\"> List of questions that are frequently asked that you can use in order to have better usability </h3>" +
					
				"<ol>" +
				  "<li> How can I close the application? " +
	    			  "<ul>" +
				      	"<li> To close the application open the File menu and click on <i>'Exit'</i> or press <i>'CTRL + E'</i> </li>" +
				      "</ul>" +
			      "</li>" +

				  "<li> How can I open the terminal in the application? " +
				  	"<ul>" +
				  	  "<li> To open the terminal open the File menu and click on <i>'Open Terminal'</i> or press <i>'CTRL + T'</i> </li>" +
				  	"</ul>" +
				  "</li>" +
				  
				  "<li> How can I restart the application? " +
					"<ul>" +
					  "<li> To restart the application open the File menu and click on <i>'Restart'</i> or press <i>'CTRL + L'</i> </li>" +
					"</ul>" +
				  "</li>" +

				  "<li> How can I refresh the application? " +
				  	"<ul>" +
				  	  "<li> To refresh the application open the File menu and click on <i>'Refresh'</i> or press <i>'CTRL + R'</i> </li>" +
				  	"</ul>" +
				  "</li>" +
				  
				  "<li> How can I create a CCA model in the application? " +
					"<ul>" +
					  "<li> To create a CCA model just start the application and create some ambients from the left menu </li>" +
					"</ul>" +
				  "</li>" +
					
				  "<li> How can I create a CCA ambient in the application? " +
					"<ul>" +
					  "<li> To create a CCA ambient navigate to the left menu, choose ambient type and click on the button </li>" +
					"</ul>" +
				  "</li>" +
				  
				  "<li> How can I send a message between two ambients in the application? " +
					"<ul>" +
					  "<li> To send a message click on the <i>'Create message'</i> button and enter needed information </li>" +
					"</ul>" +
				  "</li>" +				  
			  	
				  "<li> How can I see the shortcuts in the application? " +
					"<ul>" +
					  "<li> Navigate to the <i>Help</i> menu and then click on <i>'" + Constants.HELP_ITEM_SHORTCUTS + "'</i> </li>" +
					"</ul>" +
				  "</li>" +		
					
				  "<li> How can I see the terms and conditions in the application? " +
					"<ul>" +
					  "<li> Navigate to the <i>Help</i> menu and then click on <i>'" + Constants.HELP_ITEM_TERMS + "'</i> </li>" +
					"</ul>" +
				  "</li>" +	
					
				  "<li> How can I see the frequently asked questions in the application? " +
					"<ul>" +
					  "<li> Navigate to the <i>Help</i> menu and then click on <i>'" + Constants.HELP_ITEM_FAQ + "'</i> </li>" +
					"</ul>" +
				  "</li>" +	

				  "<li> Where I can find the CCA file with all ambients and messages between them? " +
					"<ul>" +
					  "<li> Navigate to the root folder of the app - <i>CCAEditor</i> and then open the <i>'" + Constants.AMBIENTS_CCA_FILE_PATH + "'</i> </li>" +
					"</ul>" +
				  "</li>" +		
					
				  "<li> Where I can find the CSV file with all ambients? " +
					"<ul>" +
					  "<li> Navigate to the root folder of the app - <i>CCAEditor</i> and then open the <i>'" + Constants.AMBIENTS_CSV_FILE_PATH + "'</i> </li>" +
					"</ul>" +
				  "</li>" +	
			  	
				  "<li> Where I can find the CSV file with all messages between the ambients? " +
					"<ul>" +
					  "<li> Navigate to the root folder of the app - <i>CCAEditor</i> and then open the <i>'" + Constants.MESSAGES_CSV_FILE_PATH + "'</i> </li>" +
					"</ul>" +
				  "</li>" +			
					
				  "<li> How can I see more information for the application? " +
					"<ul>" +
					  "<li> Navigate to the <i>About</i> menu and then click on the specific sub-menu item </li>" +
					"</ul>" +
				  "</li>" +	
			  	
				  "<li> How can I see created ambients and messages between them? " +
					"<ul>" +
					  "<li> Navigate to the <i>View</i> menu and then click on the specific sub-menu item </li>" +
					"</ul>" +
				  "</li>" +				  
			  	
			  	"</ol>" + 
			"</body>" +
		"</html>";

	public FAQDialog(AppMainFrame parentFrame) {
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

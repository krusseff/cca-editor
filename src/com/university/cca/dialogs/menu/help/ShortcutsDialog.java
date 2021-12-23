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

public class ShortcutsDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static final String TITLE_DIALOG = "Tips and Tricks";
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
					"li {" +
				    	"margin-bottom: 10px;" +
					"}" +
					"i {" +
						"color: red;" +
					"}" +
				"</style>" +
			"</head>" +
			"<body>" +
				"<h1 style=\"text-align: center;\"> Tips and Tricks - Shortcuts </h1>" + 
					
				"<h3 style=\"text-align: center;\"> List with shortcuts that you can use in order to have better usability </h3>" +
					
				"<ul>" +
				  "<li> To close the application open the File menu and click on <i>'Exit'</i> or press <i>'CTRL + E'</i> </li>" +
				  "<li> To open the terminal open the File menu and click on <i>'Open Terminal'</i> or press <i>'CTRL + T'</i> </li>" + 
				  "<li> To import files open the File menu and click on <i>'Import'</i> or press <i>'CTRL + I'</i> </li>" +
				  "<li> To export files open the File menu and click on <i>'Export'</i> or press <i>'CTRL + X'</i> </li>" +
				  "<li> To restart the application open the File menu and click on <i>'Restart'</i> or press <i>'CTRL + L'</i> </li>" +
				  "<li> To refresh the application open the File menu and click on <i>'Refresh'</i> or press <i>'CTRL + R'</i> </li>" +
				  "<li> To save something in the application open the File menu and click on <i>'Save'</i> or press <i>'CTRL + S'</i> </li>" +
				  "<li> To open something in the application open the File menu and click on <i>'Open'</i> or press <i>'CTRL + O'</i> </li>" +
				  "<li> To create new CCA model open the File menu and click on <i>'New'</i> or press <i>'CTRL + N'</i> </li>" +
				  "<li> To cut something in the application open the Edit menu and click on <i>'Cut'</i> or press <i>'CTRL + U'</i> </li>" +
				  "<li> To copy something in the application open the Edit menu and click on <i>'Copy'</i> or press <i>'CTRL + C'</i> </li>" +
				  "<li> To paste something in the application open the Edit menu and click on <i>'Paste'</i> or press <i>'CTRL + P'</i> </li>" +
				  "<li> To delete something in the application open the Edit menu and click on <i>'Delete'</i> or press <i>'CTRL + D'</i> </li>" +
				  "<li> To see details of the CCA open About menu and click on <i>'About CCA'</i> or press <i>'CTRL + 0'</i> </li>" +
				  "<li> To see details of the CCA Editor open About menu and click on <i>'About CCA Editor'</i> or press <i>'CTRL + 1'</i> </li>" +
				  "<li> To see details of the CCA Team open About menu and click on <i>'CCA Editor Team'</i> or press <i>'CTRL + 2'</i> </li>" +
				  "<li> To see details of some links open the About menu and click <i>'Useful Links'</i> or press <i>'CTRL + 3'</i> </li>" +
				  "<li> To see details of the technologies open the About menu and click <i>'Technologies'</i> or press <i>'CTRL + 4'</i> </li>" +
				  "<li> To contact the team of the CCA Editor open the About menu and click <i>'Contact Us'</i> or press <i>'CTRL + 5'</i> </li>" +
				  "<li> To see all ambients in CSV open the View menu and click <i>'Show Ambients CSV File'</i> or press <i>'CTRL + 6'</i> </li>" +
				  "<li> To see all messages in CSV open the View menu and click <i>'Show Messages CSV File'</i> or press <i>'CTRL + 7'</i> </li>" +
				  "<li> To see all ambients open the View menu and click <i>'Show Ambients'</i> or press <i>'CTRL + 8'</i> </li>" +
				  "<li> To see all ambient messages open the View menu and click <i>'Show Messages'</i> or press <i>'CTRL + 9'</i> </li>" +
				  "<li> To see all ambients in CCA open the View menu and click <i>'Show Ambients CCA File'</i> or press <i>'CTRL + A'</i> </li>" +
				  "<li> To see ambients stats open the View menu and click <i>'Show Ambients Statistics'</i> or press <i>'CTRL + ALT + S'</i> </li>" +
				  "<li> To see messages stats open the View menu and click <i>'Show Messages Statistics'</i> or press <i>'CTRL + SHIFT + S'</i> </li>" +
			  	"</ul>" + 
			"</body>" +
		"</html>";
	
	public ShortcutsDialog(AppMainFrame parentFrame) {
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

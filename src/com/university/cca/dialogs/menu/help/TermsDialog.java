package com.university.cca.dialogs.menu.help;

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
					"div {" +
						"margin-top: 20;" + 
						"width: " + (WIDHT_DIALOG - 250) + "px;" +
						"font-style: italic;" +
						"word-wrap: break-word;" +
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
				"<h1 style=\"text-align: center;\"> Terms and Conditions </h1>" + 
					
				"<h3 style=\"text-align: center;\"> List of terms and conditions that you need to keep in mind while using the CCA Editor </h3>" +

				"<div>" + 
					"<p> By opening the CCA Editor you agree to be bound by and to abide by the following terms and conditions. </p>" +
				"</div>" +
					
				"<div>" + 
					"<p> These terms may be amended or modified, or new conditions may be imposed, at any time. Any such changes or additions will be reflected by an update of this page. Please check these terms periodically for changes. Your continued use of the CCA Editor following the posting of changes to these terms will mean you accept those changes and that such changes shall apply to your use of the editor after such changes have been posted. </p>" +
				"</div>" +
					
				"<div>" + 
					"<p> The developers of the CCA Editor are part of the Plovdiv University, a university registered in Bulgaria, whose registered office is at 24 Tsar Asen Street, Plovdiv, 4000. </p>" +
				"</div>" +
					
				"<div>" + 
					"<p> We will only share the data that you entered into the application in accordance with our <i>Privacy Policy</i> and as set out in these terms. </p>" +
				"</div>" +
				
				"<div>" + 
					"<p> You warrant and agree that, while participating in the CCA Editor, you shall not upload, post or transmit to or distribute or otherwise publish through the editor or in relation to the editor any information, content or materials that: </p>" +
					
					"<ul>" +
					  "<li> are protected by copyright, or other proprietary or intellectual property right, or derivative works with respect thereto, except as provided herein or without first obtaining permission from us or the copyright owner; </li>" +
					  "<li> are unlawful, threatening, harassing, profane, tortious, defamatory, discriminatory, vulgar, obscene, libellous, deceptive, fraudulent, contains explicit or graphic descriptions or accounts of sexual acts (including, but not limited to, sexual language of a violent or threatening nature directed at another individual or group of individuals), invasive of another's privacy, or hateful; </li>" +
					  "<li> restrict or inhibit any other user from using and enjoying the CCA Editor, </li>" +
					  "<li> constitute or encourage conduct that would constitute a criminal offence or give rise to civil liability, or </li>" +
					  "<li> contain a virus or other harmful component or similar computer code designed to adversely affect the operation of any computer software or hardware, advertising of any kind, or false or misleading indications of origin or statements of fact. </li>" +
				  	"</ul>" + 
				"</div>" +
				
				"<div>" + 
					"<p> You also warrant and agree that you shall not: </p>" +
					
					"<ul>" +
					  "<li> impersonate, or misrepresent your affiliation with any other person or entity; </li>" +
					  "<li> upload, post, publish, transmit, reproduce, distribute or in any way exploit any information or other material obtained through the CCA Editor for commercial purposes (other than as expressly permitted by the provider of such information or other material); </li>" +
					  "<li> engage in spamming or flooding; </li>" +
					  "<li> attempt to gain unauthorised access to other computer systems through the CCA Editor; or </li>" +
					  "<li> bring the CCA Editor into disrepute. </li>" +
				  	"</ul>" + 
			  	"</div>" +
				  	
				"<div>" + 
					"<p> Except as otherwise expressly permitted herein, you may not upload, post, publish, reproduce, transmit or distribute in any way any component of the CCA Editor itself or derivative works with respect thereto, as the CCA Editor is copyrighted as a collective work under the copyright laws of Bulgaria. </p>" +
				"</div>" +
					
				"<div>" + 
					"<p> The extent of the CCA Editor's responsibility to you has been determined in the context of the following: </p>" +
					
					"<ul>" +
					  "<li> the CCA Editor is provided to you free of charge and on an <i>as is</i> basis; </li>" +
					  "<li> you are responsible for any action you do or do not take as a result of the editor and the information therein; </li>" +
					  "<li> you are responsible for ensuring that your equipment is enabled with appropriate up-to-date virus checking software. </li>" +
				  	"</ul>" + 
			  	"</div>" +
				  	
				"<div>" + 
					"<p> While the CCA Editor will endeavour to ensure that the editor is available to you at all times, without faults and that the contents are correct and accurate, it cannot make any legal commitment to you that this will be the case. </p>" +
				"</div>" +
					
				"<div>" + 
					"<p> Nothing in these terms limits or excludes CCA Editor's liability for death or personal injury caused by our negligence; liability arising from fraud or fraudulent misrepresentation; or any other reason or by any cause which cannot be excluded by law. </p>" +
				"</div>" +	
					
				"<div>" + 
					"<p> Any breach by you of the Editor Rules from time to time shall also be a breach of these terms. </p>" +
				"</div>" +	
				
				"<div>" + 
					"<p> In the event of any breach by you of these terms the CCA Editor Team reserves the right in its sole discretion to: </p>" +
				
					"<ul>" +
					  "<li> permanently or temporarily refuse you entry to the CCA Editor; </li>" +
					  "<li> delete all related information associated with your participation into the editor; </li>" +
				  	"</ul>" + 					
				"</div>" +
				  	
				"<div>" + 
					"<p> All such decisions will be final and no correspondence will be entered into. </p>" +
				"</div>" +	
					
				"<div>" + 
					"<p> If any of these terms are determined to be illegal, invalid, or otherwise enforceable then the remaining terms shall continue in full force and effect. </p>" +
				"</div>" +	
				
				"<div>" + 
					"<p> These terms shall be governed by and construed in accordance with the laws of Bulgaria and the parties agree to submit to the exclusive jurisdiction of the Courts of Bulgaria. </p>" +
				"</div>" +	
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

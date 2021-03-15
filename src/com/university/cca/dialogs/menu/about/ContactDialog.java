package com.university.cca.dialogs.menu.about;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.email.EmailSender;
import com.university.cca.util.ContactDialogUtil;
import com.university.cca.util.MouseCursorUtil;

public class ContactDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ContactDialog.class);
	private static final String TITLE_DIALOG = "Contact Us";
	private static final String TEXT_PANE_CONTENT_TYPE = "text/html";
	private static final String CONTACT_US_TITLE = "<h1 style=\"text-align: center;\"> Contact Us </h1>";
	private static final String CONTACT_US_SUBTITLE = "<h2 style=\"text-align: center;\"> Got a question? <br/> Send us a message and we'll respond as soon as possible. </h2>";
	
	private static final int TEXT_FIELD_SIZE = 16;
	private static final int TEXT_AREA_COLS = 16;
	private static final int TEXT_AREA_ROWS = 8;
	
	private JTextField nameTextField;
	private JTextField emailTextField;
	private JTextArea messageTextArea;
	
	public ContactDialog(JFrame parentFrame) {
		super(parentFrame, TITLE_DIALOG, true);
		
		addDialogContent();

		this.pack();
		this.setLocationRelativeTo(parentFrame);
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
	}
	
	private void addDialogContent() {
		JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createTitledBorder(TITLE_DIALOG));
        
        mainPanel.add(createTitlePanel());
        mainPanel.add(createSubTitlePanel());
        mainPanel.add(createNamePanel());
        mainPanel.add(createEmptySpace());
        mainPanel.add(createEmailPanel());
        mainPanel.add(createEmptySpace());
        mainPanel.add(createMessagePanel());
        mainPanel.add(createEmptySpace());
        mainPanel.add(createSendButtonPanel());
        
        this.getContentPane().add(mainPanel);
	}
	
	private JPanel createTitlePanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.add(createTextPane(CONTACT_US_TITLE));
		
		return panel;
	}
	
	private JPanel createSubTitlePanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.add(createTextPane(CONTACT_US_SUBTITLE));
		
		return panel;
	}
	
	private JPanel createNamePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		String nameLabel = "<p style=\"margin-left: 5px;\"> Name* </p>";
		nameTextField = new JTextField(TEXT_FIELD_SIZE);
		
		panel.add(createTextPane(nameLabel));
		panel.add(nameTextField);
		
		return panel;
	}
	
	private JPanel createEmailPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		String emailLabel = "<p style=\"margin-left: 5px;\"> Email* </p>";
		emailTextField = new JTextField(TEXT_FIELD_SIZE);
		
		panel.add(createTextPane(emailLabel));
		panel.add(emailTextField);
		
		return panel;
	}
	
	private JPanel createMessagePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		String messageLabel = "<p style=\"margin-left: 5px;\"> Message* </p>";
		messageTextArea = createTextArea();
		
		panel.add(createTextPane(messageLabel));
		panel.add(new JScrollPane(messageTextArea));
		
		return panel;
	}
	
	private JPanel createSendButtonPanel() {
		JPanel panel = new JPanel();
		
		JButton sendMessageButton = createSendButton();		
		sendMessageButton.addActionListener(event -> {
			logger.info("Send Message Button into the Contact Us form is clicked");
    
			String name = nameTextField.getText().trim();
			String email = emailTextField.getText().trim();
			String message = messageTextArea.getText().trim();
			
			int emailResult = EmailSender.send(name, email, message);
			ContactDialogUtil.showEmailSentDialog(this, emailResult);
		});
		
        panel.add(sendMessageButton, BorderLayout.CENTER);
        
		return panel;
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
	
	private JTextArea createTextArea() {
		JTextArea textArea = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLS);
		textArea.setToolTipText("Message that you want to send to us");
		textArea.setWrapStyleWord(true);
		
		return textArea;
	}
	
	private JButton createSendButton() {
		JButton sendMessageButton = new JButton();
		
		sendMessageButton.setIcon(new ImageIcon(Constants.SEND_ICON_PATH));
		sendMessageButton.setText("Send Message");
		sendMessageButton.setToolTipText("Click to send us a message");
		sendMessageButton.setCursor(MouseCursorUtil.getMouseHand());
		sendMessageButton.setIconTextGap(Constants.ICON_GAP_SIZE);
		sendMessageButton.setPreferredSize(new Dimension(200, 35));
		sendMessageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		sendMessageButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		return sendMessageButton;
	}
	
	private Component createEmptySpace() {
		return Box.createRigidArea(new Dimension(0, 15));
	}
}

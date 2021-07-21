package com.university.cca.dialogs;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.buttons.CancelDialogButton;
import com.university.cca.entities.Message;
import com.university.cca.files.csv.AmbientCSVWriter;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.repositories.AmbientRepository;
import com.university.cca.repositories.MessageRepository;
import com.university.cca.util.CreateMessageUtil;
import com.university.cca.util.MouseCursorUtil;

public class CreateAmbientMsgDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CreateAmbientMsgDialog.class);
	
	private static final String TITLE_DIALOG = "Create and Send Message";
	private static final int TEXT_AREA_COLS = 25;
	private static final int TEXT_AREA_ROWS = 2;
	private static final int GRID_ROWS = 5;
	private static final int GRID_COLS = 2;
	
	private JComboBox<String> senderAmbientsComboBox;
	private JComboBox<String> recipientAmbientsComboBox;
	private JComboBox<String> respondToComboBox;
	private JTextArea ambientMessageTextArea;
	
	public CreateAmbientMsgDialog(AppMainFrame parent) {
		super(parent, TITLE_DIALOG, true);
		
		addDialogContent();
	
		this.pack();
		this.setLocationRelativeTo(parent);

		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
	}

	private void addDialogContent() {
		JPanel contentPanel = new JPanel();
        contentPanel.setLayout(getGridLayout());
        
        // first row of the dialog
        this.senderAmbientsComboBox = createAmbientComboBox();
        contentPanel.add(createLabel("Sender Ambient Name: "));
        contentPanel.add(this.senderAmbientsComboBox);
        
        // second row of the dialog
        this.recipientAmbientsComboBox = createAmbientComboBox();
        contentPanel.add(createLabel("Recipient Ambient Name: "));
        contentPanel.add(this.recipientAmbientsComboBox);
        
        // third row of the dialog
        this.respondToComboBox = createRespondToComboBox();
        contentPanel.add(createLabel("Respond To: "));
        contentPanel.add(this.respondToComboBox);

        // fourth row of the dialog
        this.ambientMessageTextArea = createTextArea();
        contentPanel.add(createLabel("Ambient Message: "));
        contentPanel.add(new JScrollPane(this.ambientMessageTextArea));
        
        // fifth row of the dialog
        JButton createMessageButton = CreateMessageUtil.createMessageButton();
        contentPanel.add(createMessageButton);
        contentPanel.add(new CancelDialogButton(this));
        
        createMessageButton.addActionListener(event -> {
        	logger.info("Create Ambient Message Button is clicked");
    		createMessage();
        });
        
        this.getContentPane().add(contentPanel);
	}
	
	/**
	 * Method that validates and creates a message if the enterred data is valid.
	 */
	private void createMessage() {
		Object senderAmbient = this.senderAmbientsComboBox.getSelectedItem();
		Object recipientAmbient = this.recipientAmbientsComboBox.getSelectedItem();
		Object respondToMessage = this.respondToComboBox.getSelectedItem();
		String ambientMessage = this.ambientMessageTextArea.getText().replace("\n", " ").replace("\r", " ").trim();
		
		if (!CreateMessageUtil.isValidMessageInfo(senderAmbient, recipientAmbient, respondToMessage, ambientMessage)) {
			logger.info("Tried to create an ambient message with invalid values");
			String errorMsg = "Please, enter valid values for the input fields!";
			CreateMessageUtil.createErrorDialog(getCurrentDialog(), errorMsg);
		} else if (!CreateMessageUtil.isValidMessageLength(ambientMessage)) {
			logger.info("Tried to create an ambient message, which is lower than 0 or greater than 500");
			String errorMsg = "The message length should be greater than 0 and lower than 500!";
			CreateMessageUtil.createErrorDialog(getCurrentDialog(), errorMsg);
		} else {
			Message message = CreateMessageUtil.constructAmbient(senderAmbient, recipientAmbient, respondToMessage, ambientMessage);

			AmbientCSVWriter.writeMessageToCsv(message);
			
			logger.info("Ambient Message created successfully: {}", message);
			CreateMessageUtil.createSuccessDialog(getCurrentDialog(), senderAmbient, recipientAmbient, respondToMessage, ambientMessage);
			getCurrentDialog().dispose();
		}
	}

	/**
	 * @return set up and return the combo box with all ambient names (sorted alphabetically) that are already created.
	 */
	private JComboBox<String> createAmbientComboBox() {
		String[] ambientNames = AmbientRepository.getActiveAmbientNamesSorted();
		
		JComboBox<String> ambientNamesComboBox = new JComboBox<>(ambientNames);
		ambientNamesComboBox.setSelectedIndex(-1); // set default empty value
		ambientNamesComboBox.setCursor(MouseCursorUtil.getMouseHand());
		ambientNamesComboBox.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 5));
		ambientNamesComboBox.setToolTipText("Select an ambient!");
		
		return ambientNamesComboBox;
	}

	/**
	 * @return set up and return the combo box with all ambient messages that are already created.
	 */
	private JComboBox<String> createRespondToComboBox() {
		String[] ambientMessages = MessageRepository.getAmbientMessagesSorted();
		
		JComboBox<String> respondToCombo = new JComboBox<>(ambientMessages);
		respondToCombo.setSelectedIndex(-1); // set default empty value
		respondToCombo.setCursor(MouseCursorUtil.getMouseHand());
		respondToCombo.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 5));
		respondToCombo.setToolTipText("Select a message that you want to respond to!");
		
		return respondToCombo;
	}
	
	/**
	 * @return set up and return the text area of the create message dialog.
	 */
	private JTextArea createTextArea() {
		JTextArea messageTextArea = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLS);
		messageTextArea.setToolTipText("Message that you want to send from one ambient to another");
		messageTextArea.setWrapStyleWord(true);
		
		return messageTextArea;
	}
	
	/**
	 * @return set up and return the labels of the create message dialog.
	 */
	private JLabel createLabel(String labelName) {
		return new JLabel(labelName, JLabel.CENTER);
	}
	
	/**
	 * @return set up and return the layout of the create message dialog.
	 */
	private GridLayout getGridLayout() {
		GridLayout layout = new GridLayout(GRID_ROWS, GRID_COLS);
        layout.setHgap(10);
        layout.setVgap(10);
        
        return layout;
	}
	
	/**
	 * @return an object of the current instance.
	 */
	public CreateAmbientMsgDialog getCurrentDialog() {
		return this;
	}
}

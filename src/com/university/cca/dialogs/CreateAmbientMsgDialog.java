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
import com.university.cca.services.MessageService;
import com.university.cca.util.CreateMessageUtil;
import com.university.cca.util.MouseCursorUtil;

public class CreateAmbientMsgDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CreateAmbientMsgDialog.class);
	
	private static final String TITLE_DIALOG = "Create and Send Message";
	private static final int TEXT_AREA_COLS = 25;
	private static final int TEXT_AREA_ROWS = 2;
	private static final int GRID_ROWS = 6;
	private static final int GRID_COLS = 2;
	private static final int GRID_GAP  = 15;

	private static final boolean IS_VISIBLE    = true;
	private static final boolean HAS_MODAL 	   = true;
	private static final boolean HAS_WRAP_WORD = true;
	
	private JComboBox<String> senderComboBox;
	private JComboBox<String> recipientComboBox;
	private JComboBox<String> passMessageToComboBox;
	private JComboBox<String> respondToComboBox;
	private JTextArea messageTextArea;
	
	public CreateAmbientMsgDialog(AppMainFrame parent) {
		super(parent, TITLE_DIALOG, HAS_MODAL);
		
		addDialogContent();
	
		this.pack();
		this.setLocationRelativeTo(parent);

		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(IS_VISIBLE);
	}

	private void addDialogContent() {
		JPanel contentPanel = new JPanel();
        contentPanel.setLayout(getGridLayout());
        
        // first row of the dialog
        this.senderComboBox = createAmbientComboBox();
        contentPanel.add(createLabel("Sender Ambient Name: "));
        contentPanel.add(this.senderComboBox);
        
        // second row of the dialog
        this.recipientComboBox = createAmbientComboBox();
        contentPanel.add(createLabel("Recipient Ambient Name: "));
        contentPanel.add(this.recipientComboBox);
        
        // third row of the dialog
        this.passMessageToComboBox = createAmbientComboBox();
        contentPanel.add(createLabel("Pass The Message To: "));
        contentPanel.add(this.passMessageToComboBox);
        
        // fourth row of the dialog
        this.respondToComboBox = createRespondToComboBox();
        contentPanel.add(createLabel("Respond To: "));
        contentPanel.add(this.respondToComboBox);

        // fifth row of the dialog
        this.messageTextArea = createTextArea();
        contentPanel.add(createLabel("Ambient Message: "));
        contentPanel.add(new JScrollPane(this.messageTextArea));
        
        // sixth row of the dialog
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
		Object senderAmbient = this.senderComboBox.getSelectedItem();
		Object recipientAmbient = this.recipientComboBox.getSelectedItem();
		Object passMessageTo = this.passMessageToComboBox.getSelectedItem();
		Object respondToMessage = this.respondToComboBox.getSelectedItem();
		String ambientMessage = this.messageTextArea.getText().replace("\n", " ").replace("\r", " ").trim();
		
		if (!MessageService.isValidMessageInfo(senderAmbient, recipientAmbient, passMessageTo, respondToMessage, ambientMessage)) {
			logger.info("Tried to create an ambient message with invalid values");
			String errorMsg = "Please, enter valid values for the input fields!";
			CreateMessageUtil.createErrorDialog(getCurrentDialog(), errorMsg);
		} else if (!MessageService.isValidMessageLength(ambientMessage)) {
			logger.info("Tried to create an ambient message, which is lower than 0 or greater than 500");
			String errorMsg = "The message length should be greater than 0 and lower than 500!";
			CreateMessageUtil.createErrorDialog(getCurrentDialog(), errorMsg);
		} else {
			Message message = MessageService.constructMessage(senderAmbient, recipientAmbient, passMessageTo, respondToMessage, ambientMessage);

			AmbientCSVWriter.writeMessageToCsv(message);
			
			logger.info("Ambient Message created successfully: {}", message);
			CreateMessageUtil.createSuccessDialog(getCurrentDialog(), senderAmbient, recipientAmbient, passMessageTo, respondToMessage, ambientMessage);
			
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
		JTextArea textArea = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLS);
		textArea.setToolTipText("Message that you want to send from one ambient to another");
		textArea.setWrapStyleWord(HAS_WRAP_WORD);
		
		return textArea;
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
        layout.setHgap(GRID_GAP);
        layout.setVgap(GRID_GAP);
        
        return layout;
	}
	
	/**
	 * @return an object of the current instance.
	 */
	public CreateAmbientMsgDialog getCurrentDialog() {
		return this;
	}
}

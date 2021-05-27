package com.university.cca.dialogs.menu.view;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.university.cca.entities.Message;
import com.university.cca.files.csv.AmbientCSVReader;
import com.university.cca.tables.MessageTableModel;
import com.university.cca.util.CCAUtils;

/**
 * The dialog that holds the information for the ambient messages.
 * This information is presented into a table with update option.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class ShowMessagesDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private static final String TITLE = "Show All Ambient Messages Table Dialog";
	
	private static final String NULL_VALUE = "null";
	private static final String DASH_VALUE = "-";
	
	private static final boolean IS_VISIBLE = true;
	private static final boolean IS_MODAL = true;
	
	private static final int HEIGHT_DIALOG = CCAUtils.getScreenSize().height - 100;
	private static final int WIDHT_DIALOG = CCAUtils.getScreenSize().width;
	
	private JFrame parentFrame;

	public ShowMessagesDialog(JFrame parentFrame) {
        super(parentFrame, TITLE, IS_MODAL);
        this.parentFrame = parentFrame;
        
        addDialogContent();
        
        this.setSize(WIDHT_DIALOG, HEIGHT_DIALOG);
        this.setLocationRelativeTo(parentFrame);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(IS_VISIBLE);
	}
	
	private void addDialogContent() {
		JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        
        dialogPanel.add(createMessagesTable());

        this.getContentPane().add(dialogPanel);
	}
	
	private JScrollPane createMessagesTable() {
		List<Message> messages = getAmbientMessages();
		
		MessageTableModel messageTableModel = new MessageTableModel(messages);
		JTable messageTable = new JTable(messageTableModel);
		
		// enable column sorting of the ambient messages table
		messageTable.setAutoCreateRowSorter(MessageTableModel.IS_SORT_AVAILABLE);
		
		return new JScrollPane(messageTable);
	}
	
	private List<Message> getAmbientMessages() {
		return AmbientCSVReader.getAllMessages().stream()
			.map(message ->
			
				new Message(
					message.getSenderAmbient(),
					message.getRecipientAmbient(), 
					getValidRespondTo(message.getRespondToMessage()),
					message.getMessage()
				)
			)
			.collect(Collectors.toList());
	}
	
	private String getValidRespondTo(String respondTo) {
		String respondToResult;
		
		if (respondTo.equalsIgnoreCase(NULL_VALUE)) {
			respondToResult = DASH_VALUE;
		} else {
			respondToResult = respondTo;
		}
		
		return respondToResult;
	}
	
	// Getters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

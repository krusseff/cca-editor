package com.university.cca.dialogs.menu.view;

import java.awt.FlowLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.buttons.CloseDialogButton;
import com.university.cca.buttons.SaveFileButton;
import com.university.cca.constants.Constants;
import com.university.cca.files.csv.AmbientCSVWriter;
import com.university.cca.files.csv.AmbientCsvUtil;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.CCAUtils;
import com.university.cca.util.MouseCursorUtil;

/**
 * The dialog that holds the information for the ambient messages into a CSV file.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class ShowMessagesCSVDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ShowMessagesCSVDialog.class);

	private static final String TITLE = "Ambient Messages CSV File";
	private static final String SHOW_CSV_FILE_TITLE = "<h1 style=\"text-align: center;\"><i> Ambient Messages CSV File </i></h1>";
	private static final String TEXT_PANE_CONTENT_TYPE = "text/html";
	
	private static final int HEIGHT_DIALOG = CCAUtils.getScreenSize().height / 2 + 150;
	private static final int WIDHT_DIALOG = 800;
	
	private JTextArea textArea;

	public ShowMessagesCSVDialog(AppMainFrame parentFrame) {
        super(parentFrame, TITLE, true);
        
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
		dialogPanel.add(createButtonsPanel());

        this.getContentPane().add(dialogPanel);
	}
	
	private JScrollPane createContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel titlePanel = new JPanel(new FlowLayout());
		titlePanel.add(createTextPane(SHOW_CSV_FILE_TITLE));
		
		JPanel contentPanel = new JPanel(new FlowLayout());
		this.textArea = createMessagesCSVTextArea();
		contentPanel.add(new JScrollPane(this.textArea));
		
		panel.add(titlePanel);
		panel.add(contentPanel);
		
		return new JScrollPane(panel);
	}
	
	private JPanel createButtonsPanel() {
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		buttonsPanel.setBackground(Constants.LIGHT_GREY);
		
		buttonsPanel.add(createSaveButton());
		buttonsPanel.add(new CloseDialogButton(this));
		
		return buttonsPanel;
	}
	
	private JTextArea createMessagesCSVTextArea() {
		JTextArea textAreaCCA = new JTextArea();
		String messagesCSVFile = Constants.MESSAGES_CSV_FILE_PATH;

		try (InputStream inputStream = new FileInputStream(messagesCSVFile);
			 Reader inputStreamReader = new InputStreamReader(inputStream);
		) {
			textAreaCCA.read(inputStreamReader, null);
		} catch (IOException e) {
			logger.error("An error occurres during the reading from the ambient messages CSV file: {}", messagesCSVFile);
			textAreaCCA.setText("Unable to read from the ambient messages CSV File or the file does not exist! Contact the administrators!");
			textAreaCCA.setEnabled(false);
		}

		return textAreaCCA;
	}
	
	private SaveFileButton createSaveButton() {
		SaveFileButton saveButton = new SaveFileButton(this);
		
		// If the CSV file does not exist the SAVE button will be disabled
		if (!AmbientCsvUtil.isFileCreated(Constants.MESSAGES_CSV_FILE_PATH)) {
			saveButton.setEnabled(SaveFileButton.BUTTON_DISABLED);
			saveButton.setToolTipText(SaveFileButton.BUTTON_TOOL_TIP_DISABLED);
		}
		
		saveButton.addActionListener(event -> {
			String csvFileContent = this.textArea.getText().trim();
			
			AmbientCSVWriter.writeToCsv(csvFileContent, Constants.MESSAGES_CSV_FILE_PATH);
			
			this.dispose();
		});
		
		return saveButton;
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

	// Getters and Setters
	public JTextArea getTextArea() {
		return this.textArea;
	}
}

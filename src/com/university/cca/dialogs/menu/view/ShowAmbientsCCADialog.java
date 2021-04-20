package com.university.cca.dialogs.menu.view;

import java.awt.FlowLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.buttons.CloseDialogButton;
import com.university.cca.buttons.SaveFileButton;
import com.university.cca.constants.Constants;
import com.university.cca.files.cca.AmbientCCAUtil;
import com.university.cca.files.cca.AmbientCCAWriter;
import com.university.cca.util.CCAUtils;
import com.university.cca.util.MouseCursorUtil;

/**
 * The dialog that holds the information from the CCA file.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class ShowAmbientsCCADialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ShowAmbientsCCADialog.class);

	private static final String TITLE = "Ambients CCA File";
	private static final String SHOW_CCA_FILE_TITLE = "<h1 style=\"text-align: center;\"><i> Ambients CCA File </i></h1>";
	private static final String TEXT_PANE_CONTENT_TYPE = "text/html";
	
	private static final int HEIGHT_DIALOG = CCAUtils.getScreenSize().height / 2 + 150;
	private static final int WIDHT_DIALOG = 800;
	
	private JTextArea textArea;
	
	public ShowAmbientsCCADialog(JFrame parentFrame) {
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
		titlePanel.add(createTextPane(SHOW_CCA_FILE_TITLE));
		
		JPanel contentPanel = new JPanel(new FlowLayout());
		textArea = createCCATextArea();
		contentPanel.add(new JScrollPane(textArea));
		
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
	
	private JTextArea createCCATextArea() {
		JTextArea textAreaCCA = new JTextArea();
		String ccaFile = Constants.AMBIENTS_CCA_FILE_PATH;

		try (InputStream inputStream = new FileInputStream(ccaFile);
			 Reader inputStreamReader = new InputStreamReader(inputStream);
		) {
			textAreaCCA.read(inputStreamReader, null);
		} catch (IOException e) {
			logger.error("An error occurres during the reading from the CCA file: {}", ccaFile);
			textAreaCCA.setText("Unable to read from the CCA File or the file does not exist! Contact the administrators!");
			textAreaCCA.setEnabled(false);
		}

		return textAreaCCA;
	}
	
	private SaveFileButton createSaveButton() {
		SaveFileButton saveButton = new SaveFileButton(this);
		
		// If the CCA file does NOT exist the SAVE button will be disabled
		if (!AmbientCCAUtil.isFileCreated()) {
			saveButton.setEnabled(SaveFileButton.BUTTON_DISABLED);
			saveButton.setToolTipText(SaveFileButton.BUTTON_TOOL_TIP_DISABLED);
		}
		
		saveButton.addActionListener(event -> {
			String ccaFileContent = textArea.getText().trim();
			
			AmbientCCAWriter.write(ccaFileContent);
			
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

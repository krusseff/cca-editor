package com.university.cca.dialogs.menu.file;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.university.cca.buttons.export.ExportAmbientsCSVButton;
import com.university.cca.buttons.export.ExportCCAFileButton;
import com.university.cca.buttons.export.ExportMessagesCSVButton;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.MouseCursorUtil;

/**
 * The dialog that holds the buttons that are related to the export functionality of the application.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class ExportDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	// Constants related to the content of the dialog
	private static final String TEXT_PANE_CONTENT_TYPE = "text/html";
	private static final String DIALOG_TITLE = "Export Dialog";
	private static final String EXPORT_TITLE = "<h1 style=\"text-align: center;\"><i> Export Section </i></h1>";
	private static final String FOOTER_NOTE  = "NOTE: Please select a button and export the data that you want to analyze!";
	private static final boolean IS_DIALOG_MODAL   = true;
	private static final boolean IS_DIALOG_VISIBLE = true;
	
	// Constants with the size of the dialog
	private static final int HEIGHT_DIALOG = 400;
	private static final int WIDHT_DIALOG = 600;
	
	// Dialog content grid size
	private static final int GRID_ROWS = 5;
	private static final int GRID_COLS = 1;
	
	private AppMainFrame parentFrame;
	
	public ExportDialog(AppMainFrame parentFrame) {
        super(parentFrame, DIALOG_TITLE, IS_DIALOG_MODAL);
        this.parentFrame = parentFrame;
        
        addDialogContent();
        
        this.setSize(WIDHT_DIALOG, HEIGHT_DIALOG);
        this.setLocationRelativeTo(parentFrame);

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(IS_DIALOG_VISIBLE);
	}
	
	private void addDialogContent() {
		JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        
		dialogPanel.add(createContentPanel());
		dialogPanel.add(createFooterPanel());

        this.getContentPane().add(dialogPanel);
	}
	
	private JScrollPane createContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel titlePanel = new JPanel(new FlowLayout());
		titlePanel.add(createTextPane(EXPORT_TITLE));
		
		JPanel contentPanel = new JPanel(new GridLayout(GRID_ROWS, GRID_COLS));
		contentPanel.add(new ExportCCAFileButton(getParentFrame(), this));
		contentPanel.add(new ExportAmbientsCSVButton(getParentFrame(), this));
		contentPanel.add(new ExportMessagesCSVButton(getParentFrame(), this));
		
		panel.add(titlePanel);
		panel.add(contentPanel);
		
		return new JScrollPane(panel);
	}
	
	private JPanel createFooterPanel() {
		JPanel footerPanel = new JPanel(new FlowLayout());
		footerPanel.add(new JLabel(FOOTER_NOTE));
		
		return footerPanel;
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
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

package com.university.cca.dialogs.cca.generation;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.university.cca.buttons.cca.GenerateCCAButtonV1;
import com.university.cca.buttons.cca.OpenCCAFileButton;
import com.university.cca.buttons.cca.StartAnimatedScenarioButton;
import com.university.cca.buttons.cca.StartConsoleScenarioButton;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.MouseCursorUtil;

/**
 * The dialog that holds the buttons that are related to the generation of the CCA file.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CCAGenerationDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private static final String TITLE = "CCA Generation Dialog";
	private static final String CCA_GENERATOR_TITLE = "<h1 style=\"text-align: center;\"><i> CCA Generator </i></h1>";
	private static final String FOOTER_PANEL_NOTE = "NOTE: Create the whole CCA Model and as a last step generate the CCA file!";
	private static final String TEXT_PANE_CONTENT_TYPE = "text/html";
	
	private static final int HEIGHT_DIALOG = 400;
	private static final int WIDHT_DIALOG = 600;
	
	private static final int GRID_ROWS = 4;
	private static final int GRID_COLS = 1;
	
	private AppMainFrame parentFrame;

	public CCAGenerationDialog(AppMainFrame parentFrame) {
        super(parentFrame, TITLE, true);
        this.parentFrame = parentFrame;
        
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
		dialogPanel.add(footerPanel());

        this.getContentPane().add(dialogPanel);
	}
	
	private JScrollPane createContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel titlePanel = new JPanel(new FlowLayout());
		titlePanel.add(createTextPane(CCA_GENERATOR_TITLE));
		
		JPanel contentPanel = new JPanel(new GridLayout(GRID_ROWS, GRID_COLS));
		contentPanel.add(new GenerateCCAButtonV1(getParentFrame()));
		// TODO: add the new button for version 2
		contentPanel.add(new StartConsoleScenarioButton(getParentFrame()));
		contentPanel.add(new StartAnimatedScenarioButton(getParentFrame()));
		contentPanel.add(new OpenCCAFileButton(getParentFrame()));

		panel.add(titlePanel);
		panel.add(contentPanel);
		
		return new JScrollPane(panel);
	}
	
	private JPanel footerPanel() {
		JPanel footerPanel = new JPanel(new FlowLayout());

		JLabel footerLabel = new JLabel(FOOTER_PANEL_NOTE);
		footerPanel.add(footerLabel);
		
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

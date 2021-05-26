package com.university.cca.dialogs.menu.view;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
	private static final boolean IS_VISIBLE = true;
	private static final boolean IS_MODAL = true;
	
	// TODO: Think about the size of the dialog???
	private static final int HEIGHT_DIALOG = CCAUtils.getScreenSize().height;
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
        
		// TODO: Add dialog content: Messages Table
        // dialogPanel.add(createMessagesTable());

        this.getContentPane().add(dialogPanel);
	}
	
	// Getters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

package com.university.cca.dialogs.cca.execution;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.university.cca.constants.Constants;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.MouseCursorUtil;

/**
 * Dialog that displays the output of a CCA scenario execution via the ccaPL interpreter.
 * Shows the execution trace with reduction steps, message passing, and mobility events.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CcaConsoleOutputDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final String TITLE = "CCA Console Scenario Output";
	private static final boolean IS_MODAL = true;
	private static final boolean IS_VISIBLE = true;

	private static final int DIALOG_WIDTH = 800;
	private static final int DIALOG_HEIGHT = 600;
	private static final int FONT_SIZE = 13;

	public CcaConsoleOutputDialog(AppMainFrame parentFrame, String output, boolean isSuccess) {
		super(parentFrame, TITLE, IS_MODAL);

		addDialogContent(output, isSuccess);

		this.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
		this.setLocationRelativeTo(parentFrame);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(IS_VISIBLE);
	}

	private void addDialogContent(String output, boolean isSuccess) {
		JPanel mainPanel = new JPanel(new BorderLayout());

		JTextArea textArea = new JTextArea(output);
		textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, FONT_SIZE));
		textArea.setEditable(false);
		textArea.setCaretPosition(0);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(DIALOG_WIDTH - 20, DIALOG_HEIGHT - 80));

		mainPanel.add(scrollPane, BorderLayout.CENTER);
		mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);

		this.getContentPane().add(mainPanel);
	}

	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		JButton closeButton = new JButton("Close");
		closeButton.setIcon(new javax.swing.ImageIcon(Constants.CLOSE_ICON_PATH));
		closeButton.setCursor(MouseCursorUtil.getMouseHand());
		closeButton.addActionListener(e -> dispose());

		buttonPanel.add(closeButton);

		return buttonPanel;
	}
}

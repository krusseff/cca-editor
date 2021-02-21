package com.university.cca.menu.items.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.util.MouseCursorUtil;

public class ExitMenuItem extends JMenuItem implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ExitMenuItem.class);
	
	public static final int YES_OPTION = 0;
	
	private JFrame parentFrame;
	
	public ExitMenuItem(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.FILE_ITEM_EXIT);
		this.setIcon(new ImageIcon(Constants.EXIT_ICON_PATH));
		
		this.setToolTipText("Exit Application");
		this.setCursor(MouseCursorUtil.getMouseHand());
		this.setIconTextGap(Constants.ICON_GAP_SIZE);
		
		// Open File Menu and Press 'E' = Click on Exit
		this.setMnemonic(Constants.EXIT_SHORTCUT);
		
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("File Exit button is clicked");
		
		if (createConfirmationDialog() == YES_OPTION) {
			logger.info("Exiting the application...");
			System.exit(0);
		}
	}
	
	private int createConfirmationDialog() {
		return JOptionPane.showConfirmDialog(
			parentFrame, 
			"Are you sure you want to quit the application?", 
			"Confirm Quit Application Operation",
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );
	}

	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}

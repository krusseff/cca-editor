package com.university.cca.menu.items.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.UnderConstructionDialog;
import com.university.cca.util.MouseCursorUtil;

public class TerminalMenuItem extends JMenuItem implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(TerminalMenuItem.class);
	
	private JFrame parentFrame;
	
	public TerminalMenuItem(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.FILE_ITEM_TERMINAL);
		this.setIcon(new ImageIcon(Constants.TERMINAL_ICON_PATH));
		this.setCursor(MouseCursorUtil.getMouseHand());
		
		// Open File Menu and Press 'T' = Click on Open Terminal
		this.setMnemonic(Constants.TERMINAL_SHORTCUT);
		
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("File Open Terminal button is clicked");
		
		openTerminal();
	}
	
	private void openTerminal() {
		logger.info("User OS Type: {}", SystemUtils.OS_NAME);
		
		try {
			String openTerminalCommand = determineCommand();
			
			if (openTerminalCommand != null) {
				Runtime.getRuntime().exec(openTerminalCommand);
			} else {
				new UnderConstructionDialog(parentFrame);
			}
			
		} catch (IOException ex) {
			logger.error("Unable to open the terminal of the operation system!");
			logger.error("The following error occurres while openning the terminal: {}", ex.getMessage());
		}
	}
	
	private String determineCommand() {
		String command;
		
		if (SystemUtils.IS_OS_WINDOWS) {
			command = "cmd /c start cmd.exe";
		} else if (SystemUtils.IS_OS_LINUX) {
			command = "/usr/bin/xterm";
		} else if (SystemUtils.IS_OS_MAC) {
			command = "/bin/bash -c";
		} else {
			logger.error("Not able to determin the OS: {}", SystemUtils.OS_NAME);
			command = null;
		}
		
		return command;
	}
	
	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}
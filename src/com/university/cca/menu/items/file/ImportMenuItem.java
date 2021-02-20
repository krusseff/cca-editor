package com.university.cca.menu.items.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.UnderConstructionDialog;
import com.university.cca.util.MouseCursorUtil;

public class ImportMenuItem extends JMenuItem implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ImportMenuItem.class);
	
	private JFrame parentFrame;
	
	public ImportMenuItem(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.FILE_ITEM_IMPORT);
		this.setIcon(new ImageIcon(Constants.IMPORT_ICON_PATH));
		this.setCursor(MouseCursorUtil.getMouseHand());
		
		// Open File Menu and Press 'I' = Click on Import
		this.setMnemonic(Constants.IMPORT_SHORTCUT);
		
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO: Add the exact implementation here
		
		new UnderConstructionDialog(parentFrame);
		
		logger.info("File Import button is clicked");
	}
	
	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}
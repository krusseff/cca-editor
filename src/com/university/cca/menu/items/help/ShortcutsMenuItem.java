package com.university.cca.menu.items.help;

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

public class ShortcutsMenuItem extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ShortcutsMenuItem.class);
	
	private JFrame parentFrame;
	
	public ShortcutsMenuItem(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.HELP_ITEM_SHORTCUTS);
		this.setIcon(new ImageIcon(Constants.SHORTCUTS_ICON_PATH));
		
		this.setToolTipText("Keyboard Shortcuts");
		this.setCursor(MouseCursorUtil.getMouseHand());
		
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO: Add the exact implementation here
		// 1. Open File Menu and Press 'E' = Click on Exit
		// 2. Open File Menu and Press 'I' = Click on Import
		// 3. Open File Menu and Press 'X' = Click on Export
		// 4. Open File Menu and Press 'T' = Click on Open Terminal
		// 5. Open Edit Menu and Press 'D' = Click on Delete
		// 6. Open Edit Menu and Press 'P' = Click on Paste
		// 7. Open Edit Menu and Press 'C' = Click on Copy
		// 8. Open Edit Menu and Press 'U' = Click on Cut
		
		new UnderConstructionDialog(parentFrame);
		
		logger.info("Help Tips and Tricks button is clicked");
	}

	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}

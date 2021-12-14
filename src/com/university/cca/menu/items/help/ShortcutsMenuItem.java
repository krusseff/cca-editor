package com.university.cca.menu.items.help;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.menu.help.ShortcutsDialog;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.MouseCursorUtil;

public class ShortcutsMenuItem extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ShortcutsMenuItem.class);
	
	private AppMainFrame parentFrame;
	
	public ShortcutsMenuItem(AppMainFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.HELP_ITEM_SHORTCUTS);
		this.setIcon(new ImageIcon(Constants.SHORTCUTS_ICON_PATH));
		
		this.setToolTipText("Keyboard Shortcuts");
		this.setCursor(MouseCursorUtil.getMouseHand());
		this.setIconTextGap(Constants.ICON_GAP_SIZE);
		
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Help Tips and Tricks Button is clicked");
		
		new ShortcutsDialog(getParentFrame());
	}

	// Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

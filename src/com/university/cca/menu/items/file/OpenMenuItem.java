package com.university.cca.menu.items.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.UnderConstructionDialog;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.MouseCursorUtil;

public class OpenMenuItem extends JMenuItem implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(OpenMenuItem.class);
	
	private AppMainFrame parentFrame;
	
	public OpenMenuItem(AppMainFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.FILE_ITEM_OPEN);
		this.setIcon(new ImageIcon(Constants.OPEN_ICON_PATH));
		this.setCursor(MouseCursorUtil.getMouseHand());
		this.setIconTextGap(Constants.ICON_GAP_SIZE);
		
		// Open File Menu and click on Open or press 'CTRL + O'
	    this.setAccelerator(KeyStroke.getKeyStroke(Constants.OPEN_SHORTCUT, Constants.CTRL_SHORTCUT));
		
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new UnderConstructionDialog(getParentFrame());
		
		logger.info("File Open Button is clicked");
	}
	
	// Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

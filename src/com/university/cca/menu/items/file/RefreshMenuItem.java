package com.university.cca.menu.items.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.util.MouseCursorUtil;

public class RefreshMenuItem extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(RefreshMenuItem.class);
	
	private JFrame parentFrame;
	
	public RefreshMenuItem(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.FILE_ITEM_REFRESH);
		this.setIcon(new ImageIcon(Constants.REFRESH_ICON_PATH));
		this.setCursor(MouseCursorUtil.getMouseHand());
		
		// Open File Menu and Press 'R' = Click on Refresh
		this.setMnemonic(Constants.REFRESH_SHORTCUT);
		
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("File Refresh button is clicked");
		
		SwingUtilities.updateComponentTreeUI(parentFrame);
	}
	
	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}

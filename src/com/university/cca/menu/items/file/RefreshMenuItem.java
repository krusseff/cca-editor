package com.university.cca.menu.items.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
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
		this.setIconTextGap(Constants.ICON_GAP_SIZE);
		
		// Open File Menu and click on Refresh or press 'CTRL + R'
	    this.setAccelerator(KeyStroke.getKeyStroke(Constants.REFRESH_SHORTCUT, Constants.CTRL_SHORTCUT));
		
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("File Refresh Button is clicked");
		
		SwingUtilities.updateComponentTreeUI(this.parentFrame);
	}
	
	// Getters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

package com.university.cca.menu.items.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.MouseCursorUtil;

public class RestartMenuItem extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(RestartMenuItem.class);
	
	private AppMainFrame parentFrame;
	
	public RestartMenuItem(AppMainFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.FILE_ITEM_RESTART);
		this.setIcon(new ImageIcon(Constants.RESTART_ICON_PATH));
		this.setCursor(MouseCursorUtil.getMouseHand());
		this.setToolTipText("Restart the application");
		this.setIconTextGap(Constants.ICON_GAP_SIZE);
		
		// Open File Menu and click on Restart or press 'CTRL + L'
	    this.setAccelerator(KeyStroke.getKeyStroke(Constants.RESTART_SHORTCUT, Constants.CTRL_SHORTCUT));
		
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("File Restart Button is clicked");
		logger.info("CCA Editor application re-starting... :)");

		int applicationMode = this.parentFrame.getApplicationMode();
		
		this.parentFrame.dispose();
		new AppMainFrame(applicationMode);
	}
	
	// Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

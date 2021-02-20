package com.university.cca.menu.items.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.MouseCursorUtil;

public class RestartMenuItem extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(RestartMenuItem.class);
	
	private JFrame parentFrame;
	
	public RestartMenuItem(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.FILE_ITEM_RESTART);
		this.setIcon(new ImageIcon(Constants.RESTART_ICON_PATH));
		this.setCursor(MouseCursorUtil.getMouseHand());
		this.setToolTipText("Restart the application");
		
		// Open File Menu and Press 'L' = Click on Restart
		this.setMnemonic(Constants.RESTART_SHORTCUT);
		
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("File Restart button is clicked");
		logger.info("CCA Editor application re-starting... :)");

		parentFrame.dispose();
		new AppMainFrame();
	}
	
	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}

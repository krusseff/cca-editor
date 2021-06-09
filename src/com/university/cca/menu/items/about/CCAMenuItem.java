package com.university.cca.menu.items.about;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.menu.about.CCADialog;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.MouseCursorUtil;

public class CCAMenuItem extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(CCAMenuItem.class);
    
    private AppMainFrame parentFrame;
	
	public CCAMenuItem(AppMainFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.ABOUT_ITEM_CCA);		
		this.setIcon(new ImageIcon(Constants.ABOUT_CCA_ICON_PATH));
		this.setCursor(MouseCursorUtil.getMouseHand());
		this.setIconTextGap(Constants.ICON_GAP_SIZE);
		
		// Open About Menu and click on About CCA or press 'CTRL + 0'
	    this.setAccelerator(KeyStroke.getKeyStroke(Constants.ABOUT_CCA_SHORTCUT, Constants.CTRL_SHORTCUT));
		
		this.addActionListener(this);
	}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("About CCA item menu button is clicked");
		
		new CCADialog(this.parentFrame);
	}

	// Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

package com.university.cca.menu.items.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.menu.view.ShowMessagesDialog;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.MouseCursorUtil;

public class ShowAmbientMessages extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ShowAmbientMessages.class);
	
	private AppMainFrame parentFrame;
	
	public ShowAmbientMessages(AppMainFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.VIEW_ITEM_MESSAGES);
		this.setIcon(new ImageIcon(Constants.SHOW_AMBIENTS_INFO_ICON_PATH));
		
		this.setToolTipText("Show All Ambient Messages");
		this.setCursor(MouseCursorUtil.getMouseHand());
		this.setIconTextGap(Constants.ICON_GAP_SIZE);
		
		// Open View Menu and click on Show Messages or press 'CTRL + 9'
	    this.setAccelerator(KeyStroke.getKeyStroke(Constants.SHOW_MESSAGES_SHORTCUT, Constants.CTRL_SHORTCUT));

		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("View Show All Ambient Messages information button is clicked");
		
		new ShowMessagesDialog(getParentFrame());
	}

	// Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

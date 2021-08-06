package com.university.cca.menu.items.view;

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

public class ShowMessagesStatistics extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ShowMessagesStatistics.class);
	
	private AppMainFrame parentFrame;
	
	public ShowMessagesStatistics(AppMainFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.VIEW_ITEM_MESSAGES_STATS);
		this.setIcon(new ImageIcon(Constants.SHOW_AMBIENTS_STATS_ICON_PATH));
		
		this.setToolTipText("Show Messages Statistics");
		this.setCursor(MouseCursorUtil.getMouseHand());
		this.setIconTextGap(Constants.ICON_GAP_SIZE);
		
		// Open View Menu and click on Show Messages Statistics or press 'CTRL + SHIFT + S'
	    this.setAccelerator(KeyStroke.getKeyStroke(Constants.SHOW_MESSAGES_STATS_SHORTCUT, Constants.CTRL_SHIFT_SHORTCUT));
		
	    // TODO: Remove when the functionality is ready
	    this.setEnabled(false);
	    
	    this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("View Show Messages Statistics button is clicked");
		
		// TODO: new ShowMessagesStatsDialog(getParentFrame());
		new UnderConstructionDialog(getParentFrame());
	}

	// Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

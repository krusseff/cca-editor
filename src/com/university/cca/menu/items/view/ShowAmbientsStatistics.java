package com.university.cca.menu.items.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.menu.view.ShowAmbientsStatisticsDialog;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.MouseCursorUtil;

public class ShowAmbientsStatistics extends JMenuItem implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ShowAmbientsStatistics.class);
	
	private static final String TOOL_TIP = "Show Ambients Statistics";
	
	private AppMainFrame parentFrame;
	
	public ShowAmbientsStatistics(AppMainFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.VIEW_ITEM_AMBIENTS_STATS);
		this.setIcon(new ImageIcon(Constants.SHOW_AMBIENTS_STATS_ICON_PATH));
		
		this.setToolTipText(TOOL_TIP);
		this.setCursor(MouseCursorUtil.getMouseHand());
		this.setIconTextGap(Constants.ICON_GAP_SIZE);
		
		// Open View Menu and click on Show Ambients Statistics or press 'CTRL + ALT + S'
	    this.setAccelerator(KeyStroke.getKeyStroke(Constants.SHOW_AMBIENTS_STATS_SHORTCUT, Constants.CTRL_ALT_SHORTCUT));
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("View Show Ambients Statistics button is clicked");
		
		new ShowAmbientsStatisticsDialog(getParentFrame());
	}

	// Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

package com.university.cca.menu.items.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.menu.view.ShowAmbientsDialog;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.MouseCursorUtil;

public class ShowAmbients extends JMenuItem implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ShowAmbients.class);
	
	private static final String TOOL_TIP = "Show All Ambients";
	
	private AppMainFrame parentFrame;
	
	public ShowAmbients(AppMainFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.VIEW_ITEM_AMBIENTS);
		this.setIcon(new ImageIcon(Constants.SHOW_AMBIENTS_INFO_ICON_PATH));
		
		this.setToolTipText(TOOL_TIP);
		this.setCursor(MouseCursorUtil.getMouseHand());
		this.setIconTextGap(Constants.ICON_GAP_SIZE);
		
		// Open View Menu and click on Show Ambients or press 'CTRL + 8'
	    this.setAccelerator(KeyStroke.getKeyStroke(Constants.SHOW_AMBIENTS_SHORTCUT, Constants.CTRL_SHORTCUT));

	    // TODO: Remove when the functionality is ready
	    this.setEnabled(false);
	    
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("View Show All Ambients information button is clicked");
		
		new ShowAmbientsDialog(getParentFrame());
	}

	// Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

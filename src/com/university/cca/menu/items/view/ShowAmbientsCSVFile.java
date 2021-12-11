package com.university.cca.menu.items.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.menu.view.ShowAmbientsCSVDialog;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.MouseCursorUtil;

public class ShowAmbientsCSVFile extends JMenuItem implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ShowAmbientsCSVFile.class);
	
	private static final String TOOL_TIP = "Show All Ambients CSV File";
	
	private AppMainFrame parentFrame;
	
	public ShowAmbientsCSVFile(AppMainFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.VIEW_ITEM_AMBIENTS_CSV);
		this.setIcon(new ImageIcon(Constants.SHOW_CSV_ICON_PATH));
		
		this.setToolTipText(TOOL_TIP);
		this.setCursor(MouseCursorUtil.getMouseHand());
		this.setIconTextGap(Constants.ICON_GAP_SIZE);
		
		// Open View Menu and click on Show Ambients CSV File or press 'CTRL + 6'
	    this.setAccelerator(KeyStroke.getKeyStroke(Constants.SHOW_AMBIENTS_CSV_SHORTCUT, Constants.CTRL_SHORTCUT));

		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("View Show Ambients CSV File button is clicked");
		
		new ShowAmbientsCSVDialog(getParentFrame());
	}

	// Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

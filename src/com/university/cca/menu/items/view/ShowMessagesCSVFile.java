package com.university.cca.menu.items.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.UnderConstructionDialog;
import com.university.cca.util.MouseCursorUtil;

public class ShowMessagesCSVFile extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ShowMessagesCSVFile.class);
	
	private JFrame parentFrame;
	
	public ShowMessagesCSVFile(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.VIEW_ITEM_MESSAGES_CSV);
		this.setIcon(new ImageIcon(Constants.SHOW_CSV_ICON_PATH));
		
		this.setToolTipText("Show All Ambient Messages CSV File");
		this.setCursor(MouseCursorUtil.getMouseHand());
		this.setIconTextGap(Constants.ICON_GAP_SIZE);
		
		// Open View Menu and click on Show Messages CSV File or press 'CTRL + 7'
	    this.setAccelerator(KeyStroke.getKeyStroke(Constants.SHOW_MESSAGES_CSV_SHORTCUT, Constants.CTRL_SHORTCUT));

		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("View Show Ambient Messages CSV File button is clicked");
		
		// TODO
		// [IN PROGRESS] Show ambient messages CSV file
		
		new UnderConstructionDialog(getParentFrame());
	}

	// Getters and Setters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

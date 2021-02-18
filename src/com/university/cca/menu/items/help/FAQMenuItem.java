package com.university.cca.menu.items.help;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.UnderConstructionDialog;

public class FAQMenuItem extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(FAQMenuItem.class);
	
	private JFrame parentFrame;

	public FAQMenuItem(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.HELP_ITEM_FAQ);
		this.setIcon(new ImageIcon(Constants.HELP_ICON_PATH));
		
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO: Add the exact implementation here
		
		new UnderConstructionDialog(parentFrame);
		
		logger.info("Help FAQ button is clicked");
	}

	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}

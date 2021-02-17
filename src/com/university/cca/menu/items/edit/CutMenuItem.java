package com.university.cca.menu.items.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.UnderConstructionDialog;

public class CutMenuItem extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CutMenuItem.class);
	
	private JFrame parentFrame;

	public CutMenuItem(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.EDIT_ITEM_CUT);
		this.setIcon(new ImageIcon(Constants.CUT_ICON_PATH));
		
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO: Add the exact implementation here
		
		new UnderConstructionDialog(parentFrame);
		
		logger.info("Edit cut button is clicked");
	}

	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}

package com.university.cca.menu.items.about;

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

public class LinksMenuItem extends JMenuItem implements ActionListener {
	
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(LinksMenuItem.class);
    
    private JFrame parentFrame;
    
	public LinksMenuItem(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.ABOUT_ITEM_LINKS);		
		this.setIcon(new ImageIcon(Constants.LINKS_ICON_PATH));
		this.setCursor(MouseCursorUtil.getMouseHand());
		this.setIconTextGap(Constants.ICON_GAP_SIZE);
		
		// Open About Menu and click on Useful Links or press 'CTRL + 3'
	    this.setAccelerator(KeyStroke.getKeyStroke(Constants.USEFUL_LINKS_SHORTCUT, Constants.CTRL_SHORTCUT));
		
		this.addActionListener(this);
	}    
    
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Useful Links About item menu button is clicked");
		
		new UnderConstructionDialog(parentFrame);
		
		// TODO: Add the exact implementation here
		// 4. Useful links: delc, delc2, delc space, github repo, fmi plovdiv web site
	}

	// Getters and Setters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

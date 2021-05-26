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
import com.university.cca.dialogs.menu.about.CCATeamDialog;
import com.university.cca.util.MouseCursorUtil;

public class CCATeamMenuItem extends JMenuItem implements ActionListener {
	
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(CCATeamMenuItem.class);
    
    private JFrame parentFrame;

	public CCATeamMenuItem(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.ABOUT_ITEM_CCA_TEAM);		
		this.setIcon(new ImageIcon(Constants.CCA_TEAM_ICON_PATH));
		this.setCursor(MouseCursorUtil.getMouseHand());
		this.setIconTextGap(Constants.ICON_GAP_SIZE);
		
		// Open About Menu and click on CCA Team or press 'CTRL + 2'
	    this.setAccelerator(KeyStroke.getKeyStroke(Constants.CCA_TEAM_SHORTCUT, Constants.CTRL_SHORTCUT));
		
		this.addActionListener(this);
	}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("About CCA Editor Team item menu button is clicked");

		new CCATeamDialog(this.parentFrame);
	}

	// Getters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

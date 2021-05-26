package com.university.cca.menu.items.help;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.menu.help.FAQDialog;
import com.university.cca.util.MouseCursorUtil;

public class FAQMenuItem extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(FAQMenuItem.class);
	
	private JFrame parentFrame;

	public FAQMenuItem(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.HELP_ITEM_FAQ);
		this.setIcon(new ImageIcon(Constants.HELP_ICON_PATH));
		
		this.setToolTipText("Frequently Asked Questions");
		this.setCursor(MouseCursorUtil.getMouseHand());
		this.setIconTextGap(Constants.ICON_GAP_SIZE);
		
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Help FAQ Button is clicked");
		
		new FAQDialog(this.parentFrame);
	}

	// Getters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

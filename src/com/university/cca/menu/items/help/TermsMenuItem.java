package com.university.cca.menu.items.help;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.TermsDialog;
import com.university.cca.util.MouseCursorUtil;

public class TermsMenuItem extends JMenuItem implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(TermsMenuItem.class);
	
	private JFrame parentFrame;
	
	public TermsMenuItem(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.HELP_ITEM_TERMS);
		this.setIcon(new ImageIcon(Constants.TERMS_ICON_PATH));
		
		this.setToolTipText("Terms and Conditions");
		this.setCursor(MouseCursorUtil.getMouseHand());
		this.setIconTextGap(Constants.ICON_GAP_SIZE);
		
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Help Terms and Conditions button is clicked");
		
		new TermsDialog(parentFrame);
	}

	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}

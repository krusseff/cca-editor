package com.university.cca.menu.items.edit;

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

public class DeleteMenuItem extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DeleteMenuItem.class);
    
    private JFrame parentFrame;
    
	public DeleteMenuItem(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.EDIT_ITEM_DELETE);
		this.setIcon(new ImageIcon(Constants.DELETE_ICON_PATH));
		this.setCursor(MouseCursorUtil.getMouseHand());
		this.setIconTextGap(Constants.ICON_GAP_SIZE);
		
		// Open Edit Menu and click on Delete or press 'CTRL + D'
	    this.setAccelerator(KeyStroke.getKeyStroke(Constants.DELETE_SHORTCUT, Constants.CTRL_SHORTCUT));
		
		this.addActionListener(this);
	}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		new UnderConstructionDialog(this.parentFrame);
		
		logger.info("Edit Delete Button is clicked");
	}
	
	// Getters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

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

public class CopyMenuItem extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(CopyMenuItem.class);
    
    private JFrame parentFrame;

	public CopyMenuItem(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.EDIT_ITEM_COPY);		
		this.setIcon(new ImageIcon(Constants.COPY_ICON_PATH));
		this.setCursor(MouseCursorUtil.getMouseHand());
		this.setIconTextGap(Constants.ICON_GAP_SIZE);
		
		// Open Edit Menu and click on Copy or press 'CTRL + C'
	    this.setAccelerator(KeyStroke.getKeyStroke(Constants.COPY_SHORTCUT, Constants.CTRL_SHORTCUT));
		
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO: Add the exact implementation here
		
		new UnderConstructionDialog(parentFrame);
		
		logger.info("Edit copy button is clicked");
	}

	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}

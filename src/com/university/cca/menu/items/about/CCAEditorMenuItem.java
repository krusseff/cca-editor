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
import com.university.cca.dialogs.menu.about.CCAEditorDialog;
import com.university.cca.util.MouseCursorUtil;

public class CCAEditorMenuItem extends JMenuItem implements ActionListener {
	
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(CCAEditorMenuItem.class);

    private JFrame parentFrame;
    
	public CCAEditorMenuItem(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setText(Constants.ABOUT_ITEM_CCA_EDITOR);		
		this.setIcon(new ImageIcon(Constants.ABOUT_CCA_ICON_PATH));
		this.setCursor(MouseCursorUtil.getMouseHand());
		this.setIconTextGap(Constants.ICON_GAP_SIZE);
		
		// Open About Menu and click on About CCA Editor or press 'CTRL + 1'
	    this.setAccelerator(KeyStroke.getKeyStroke(Constants.ABOUT_CCA_EDITOR_SHORTCUT, Constants.CTRL_SHORTCUT));
		
		this.addActionListener(this);
	}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("About CCA Editor item menu button is clicked");

		new CCAEditorDialog(parentFrame);
	}

	// Getters and Setters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

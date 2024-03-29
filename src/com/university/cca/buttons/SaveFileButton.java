package com.university.cca.buttons;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import com.university.cca.constants.Constants;
import com.university.cca.util.MouseCursorUtil;

/**
 * Button that saves the file content from text area to the CCA/CSV file.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class SaveFileButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	
	public static final boolean BUTTON_DISABLED 		= false;
	public static final String BUTTON_TOOL_TIP_DISABLED = "Not able to save to not existing file";
	
	private static final String BUTTON_NAME		 = "Save";
	private static final String BUTTON_TOOL_TIP  = "Click to save the content";
	
	private JDialog parentDialog;

    public SaveFileButton(JDialog parentDialog) {
    	this.parentDialog = parentDialog;
    	
    	this.setText(BUTTON_NAME);
        this.setIcon(new ImageIcon(Constants.SAVE_BUTTON_ICON_PATH));

        this.setCursor(MouseCursorUtil.getMouseHand());
        this.setToolTipText(BUTTON_TOOL_TIP);
        
        this.setPreferredSize(new Dimension(200, 35));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
    }
	
	// Getters and Setters
	public JDialog getParentDialog() {
		return this.parentDialog;
	}
}

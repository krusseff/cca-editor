package com.university.cca.buttons;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.university.cca.constants.Constants;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.MouseCursorUtil;

/**
 * Button that exports ambient and message statistics to the CSV file.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class ExportToCSVButton extends JButton {

	private static final long serialVersionUID = 1L;
	
	public static final boolean BUTTON_DISABLED 		= false;
	public static final String BUTTON_TOOL_TIP_DISABLED = "Not available statistics to export";
	
	private static final String BUTTON_NAME		= "Export to CSV";
	private static final String BUTTON_TOOL_TIP = "Export to CSV File";
	private static final boolean IS_FOCUSABLE 	= false;
	
	private AppMainFrame parentFrame;
	
    public ExportToCSVButton(AppMainFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
        this.setText(BUTTON_NAME);
        this.setIcon(new ImageIcon(Constants.EXPORT_CSV_BUTTON_ICON_PATH));

        this.setPreferredSize(new Dimension(155, 35));
        this.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.setAlignmentY(Component.RIGHT_ALIGNMENT);
        this.setIconTextGap(Constants.ICON_GAP_SIZE);

        this.setFocusable(IS_FOCUSABLE);
        this.setToolTipText(BUTTON_TOOL_TIP);
        this.setCursor(MouseCursorUtil.getMouseHand());
    }
	
	// Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

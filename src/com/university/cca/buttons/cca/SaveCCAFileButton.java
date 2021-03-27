package com.university.cca.buttons.cca;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.files.cca.AmbientCCAWriter;
import com.university.cca.util.MouseCursorUtil;

/**
 * Button that saves the CCA content from text area to the CCA file.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class SaveCCAFileButton extends JButton implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SaveCCAFileButton.class);
	
	private static final String BUTTON_TOOL_TIP = "Click to save the content";
	
	private JDialog parentDialog;
	private String content;

    public SaveCCAFileButton(JDialog parentDialog, String contentToSave) {
    	this.parentDialog = parentDialog;
    	this.content = contentToSave;
    	
    	this.setText("Save");
        this.setIcon(new ImageIcon(Constants.SAVE_BUTTON_ICON_PATH));
        this.setToolTipText(BUTTON_TOOL_TIP);
        this.setCursor(MouseCursorUtil.getMouseHand());
        
        this.setPreferredSize(new Dimension(200, 35));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
        
        this.addActionListener(this);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Save CCA file button is clicked");

		saveToFile();
	}
	
	private void saveToFile() {
		String ccaContent = this.content.trim();
		AmbientCCAWriter.write(ccaContent);
		
		this.parentDialog.dispose();
	}
	
	// Getters and Setters
	public JDialog getParentDialog() {
		return this.parentDialog;
	}

	public String getContent() {
		return this.content;
	}
}

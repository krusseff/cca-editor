package com.university.cca.buttons.cca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.dialogs.UnderConstructionDialog;
import com.university.cca.util.MouseCursorUtil;

/**
 * The button that opens the generated CCA file
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class OpenCCAFileButton extends JButton implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(OpenCCAFileButton.class);
	
	private JFrame parentFrame;
	
    public OpenCCAFileButton(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
        this.setText("Open CCA File");
        this.setIcon(new ImageIcon(Constants.OPEN_CCA_ICON_PATH));
        
        this.setCursor(MouseCursorUtil.getMouseHand());
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
        
        this.setEnabled(false);
        
        this.addActionListener(this);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Open the CCA file button is clicked");

		// TODO: Exact implementation here
		new UnderConstructionDialog(parentFrame);
	}
	
	// Getters and Setters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

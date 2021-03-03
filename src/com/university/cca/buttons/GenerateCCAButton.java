package com.university.cca.buttons;

import java.awt.Dimension;
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
 * The button that triggers generation of the CCA file
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class GenerateCCAButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(GenerateCCAButton.class);
	
	private JFrame parentFrame;
	
    public GenerateCCAButton(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
        this.setText("CCA Generator");
        this.setIcon(new ImageIcon(Constants.GENERATE_CCA_ICON_PATH));

        this.setPreferredSize(new Dimension(155, 35));
        this.setCursor(MouseCursorUtil.getMouseHand());
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
        
        this.addActionListener(this);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Generate CCA Button is clicked and generating CCA is triggered");
		
		// TODO: Implement the exact logic here
		
		new UnderConstructionDialog(parentFrame);
	}
	
	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}

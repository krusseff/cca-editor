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
import com.university.cca.dialogs.CreateAmbientDialog;
import com.university.cca.enums.AmbientType;
import com.university.cca.util.MouseCursorUtil;

public class ShopButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ShopButton.class);
	
	private JFrame parentFrame;
	
    public ShopButton(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	
        this.setText("Food Shop");
        this.setIcon(new ImageIcon(Constants.SHOP_ICON_PATH));
        this.setPreferredSize(new Dimension(135, 40));
        this.setCursor(MouseCursorUtil.getMouseHand());
        this.setIconTextGap(Constants.ICON_GAP_SIZE);
        
        this.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Shop Button is clicked");
		
		new CreateAmbientDialog(
			parentFrame, 
			"Create Shop", 
			"Please, enter a valid shop information",
			AmbientType.SHOP
		);
	}
	
	// Getters and Setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}

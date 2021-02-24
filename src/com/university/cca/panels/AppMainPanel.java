package com.university.cca.panels;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.buttons.CreateMessageButton;

/**
 * Main panel of the application
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AppMainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(AppMainPanel.class);
	
	private JFrame parentFrame;
	
	public AppMainPanel(JFrame parentFrame) {
		logger.info("Application main frame initialized");
		this.parentFrame = parentFrame;
		
		// this.setLayout(new CardLayout());
		// PanelsUtil.setPanelSize(this);
		
		addContentToPanel();
		
		parentFrame.add(this, BorderLayout.CENTER); 
		// parentFrame.add(this); 
		this.setVisible(true);
	}
	
	/**
	 * Method that adds content to the main panel of the application
	 */
	private void addContentToPanel() {
		this.add(new CreateMessageButton(parentFrame));
	}

	// Getters and setters
	public JFrame getParentFrame() {
		return parentFrame;
	}
}

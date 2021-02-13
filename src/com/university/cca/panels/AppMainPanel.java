package com.university.cca.panels;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.university.cca.buttons.CreateMessageButton;
import com.university.cca.util.PanelsUtil;

/**
 * Main panel of the application
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AppMainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JFrame parentFrame;
	
	public AppMainPanel(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setLayout(new FlowLayout());
		PanelsUtil.setPanelSize(this);
		
		addContentToPanel();
		
		parentFrame.add(this);
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

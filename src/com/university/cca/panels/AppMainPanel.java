package com.university.cca.panels;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.buttons.CreateMessageButton;
import com.university.cca.buttons.GenerateCCAButton;
import com.university.cca.constants.Constants;

/**
 * Main panel of the application
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AppMainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(AppMainPanel.class);
    
    private static final int X_COORDINATE = 0;
    private static final int Y_COORDINATE = 0;
	
	private JFrame parentFrame;
	
	public AppMainPanel(JFrame parentFrame) {
		logger.info("Application main panel with the map is initialized successfully");
		this.parentFrame = parentFrame;
		
		addContentToPanel();
		
		parentFrame.add(this);
		this.setVisible(true);
	}
	
	private void addContentToPanel() {
		this.add(new CreateMessageButton(parentFrame));
		this.add(new GenerateCCAButton(parentFrame));
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Image image = new ImageIcon(Constants.BACKGROUND_ICON_PATH).getImage();
        
        g.drawImage(image, X_COORDINATE, Y_COORDINATE, getWidth(), getHeight(), this);
    }
	
	// Getters and setters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

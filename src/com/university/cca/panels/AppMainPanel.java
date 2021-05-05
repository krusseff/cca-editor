package com.university.cca.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.buttons.CreateMessageButton;
import com.university.cca.buttons.cca.CCAGeneratorButton;
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

    // This is the size of the background image with the Plovdiv map
    // private static final int PANEL_WIDTH = 1697;
    // private static final int PANEL_HEIGTH = 1169;
    
    // This is the size of the background image with the Lovech map mini
    private static final int PANEL_WIDTH = 666;
    private static final int PANEL_HEIGTH = 524;
    
    private static final int X_COORDINATE = 0;
    private static final int Y_COORDINATE = 0;
    
	private JFrame parentFrame;
	
	public AppMainPanel(JFrame parentFrame) {
		logger.info("Application main panel with the map is initialized successfully");
		this.parentFrame = parentFrame;
		
		// This places the buttons on the left side of the panel
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		// Comment the preferred size of the panel to make the map smaller
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGTH));
		
		addContentToPanel();
		
		parentFrame.add(new JScrollPane(this));
		this.setVisible(true);
	}
	
	private void addContentToPanel() {
		this.add(new CreateMessageButton(this.parentFrame));
		this.add(new CCAGeneratorButton(this.parentFrame));
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(getLovechMap(), X_COORDINATE, Y_COORDINATE, this);
        
        // Use this line and comment the preferred size of the panel to make the map smaller
        // g.drawImage(image, X_COORDINATE, Y_COORDINATE, getWidth(), getHeight(), this);
    }
    
    @SuppressWarnings("unused")
	private Image getPlovdivMap() {
    	return new ImageIcon(Constants.BACKGROUND_ICON_PATH).getImage();
    }
    
    private Image getLovechMap() {
    	return new ImageIcon(Constants.BACKGROUND_LOVECH_ICON_PATH).getImage();
    }
	
	// Getters
	public JFrame getParentFrame() {
		return this.parentFrame;
	}
}

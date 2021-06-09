package com.university.cca.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.buttons.CreateMessageButton;
import com.university.cca.buttons.cca.CCAGeneratorButton;
import com.university.cca.constants.ApplicationModes;
import com.university.cca.constants.Constants;
import com.university.cca.frames.AppMainFrame;

/**
 * Main panel of the CCA Editor application.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AppMainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(AppMainPanel.class);
    
    // Start the application with the map of Lovech
    private static final boolean IS_SMART_CITY_LOVECH = true;

    // This is the size of the background image with the Plovdiv map
    private static final int PANEL_WIDTH_PLOVDIV = 1697;
    private static final int PANEL_HEIGTH_PLOVDIV = 1169;
    
    // This is the size of the background image with the Lovech map mini
    private static final int PANEL_WIDTH_LOVECH = 666;
    private static final int PANEL_HEIGTH_LOVECH = 524;
    
    // This is the size of the background image with the agriculture map
    private static final int PANEL_WIDTH_AGRICULTURE = 590;
    private static final int PANEL_HEIGTH_AGRICULTURE = 332;
    
    private static final int X_COORDINATE = 0;
    private static final int Y_COORDINATE = 0;
    
	private AppMainFrame parentFrame;
	
	public AppMainPanel(AppMainFrame parentFrame) {
		logger.info("Application main panel with the map is initialized successfully");
		this.parentFrame = parentFrame;
		
		// This places the buttons on the left side of the panel
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		// Comment the preferred size of the panel to make the map smaller
		setPreferredSize();
		
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
        
        switch (this.parentFrame.getApplicationMode()) {
			case ApplicationModes.SMART_CITY:
				g.drawImage(getSmartCityMap(), X_COORDINATE, Y_COORDINATE, this);
				break;
				
			case ApplicationModes.SMART_AGRICULTURE:
				g.drawImage(getSmartAgricultureMap(), X_COORDINATE, Y_COORDINATE, this);
				break;
	
			default:
				g.drawImage(getSmartCityMap(), X_COORDINATE, Y_COORDINATE, this);
				break;
		}
        
        // Use this line and comment the preferred size of the panel to make the map smaller
        // g.drawImage(image, X_COORDINATE, Y_COORDINATE, getWidth(), getHeight(), this);
    }
    
    private void setPreferredSize() {
    	
    	switch (this.parentFrame.getApplicationMode()) {
			case ApplicationModes.SMART_CITY:
				setSmartCityPreferredSize();
				break;
				
			case ApplicationModes.SMART_AGRICULTURE:
				setSmartAgriculturePreferredSize();
				break;
	
			default:
				setSmartCityPreferredSize();
				break;
		}
    }
    
    private void setSmartCityPreferredSize() {
    	if (IS_SMART_CITY_LOVECH) {
    		this.setPreferredSize(new Dimension(PANEL_WIDTH_LOVECH, PANEL_HEIGTH_LOVECH));
    	} else {
    		this.setPreferredSize(new Dimension(PANEL_WIDTH_PLOVDIV, PANEL_HEIGTH_PLOVDIV));
    	}
    }
    
    private void setSmartAgriculturePreferredSize() {
    	Dimension dimension = new Dimension(PANEL_WIDTH_AGRICULTURE, PANEL_HEIGTH_AGRICULTURE);
		this.setPreferredSize(dimension);
    }
    
    private Image getSmartCityMap() {
    	Image smartCityMap = null;
    	
    	if (IS_SMART_CITY_LOVECH) {
    		smartCityMap = getLovechMap();
    	} else {
    		smartCityMap = getPlovdivMap();
    	}
    	
    	return smartCityMap;
    }
    
	private Image getPlovdivMap() {
    	return new ImageIcon(Constants.BACKGROUND_ICON_PATH).getImage();
    }
    
    private Image getLovechMap() {
    	return new ImageIcon(Constants.BACKGROUND_LOVECH_ICON_PATH).getImage();
    }
    
    private Image getSmartAgricultureMap() {
    	return new ImageIcon(Constants.BACKGROUND_AGRICULTURE_ICON_PATH).getImage();
    }
	
	// Getters
	public AppMainFrame getParentFrame() {
		return this.parentFrame;
	}
}

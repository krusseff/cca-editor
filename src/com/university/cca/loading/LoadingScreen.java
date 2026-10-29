package com.university.cca.loading;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;

/**
 * Class that implemented the loading screen of the CCA Editor application.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class LoadingScreen {

	private static final Logger logger = LoggerFactory.getLogger(LoadingScreen.class);
	
	private static final int DISPLAY_TIME_MILLIS = 3000;
	
	private JFrame imageFrame;
	
	public LoadingScreen() { 
		// Default constructor to be able to create objects of type LoadingScreen
	}
	
	public void createImage() {
		SwingUtilities.invokeLater(() -> {
			try {
				imageFrame = new JFrame();
				imageFrame.setUndecorated(true);
				imageFrame.setVisible(true);
				imageFrame.setBounds(390, 230, 600, 300);
				imageFrame.setLayout(null);
					
				JPanel imagePanel = new JPanel();
				imagePanel.setVisible(true);
				imagePanel.setBounds(0, 0, 600, 300);
				imagePanel.setLayout(null);
				
				imageFrame.add(imagePanel);
				
				// set icon and title
				ImageIcon icon = new ImageIcon(Constants.IMAGES_ICON_JPG);
				imageFrame.setIconImage(icon.getImage());
				imageFrame.setTitle(Constants.APPLICATION_TITLE);
				
				JLabel imageLabel = new JLabel();
				imageLabel.setVisible(true);
				imageLabel.setLayout(new FlowLayout());
				imageLabel.setBounds(0, 0, 600, 300);
	
				imagePanel.add(imageLabel);
				
				// set loading screen
				ImageIcon loadingPicture = new ImageIcon(Constants.IMAGES_LOADING_SCREEN_JPG);
				imageLabel.setIcon(loadingPicture);
				
				// Hide the loading screen after the display period
				Timer hideTimer = new Timer(DISPLAY_TIME_MILLIS, e -> imageFrame.setVisible(false));
				hideTimer.setRepeats(false);
				hideTimer.start();
				
			} catch (Exception ex) {
				logger.error("Application loading screen error occurred: {}", ex.getMessage());
			}
		});
	}
}

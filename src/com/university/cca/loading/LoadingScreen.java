package com.university.cca.loading;

import java.awt.FlowLayout;
import java.awt.IllegalComponentStateException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	
	private static final int WAIT_TIME_MILLIS  = 2000;
	private static final int SLEEP_TIME_MILLIS = 3000;
	
	private JFrame imageFrame;
	
	public LoadingScreen() { 
		// Default constructor to be able to create objects of type LoadingScreen
	}
	
	public void createImage() {
		try {
			Thread loadingScreenThread = new Thread(() -> {
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
			});
			
			synchronized (loadingScreenThread) {
				loadingScreenThread.wait(WAIT_TIME_MILLIS);
				loadingScreenThread.start();
				Thread.sleep(SLEEP_TIME_MILLIS);
				imageFrame.setVisible(false);
			}
			
		} catch (IllegalComponentStateException | InterruptedException ex) {
			logger.error("Application loading screen error occurred: {}", ex.getMessage());
			Thread.currentThread().interrupt();
		}
	}
}

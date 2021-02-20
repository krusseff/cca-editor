package com.university.cca.loading;

import static com.university.cca.constants.Constants.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.IllegalComponentStateException;
import java.awt.FlowLayout;

/**
 * @author Konstantin Rusev
 * @version 1.0
 */
public class LoadingScreen {

	private static final Logger logger = LoggerFactory.getLogger(LoadingScreen.class);
	
	private JFrame imageFrame;
	
	public LoadingScreen() { 
		// Default constructor to be able to create objects of type LoadingScreen
	}
	
	@SuppressWarnings("deprecation")
	public void createImage() {
		try {
			Thread loadingScreenThread = new Thread(new Runnable() {
				public void run() {
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
				    ImageIcon icon = new ImageIcon(IMAGES_ICON_JPG);
				    imageFrame.setIconImage(icon.getImage());
				    imageFrame.setTitle(APPLICATION_TITLE);
					
				    JLabel imageLabel = new JLabel();
					imageLabel.setVisible(true);
					imageLabel.setLayout(new FlowLayout());
					imageLabel.setBounds(0, 0, 600, 300);

					imagePanel.add(imageLabel);
					
					// set loading screen
					ImageIcon loadingPicture = new ImageIcon(IMAGES_LOADING_SCREEN_JPG);
					imageLabel.setIcon(loadingPicture);
				}
			});
			
			synchronized(loadingScreenThread) {
				loadingScreenThread.wait(2000);
				loadingScreenThread.start();
				Thread.sleep(3000);
				loadingScreenThread.stop();
				imageFrame.setVisible(false);
			}
		}
		catch(IllegalComponentStateException | InterruptedException ex) {
			logger.error("Application loading screen error occurred: {}", ex.getMessage());
		}
	}
}

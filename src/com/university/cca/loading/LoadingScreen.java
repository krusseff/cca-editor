package com.university.cca.loading;

import static com.university.cca.constants.Constants.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.IllegalComponentStateException;
import java.awt.FlowLayout;

import java.lang.Thread;
import java.lang.Runnable;

/**
 * @author Konstantin Rusev
 * @version 1.0
 */
public class LoadingScreen {

	private JFrame imageFrame;
	
	public LoadingScreen() { 
		
	}
	
	@SuppressWarnings("deprecation")
	public void createImage() {
		try {
			Thread t = new Thread(new Runnable() {
				public void run() {
					imageFrame = new JFrame();
					imageFrame.setUndecorated(true);
					imageFrame.setVisible(true);
					imageFrame.setBounds(390, 230, 600, 300);
					imageFrame.setLayout(null); // TODO: new FlowLayout()
						
					JPanel imagePanel = new JPanel();
					imagePanel.setVisible(true);
					imagePanel.setBounds(0, 0, 600, 300);
					imagePanel.setLayout(null); // TODO: new FlowLayout()
					
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
			
			synchronized(t) {
				t.wait(2000);
				t.start();
				Thread.sleep(7000);
				t.stop();
				imageFrame.setVisible(false);
			}
		}
		catch(IllegalComponentStateException | InterruptedException ex) {
			System.out.println(ex.getMessage());
		}
	}
}

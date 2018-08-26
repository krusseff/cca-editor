package com.university.cca.frames;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.university.cca.constants.Constants;

/**
 * Main Frame of CCA Editor application
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class MainFrame {
	
	public void createMainFrame() {
		JFrame mainFrame = new JFrame();
		mainFrame.setResizable(true);
		mainFrame.setVisible(true);
		mainFrame.setLayout(null);
		mainFrame.setTitle(Constants.APPLICATION_TITLE);
		mainFrame.setName(Constants.MAIN_FRAME_NAME);
		mainFrame.setIconImage(createMainFrameIconImage());
		makeFrameHalfSize(mainFrame);
		makeFrameCenterPosition(mainFrame);
	}
	
	private Image createMainFrameIconImage() {
		ImageIcon img = new ImageIcon(Constants.IMAGES_ICON_JPG);
		Image mainFrameIconImage = img.getImage();
		return mainFrameIconImage;
	}
	
	private void makeFrameHalfSize(JFrame jFrame) {
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    jFrame.setSize(screenSize.width / 2, screenSize.height / 2);
	}
	
	private void makeFrameCenterPosition(JFrame jFrame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - jFrame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - jFrame.getHeight()) / 2);
	    jFrame.setLocation(x, y);
	}
}

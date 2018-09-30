package com.university.cca.frames;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.university.cca.constants.Constants;
import com.university.cca.util.CCAUtils;

/**
 * Main Frame of CCA Editor application
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class MainFrame {
	
	public void createMainFrame(JFrame mainFrame) {
		mainFrame.setResizable(true); // TODO Decide true or false
		mainFrame.setVisible(true);
		mainFrame.setLayout(null);
		mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle(Constants.APPLICATION_TITLE);
		mainFrame.setName(Constants.MAIN_FRAME_NAME);
		mainFrame.setIconImage(createMainFrameIconImage());
		makeFrameHalfSize(mainFrame);
		makeFrameCenterPosition(mainFrame);
	}
	
	private Image createMainFrameIconImage() {
		ImageIcon img = new ImageIcon(Constants.IMAGES_ICON_JPG);
		return img.getImage();
	}
	
	private void makeFrameHalfSize(JFrame jFrame) {
		CCAUtils ccaUtils = new CCAUtils();
	    Dimension screenSize = ccaUtils.getScreenSize();
	    jFrame.setSize(screenSize.width / 2, screenSize.height / 2);
	}
	
	private void makeFrameCenterPosition(JFrame jFrame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - jFrame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - jFrame.getHeight()) / 2);
	    jFrame.setLocation(x, y);
	}
}

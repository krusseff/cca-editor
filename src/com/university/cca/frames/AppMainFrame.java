package com.university.cca.frames;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.university.cca.constants.Constants;
import com.university.cca.panels.AppMainPanel;
import com.university.cca.util.CCAUtils;

/**
 * Main Frame of CCA Editor application
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AppMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public AppMainFrame() {
		this.setTitle(Constants.APPLICATION_TITLE);
		this.setName(Constants.MAIN_FRAME_NAME);
		this.setIconImage(createMainFrameIconImage());

		// TODO: Ако нещо не се дисплейва може да е от layout-a
		this.setLayout(new FlowLayout());
		this.setResizable(true);
		makeFrameHalfSize();
		makeFrameCenterPosition();
		// this.setSize(500, 500); // TODO
		
		// Add content to the frame
		AppMainPanel mainPanel = new AppMainPanel(this);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private Image createMainFrameIconImage() {
		ImageIcon img = new ImageIcon(Constants.IMAGES_ICON_JPG);
		return img.getImage();
	}
	
	private void makeFrameHalfSize() {
		CCAUtils ccaUtils = new CCAUtils();
	    Dimension screenSize = ccaUtils.getScreenSize();
	    this.setSize(screenSize.width / 2, screenSize.height / 2);
	}
	
	private void makeFrameCenterPosition() {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	}
}

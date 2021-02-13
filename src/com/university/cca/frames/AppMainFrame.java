package com.university.cca.frames;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.university.cca.constants.Constants;
import com.university.cca.panels.AmbientButtonsPanel;
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
	private static final int GRID_ROWS = 0;
	private static final int GRID_COLS = 3; // TODO: Maybe 4

	public AppMainFrame() {
		this.setTitle(Constants.APPLICATION_TITLE);
		this.setName(Constants.MAIN_FRAME_NAME);
		this.setIconImage(createMainFrameIconImage());
		this.setResizable(true);
		
		this.setLayout(getGridLayout());
		makeFrameHalfSize();
		makeFrameCenterPosition();
		
		addContentToFrame();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * Method that adds content to the main frame of the application
	 */
	private void addContentToFrame() {
		new AmbientButtonsPanel(this);
		new AppMainPanel(this);
	}
	
	/**
	 * Set the image of the main frame of the application
	 */
	private Image createMainFrameIconImage() {
		ImageIcon image = new ImageIcon(Constants.IMAGES_ICON_JPG);
		return image.getImage();
	}
	
	/**
	 * Set the size of the main frame of the application
	 */
	private void makeFrameHalfSize() {
		CCAUtils ccaUtils = new CCAUtils();
	    Dimension screenSize = ccaUtils.getScreenSize();
	    this.setSize(screenSize.width / 2, screenSize.height / 2);
	}
	
	/**
	 * Set the position of the main frame of the application
	 */
	private void makeFrameCenterPosition() {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	}
	
	/**
	 * Set the Grid Layout of the main frame of the application
	 */
	private GridLayout getGridLayout() {
		GridLayout layout = new GridLayout(GRID_ROWS, GRID_COLS);
        layout.setHgap(10);
        layout.setVgap(10);
        
        return layout;
	}
}

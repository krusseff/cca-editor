package com.university.cca.main;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.university.cca.constants.Constants;
import com.university.cca.frames.AppMainFrame;
import com.university.cca.loading.LoadingScreen;
import com.university.cca.menu.items.AboutMenuItems;
import com.university.cca.menu.items.HelpMenuItems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main Class of CCA Editor application. 
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class Main {
	
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String... args) {
		logger.info("CCA Editor application starting... :)");
		createLoadingScreen();

		new AppMainFrame();
		
		// JMenuItem aboutCCA = new JMenuItem(Constants.ABOUT_CCA, getAboutIconImage());
		// JMenuItem aboutCCAEditor = new JMenuItem(Constants.ABOUT_CCA_EDITOR, getAboutIconImage());
		// createAboutCCAMenuItem(about, aboutCCA);
		// createAboutCCAEditorMenuItem(about, aboutCCAEditor);

		// JMenuItem faq = new JMenuItem(Constants.FAQ, getFAQIconImage());
		// createFAQMenuItem(help, faq);
	}
	
	public static void createLoadingScreen() {
		LoadingScreen loadingScreen = new LoadingScreen();
		loadingScreen.createImage();
	}
	
	public static ImageIcon getAboutIconImage() {
		return new ImageIcon(Constants.ABOUT_ICON_PATH);
	}
	
	public static void createAboutCCAMenuItem(JMenu aboutMenu, JMenuItem aboutCCAMenuItem) {
		AboutMenuItems aboutMenuItems = new AboutMenuItems();
		aboutMenuItems.addAboutCCAMenuItem(aboutMenu, aboutCCAMenuItem);
	}
	
	public static void createAboutCCAEditorMenuItem(JMenu aboutMenu, JMenuItem aboutCCAEditorMenuItem) {
		AboutMenuItems aboutMenuItems = new AboutMenuItems();
		aboutMenuItems.addAboutCCAEditorMenuItem(aboutMenu, aboutCCAEditorMenuItem);
	}
	
	public static ImageIcon getFAQIconImage() {
		return new ImageIcon(Constants.HELP_ICON_PATH);
	}
	
	public static void createFAQMenuItem(JMenu helpMenu, JMenuItem faqMenuItem) {
		HelpMenuItems helpMenuItems = new HelpMenuItems();
		helpMenuItems.addFAQMenuItem(helpMenu, faqMenuItem);
	}
}

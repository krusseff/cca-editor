package com.university.cca.main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.university.cca.constants.Constants;
import com.university.cca.frames.MainFrame;
import com.university.cca.loading.LoadingScreen;
import com.university.cca.menu.MainMenu;
import com.university.cca.menu.MainMenuBar;
import com.university.cca.menu.items.AboutMenuItems;
import com.university.cca.menu.items.HelpMenuItems;
import com.university.cca.panels.MainPanel;

/**
 * Main Class of CCA Editor application. 
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class Main {
	
	public static void main(String... args) {
		createLoadingScreen();
		
		JFrame jMainFrame = new JFrame();
		createMainFrame(jMainFrame);
		
		JMenuBar jMainMenuBar = new JMenuBar();
		createMainMenuBar(jMainMenuBar, jMainFrame);

		// TODO Main Menu - Work in progress
		JMenu file = new JMenu(Constants.FILE);
		JMenu edit = new JMenu(Constants.EDIT);
		JMenu view = new JMenu(Constants.VIEW);
		JMenu help = new JMenu(Constants.HELP);
		JMenu about = new JMenu(Constants.ABOUT);
		createMainMenu(jMainMenuBar, file, edit, view, help, about);
		
		JPanel jMainPanel = new JPanel();
		createMainPanel(jMainFrame, jMainPanel);
		
		JMenuItem aboutCCA = new JMenuItem(Constants.ABOUT_CCA, getAboutIconImage());
		JMenuItem aboutCCAEditor = new JMenuItem(Constants.ABOUT_CCA_EDITOR, getAboutIconImage());
		createAboutCCAMenuItem(about, aboutCCA);
		createAboutCCAEditorMenuItem(about, aboutCCAEditor);

		JMenuItem faq = new JMenuItem(Constants.FAQ, getFAQIconImage());
		createFAQMenuItem(help, faq);
	}
	
	public static void createLoadingScreen() {
		LoadingScreen loadingScreen = new LoadingScreen();
		loadingScreen.createImage();
	}
	
	public static void createMainFrame(JFrame frame) {
		MainFrame mainFrame = new MainFrame();
		mainFrame.createMainFrame(frame);
	}
	
	public static void createMainMenuBar(JMenuBar jMainMenuBar, JFrame jMainFrame) {
		MainMenuBar mainMenuBar = new MainMenuBar();
		mainMenuBar.createMainMenuBar(jMainMenuBar, jMainFrame);
	}
	
	public static void createMainMenu(JMenuBar jMainMenuBar, JMenu file, JMenu edit,
									  JMenu view, JMenu help, JMenu about) {
		MainMenu mainMenu = new MainMenu();
		mainMenu.createMainMenu(file, edit, view, help, about, jMainMenuBar);
	}
	
	public static void createMainPanel(JFrame jMainFrame, JPanel jMainPanel) {
		MainPanel mainPanel = new MainPanel();
		mainPanel.createMainPanel(jMainFrame, jMainPanel);
	}
	
	public static ImageIcon getAboutIconImage() {
		ImageIcon aboutIcon = new ImageIcon(Constants.ABOUT_ICON_PATH);
		return aboutIcon;
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
		ImageIcon faqIcon = new ImageIcon(Constants.HELP_ICON_PATH);
		return faqIcon;
	}
	
	public static void createFAQMenuItem(JMenu helpMenu, JMenuItem faqMenuItem) {
		HelpMenuItems helpMenuItems = new HelpMenuItems();
		helpMenuItems.addFAQMenuItem(helpMenu, faqMenuItem);
	}
}

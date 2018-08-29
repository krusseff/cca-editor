package com.university.cca.main;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import com.university.cca.frames.MainFrame;
import com.university.cca.loading.LoadingScreen;
import com.university.cca.menu.MainMenuBar;

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
		
		// TODO Menu Bar - Work in progress
		JMenuBar jMainMenuBar = new JMenuBar();
		createMainMenu(jMainMenuBar, jMainFrame);
	}
	
	public static void createLoadingScreen() {
		LoadingScreen loadingScreen = new LoadingScreen();
		loadingScreen.createImage();
	}
	
	public static void createMainFrame(JFrame frame) {
		MainFrame mainFrame = new MainFrame();
		mainFrame.createMainFrame(frame);
	}
	
	public static void createMainMenu(JMenuBar jMainMenuBar, JFrame jMainFrame) {
		MainMenuBar mainMenuBar = new MainMenuBar();
		mainMenuBar.createMainMenuBar(jMainMenuBar, jMainFrame);
	}
}

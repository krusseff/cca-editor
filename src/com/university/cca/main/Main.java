package com.university.cca.main;

import com.university.cca.frames.MainFrame;
import com.university.cca.loading.LoadingScreen;

/**
 * Main Class of CCA Editor application. 
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class Main {
	public static void main(String... args) {
		createLoadingScreen();
		createMainFrame();
		// TODO Next Step: Create MenuBar and Menu
	}
	
	public static void createLoadingScreen() {
		LoadingScreen loadingScreen = new LoadingScreen();
		loadingScreen.createImage();
	}
	
	public static void createMainFrame() {
		MainFrame mainFrame = new MainFrame();
		mainFrame.createMainFrame();
	}
}

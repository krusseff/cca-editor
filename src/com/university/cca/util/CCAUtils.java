package com.university.cca.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.university.cca.loading.LoadingScreen;

/**
 * Base utility class with utility methods for CCA Editor application
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CCAUtils {
	
	public CCAUtils() {
		// Constructor that prevents creating an object of type CCAUtils
	}
	
	public static void createLoadingScreen() {
		LoadingScreen loadingScreen = new LoadingScreen();
		loadingScreen.createImage();
	}
	
	public Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
}

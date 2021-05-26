package com.university.cca.main;

import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.CCAUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class and entry point of the CCA Editor application. 
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class Main {
	
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String... args) {
		runApplication();
	}
	
	/**
	 * Method, which main responsibility is to start the CCA Editor Application
	 * 
	 */
	private static void runApplication() {
		logger.info("CCA Editor application starting... :)");

		CCAUtils.createLoadingScreen();

		new AppMainFrame();
	}
}

package com.university.cca.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.frames.AppMainFrame;
import com.university.cca.util.AppModeUtil;
import com.university.cca.util.CCAUtils;

/**
 * Main class and entry point of the CCA Editor application. 
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class Main {
	
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String... args) {
		int applicationMode = AppModeUtil.findApplicationMode(args);
		
		runApplication(applicationMode);
	}
	
	/**
	 * Method, which main responsibility is to start the CCA Editor Application
	 */
	private static void runApplication(int applicationMode) {
		logger.info("CCA Editor application starting in application mode: {}", applicationMode);

		CCAUtils.createLoadingScreen();

		new AppMainFrame(applicationMode);
	}
}

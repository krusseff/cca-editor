package com.university.cca.util;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.ApplicationModes;

/**
 * Utility class with utility methods for the CCA Editor application mode.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AppModeUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(AppModeUtil.class);
	
	private AppModeUtil() {
		// Constructor that prevents creating an object of type AppModeUtil class
	}
	
	public static int findApplicationMode(String[] appArguments) {
		String argsToString = Arrays.toString(appArguments);
		int applicationMode = 0;
		
		logger.info("Application arguments: {}", argsToString);
		
		if (appArguments.length > 0) {
			int appModeFromArgs = convertAppModeToInt(appArguments[0]);
			applicationMode = getApplicationMode(appModeFromArgs);
		} else {
			applicationMode = ApplicationModes.SMART_CITY;
			logger.warn("Application argumetns not found: {}", argsToString);
			logger.warn("Starting in default application mode: {}", applicationMode);
		}
		
		return applicationMode;
	}
	
	private static int getApplicationMode(int appModeFromArgs) {
		int applicationMode;
		
		if (isValidApplicationMode(appModeFromArgs)) {
			applicationMode = appModeFromArgs;
			logger.info("Starting the application in mode: {}", applicationMode);
		} else {
			applicationMode = ApplicationModes.SMART_CITY;
			logger.warn("Invalid application mode: {}", appModeFromArgs);
			logger.warn("Starting in default application mode: {}", applicationMode);
		}
		
		return applicationMode;
	}
	
	private static int convertAppModeToInt(String appModeAsString) {
		int appModeAsInt;
		
		try {
			appModeAsInt = Integer.parseInt(appModeAsString);
		} catch (NumberFormatException ex) {
			appModeAsInt = 0;
			logger.error("Cannot convert string: {} to integer value", appModeAsString);
			logger.warn("Replacing the invalid value with the defaul application mode: {}", appModeAsInt);
		}
		
		return appModeAsInt;
	}

	private static boolean isValidApplicationMode(int applicationMode) {
		return applicationMode == ApplicationModes.SMART_CITY ||
			   applicationMode == ApplicationModes.SMART_AGRICULTURE;
	}
}

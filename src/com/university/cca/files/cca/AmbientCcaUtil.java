package com.university.cca.files.cca;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility methods related to the functionality that reads and writes from/to CCA files.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientCCAUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(AmbientCCAUtil.class);
	
	private AmbientCCAUtil() {
		// Prevent creating an object of type AmbientCcaUtil
	}
	
	/**
	 * Method that creates a file if it does not exist.
	 */
	public static void createFileIfDoesNotExist(String filePath) {
		File file = new File(filePath);
		
		if (!file.exists()) {
			try {
				boolean result = file.createNewFile();
				logger.info("The file: {} is created with status: {}", filePath, result);
			} catch (IOException e) {
				logger.error("Unable to create a file: {}", e.getMessage());
				logger.error("Exiting the program... :(");
				System.exit(2);
			}
		} else {
			logger.info("The file: {} already exists.", filePath);
		}	
	}
}

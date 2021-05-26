package com.university.cca.files.cca;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;

/**
 * Utility methods related to the functionality that reads and writes from/to CCA files.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientCCAUtility {
	
	private static final Logger logger = LoggerFactory.getLogger(AmbientCCAUtility.class);
	
	private AmbientCCAUtility() {
		// Prevent creating an object of type AmbientCcaUtil
	}
	
	/**
	 * Method that creates a file if it does not exist.
	 * 
	 */
	public static void createFileIfDoesNotExist(String filePath) {
		File file = new File(filePath);
		
		if (!file.exists()) {
			try {
				boolean result = file.createNewFile();
				logger.info("The file: {} is created with status: {}", filePath, result);
			} catch (IOException ex) {
				logger.error("Unable to create a file. Exception: {}", ex.getMessage());
				logger.error("Exiting the program... :(");
				System.exit(2);
			}
		} else {
			logger.info("The file with name: {} already exists.", filePath);
		}	
	}
	
	/**
	 * Method that checks for Ambients CCA file existence
	 * 
	 * @return <code>true</code> if the CCA file already exists
	 * 		   <code>false</code> if the CCA file does not exist
	 * 
	 */
	public static boolean isFileCreated() {
		String filePath = Constants.AMBIENTS_CCA_FILE_PATH;

		File file = new File(filePath);
		
		return file.exists();
	}
}

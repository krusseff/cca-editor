package com.university.cca.files.cca;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;

/**
 * Utility methods related to the writting an ambient information to the CCA file.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientCCAWriter {
	
	private static final Logger logger = LoggerFactory.getLogger(AmbientCCAWriter.class);
	
	private static final boolean SHOULD_APPEND_TO_FILE = false;
	private static final String NEW_LINE = "\n";
	private static final int EXIT_CODE_2 = 2;
	
	private AmbientCCAWriter() {
		// Prevent creating an object of type AmbientCCAWriter class
	}
	
	public static void write(String data) {
		writeToFile(Constants.AMBIENTS_CCA_FILE_PATH, data);
	}
	
	public static void write(String filePath, String data) {
		writeToFile(filePath, data);
	}
	
	/**
	 * Method that writes the data to the specified file with .cca extension.
	 * The data will be re-written on each execution in order to handle any changes.
	 */
	private static void writeToFile(String filePath, String data) {
		AmbientCCAUtility.createFileIfDoesNotExist(filePath);
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, SHOULD_APPEND_TO_FILE))) {
			writer.write(data);
			writer.write(NEW_LINE);
			logger.info("The data: {} is written successfully to the CCA file: {}", data, filePath);
		} catch (IOException ex) {
			logger.error("Unable to write the data: {} to the CCA file: {}, Exception: {}", data, filePath, ex.getMessage());
			logger.error("Exiting the program... :(");
			System.exit(EXIT_CODE_2);
		}
	}
}

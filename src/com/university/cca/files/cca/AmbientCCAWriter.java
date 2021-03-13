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
	private static final boolean APPEND_TO_FILE = true;
	private static final String NEW_LINE = "\n";

	private AmbientCCAWriter() {
		// Prevent creating an object of type AmbientCCAWriter
	}
	
	public static void write(String data) {
		writeToFile(data);
	}
	
	private static void writeToFile(String data) {
		String filePath = Constants.AMBIENTS_CCA_FILE_PATH;
		AmbientCCAUtil.createFileIfDoesNotExist(filePath);
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, APPEND_TO_FILE))) {
			writer.write(data);
			writer.write(NEW_LINE);
			logger.info("The data: {} is written successfully to the CCA file", data);
		} catch (IOException e) {
			logger.error("Unable to write the data to the CCA file: {}", e.getMessage());
			logger.error("Exiting the program... :(");
			System.exit(2);
		}
	}
}

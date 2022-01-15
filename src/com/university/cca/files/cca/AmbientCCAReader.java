package com.university.cca.files.cca;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.Constants;
import com.university.cca.exceptions.FileReadFailedException;

/**
 * Utility methods related to the reading an ambient information from the CCA file.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientCCAReader {
	
	private static final Logger logger = LoggerFactory.getLogger(AmbientCCAReader.class);
	
	private static final String NEW_LINE = "\n";

	private AmbientCCAReader() {
		// Prevent creating an object of type AmbientCCAReader class
	}

	/**
	 * Method that reads the CCA scenario from the CCA file (if the file already exists).
	 */
	public static String read() throws FileNotFoundException, FileReadFailedException {
		String filePath = Constants.AMBIENTS_CCA_FILE_PATH;
	
		// if the file does not exist throw an exception
		if (!AmbientCCAUtility.isFileCreated()) {
			logger.error("The CCA file is not found on the specified path: {}", filePath);
			throw new FileNotFoundException(filePath);
		}
		
		return readCCAFile(filePath);
	}
	
	/**
	 * Method that reads the whole file as a stream and then converts and returns it as string
	 */
	private static String readCCAFile(String filePath) throws FileReadFailedException {
		try(Stream<String> fileStream = Files.lines(Paths.get(filePath))) {
			return fileStream.collect(Collectors.joining(NEW_LINE));
		} catch (IOException ex) {
			logger.error("Unable to read the data from the CCA file: {}, Exception: {}", filePath, ex.getMessage());
			throw new FileReadFailedException(ex.getMessage());
		}
	}
}

package com.university.cca.email;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.cca.constants.Constants;

public class EmailConfigReader {

	private static final Logger logger = LoggerFactory.getLogger(EmailConfigReader.class);
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

	private EmailConfigReader() {
		// prevent creating an object of type EmailConfigReader
	}

	public static EmailConfiguration getEmailConfigurations() {
		File emailConfigFile = createIfNotExists();
		EmailConfiguration emailConfig;
		
		try {
			emailConfig = objectMapper.readValue(emailConfigFile, EmailConfiguration.class);
			
			logger.info("Successfully read the email configurations from the file: {}", emailConfigFile);
		} catch (IOException ex) {
			emailConfig = null;
			
			logger.error("Unable to read the email configurations from the file: {}", ex.getMessage());
		}
		
		return emailConfig;
	}
	
	public static File createIfNotExists() {
		File file = new File(Constants.EMAIL_CONFIG_FILE_PATH);
		
		if (!file.exists()) {
			try {
				boolean result = file.createNewFile();
				logger.info("The email configuration file: {} is created with status: {}", file.getAbsolutePath(), result);
			} catch (IOException ex) {
				logger.error("Unable to create email configuration file: {}", ex.getMessage());
				logger.error("Exiting the program... :(");
				System.exit(2);
			}
		} else {
			logger.info("The file: {} already exists.", file.getAbsolutePath());
		}	
		
		return file;
	}
}

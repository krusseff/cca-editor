package com.university.cca.files.cca;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.CCAConstants;

/**
 * Utility methods related to the reading a CCA ambient templates from files.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class TemplateCCAReader {
	
	private static final Logger logger = LoggerFactory.getLogger(TemplateCCAReader.class);
	
	private static final String NEW_LINE = "\n";
	
	private TemplateCCAReader() {
		// Prevent creating an object of type TemplateCCAReader
	}
	
	public static String readAmbientDelimiterTemplate() {
		return readFromFile(CCAConstants.AMBIENTS_DELIMITER_FILE_PATH);
	}
	
	public static String readSendReceiveAmbientTemplate() {
		return readFromFile(CCAConstants.SEND_RECEIVE_AMBIENT_FILE_PATH);
	}
	
	public static String readSendSiblingAmbientTemplate() {
		return readFromFile(CCAConstants.SEND_TO_SIBLING_AMBIENT_FILE_PATH);
	}
	
	public static String readReceiveSiblingAmbientTemplate() {
		return readFromFile(CCAConstants.RECEIVE_FROM_SIBLING_FILE_PATH);
	}
	
	public static String readSendReceiveOneAmbientTemplate() {
		return readFromFile(CCAConstants.SEND_RECEIVE_ONE_AMBIENT_FILE_PATH);
	}
	
	public static String readReceiveSendOneAmbientTemplate() {
		return readFromFile(CCAConstants.RECEIVE_SEND_ONE_AMBIENT_FILE_PATH);
	}
	
	/**
	 * Method, which main responsibility is to read from the CCA Template files.
	 */
	private static String readFromFile(String filePath) {
		AmbientCCAUtility.createFileIfDoesNotExist(filePath);
		
	    StringBuilder resultStringBuilder = new StringBuilder();
	    
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
	        String fileLine;
	        
	        while ((fileLine = br.readLine()) != null) {
	            resultStringBuilder.append(fileLine).append(NEW_LINE);
	        }
	    } catch (IOException ex) {
	    	logger.error("Unable to read the data from the file: {}", ex.getMessage());
		}
	    
	    logger.info("Successfully read all the information from the file: {}", filePath);
	    
	    return resultStringBuilder.toString();
	}
}

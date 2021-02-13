package com.university.cca.files.csv;

import java.io.File;
import java.io.IOException;

import com.university.cca.entities.Ambient;
import com.university.cca.entities.Message;

/**
 * Utility methods related to the functionality that reads and writes from/to CSV file.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientCsvUtil {
	
	private AmbientCsvUtil() {
		// Prevent creating an object of type AmbientCsvUtil
	}
	
	/**
	 * Method that creates a csv file if it does not exist.
	 */
	public static void createFileIfDoesNotExist(String filePath) {
		File file = new File(filePath);
		
		if (!file.exists()) {
			try {
				boolean result = file.createNewFile();
				System.out.println("CSV file: " + filePath + " is created with status: " + result);
			} catch (IOException e) {
				System.err.println("Unable to create CSV file: " + e.getMessage());
				System.err.println("Exiting the program...");
				System.exit(2);
			}
		} else {
			System.out.println("File: " + filePath + " already exists.");
		}	
	}
	
	/**
	 * Method that converts from CsvAmbientBean object to Ambient object.
	 */
	public static Ambient convertToAmbient(CsvAmbientBean ambientBean) {
		Ambient ambient = new Ambient();
		ambient.setName(ambientBean.getName());
		ambient.setLocation(ambientBean.getLocation());
		ambient.setStaticAmbient(ambientBean.isStaticAmbient());
		ambient.setParentAmbient(ambientBean.getParentAmbient());
		ambient.setAmbientType(ambientBean.getAmbientType());
		ambient.setLatitude(ambientBean.getLatitude());
		ambient.setLongitude(ambientBean.getLongitude());
		
		return ambient;
	}
	
	/**
	 * Method that converts from Ambient object to CsvAmbientBean object.
	 */
	public static CsvAmbientBean convertToCsvAmbientBean(Ambient ambient) {
		CsvAmbientBean ambientBean = new CsvAmbientBean();
		ambientBean.setName(ambient.getName());
		ambientBean.setLocation(ambient.getLocation());
		ambientBean.setStaticAmbient(ambient.isStaticAmbient());
		ambientBean.setParentAmbient(ambient.getParentAmbient());
		ambientBean.setAmbientType(ambient.getAmbientType());
		ambientBean.setLatitude(ambient.getLatitude());
		ambientBean.setLongitude(ambient.getLongitude());
		
		return ambientBean;
	}
	
	/**
	 * Method that converts from CsvMessageBean object to Message object.
	 */
	public static Message convertToMessage(CsvMessageBean messageBean) {
		Message ambientMessage = new Message();
		ambientMessage.setSenderAmbient(messageBean.getSenderAmbient());
		ambientMessage.setRecipientAmbient(messageBean.getRecipientAmbient());
		ambientMessage.setMessage(messageBean.getAmbientMessage());
		
		return ambientMessage;
	}
	
	/**
	 * Method that converts from Message object to CsvMessageBean object.
	 */
	public static CsvMessageBean convertToCsvMessageBean(Message ambientMessage) {
		CsvMessageBean messageBean = new CsvMessageBean();
		messageBean.setSenderAmbient(ambientMessage.getSenderAmbient());
		messageBean.setRecipientAmbient(ambientMessage.getRecipientAmbient());
		messageBean.setAmbientMessage(ambientMessage.getMessage());
		
		return messageBean;
	}
}

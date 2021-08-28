package com.university.cca.files.csv;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.entities.Ambient;
import com.university.cca.entities.AmbientStatistics;
import com.university.cca.entities.Message;

/**
 * Utility methods related to the functionality that reads and writes from/to CSV file.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CsvUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(CsvUtil.class);
	
	private CsvUtil() {
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
				logger.info("The CSV file: {} is created with status: {}", filePath, result);
			} catch (IOException ex) {
				logger.error("Unable to create the CSV file: {}", ex.getMessage());
				logger.error("Exiting the program... :(");
				System.exit(2);
			}
		} else {
			logger.info("The file: {} already exists.", filePath);
		}	
	}

	/**
	 * Method that checks for CSV file existence
	 * 
	 * @return <code>true</code> if the specified file already exists
	 * 		   <code>false</code> if the specified file does not exist
	 */
	public static boolean isFileCreated(String filePath) {
		File file = new File(filePath);
		
		return file.exists();
	}
	
	/**
	 * Method that converts from CsvAmbientBean object to Ambient object.
	 */
	public static Ambient convertToAmbient(CsvAmbientBean ambientBean) {
		Ambient ambient = new Ambient();
		ambient.setName(ambientBean.getName());
		ambient.setAmbientType(ambientBean.getAmbientType());
		ambient.setLocation(ambientBean.getLocation());
		ambient.setLatitude(ambientBean.getLatitude());
		ambient.setLongitude(ambientBean.getLongitude());
		ambient.setStaticAmbient(ambientBean.isStaticAmbient());
		ambient.setActiveAmbient(ambientBean.isActiveAmbient());
		ambient.setParentAmbient(ambientBean.getParentAmbient());
		
		return ambient;
	}
	
	/**
	 * Method that converts from Ambient object to CsvAmbientBean object.
	 */
	public static CsvAmbientBean convertToCsvAmbientBean(Ambient ambient) {
		CsvAmbientBean ambientBean = new CsvAmbientBean();
		ambientBean.setName(ambient.getName());
		ambientBean.setAmbientType(ambient.getAmbientType());
		ambientBean.setLocation(ambient.getLocation());
		ambientBean.setLatitude(ambient.getLatitude());
		ambientBean.setLongitude(ambient.getLongitude());
		ambientBean.setStaticAmbient(ambient.isStaticAmbient());
		ambientBean.setActiveAmbient(ambient.isActiveAmbient());
		ambientBean.setParentAmbient(ambient.getParentAmbient());
		
		return ambientBean;
	}
	
	/**
	 * Method that converts from CsvMessageBean object to Message object.
	 */
	public static Message convertToMessage(CsvMessageBean messageBean) {
		Message ambientMessage = new Message();
		ambientMessage.setSenderAmbient(messageBean.getSenderAmbient());
		ambientMessage.setRecipientAmbient(messageBean.getRecipientAmbient());
		ambientMessage.setRespondToMessage(messageBean.getRespondToMessage());
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
		messageBean.setRespondToMessage(ambientMessage.getRespondToMessage());
		messageBean.setAmbientMessage(ambientMessage.getMessage());
		
		return messageBean;
	}

	/**
	 * Method that converts from AmbientStatistics to CsvAmbientStatisticsBean
	 */
	public static CsvAmbientStatisticsBean convertToCsvAmbientStatisticsBean(AmbientStatistics ambientStats) {
		CsvAmbientStatisticsBean ambientStatsBean = new CsvAmbientStatisticsBean();
		ambientStatsBean.setTotalCount(ambientStats.getTotalCount());
		ambientStatsBean.setStaticCount(ambientStats.getStaticCount());
		ambientStatsBean.setNonStaticCount(ambientStats.getNonStaticCount());
		ambientStatsBean.setActiveCount(ambientStats.getActiveCount());
		ambientStatsBean.setInactiveCount(ambientStats.getInactiveCount());
		
		return ambientStatsBean;
	}
}

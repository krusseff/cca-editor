package com.university.cca.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.university.cca.entities.Message;
import com.university.cca.entities.MessageStatistics;
import com.university.cca.files.csv.AmbientCSVReader;

/**
 * Holds methods for fetching message statistics from given datasource.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class MessageStatisticsRepository {
	
	private MessageStatisticsRepository() {
		// Prevent creating an object of type MessageStatisticsRepository
	}
	
	/**
	 * Fetches and calculates all message statistics and return them as a map
	 */
	public static Map<String, MessageStatistics> getMessageStatistics() {
		Map<String, MessageStatistics> messageStatistics = new HashMap<>();

		// TODO: implement the logic for calculating statistics
		for (Message message : getAllMessages()) {
			
		}
		
		return messageStatistics;
	}

	/**
	 * Fetches all messages from given data source
	 */
	private static List<Message> getAllMessages() {
		
		return AmbientCSVReader.getAllMessages();
	}
}

package com.university.cca.repositories;

import java.util.List;

import com.university.cca.entities.Message;
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
	 * Fetches and calculates all message statistics
	 */
	public static void getMessageStatistics() { // TODO: Change return type

		// TODO: implement the logic for calculating statistics
		for (Message message : getAllMessages()) {
			
		}
		
	}

	/**
	 * Fetches all messages from given data source
	 */
	private static List<Message> getAllMessages() {
		
		return AmbientCSVReader.getAllMessages();
	}
}

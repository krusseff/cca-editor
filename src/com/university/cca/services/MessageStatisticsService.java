package com.university.cca.services;

import java.util.ArrayList;
import java.util.List;

import com.university.cca.entities.MessageStatistics;

/**
 * Holds methods for fetching message statistics.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class MessageStatisticsService {

	private MessageStatisticsService() {
		// Prevent creating an object of type MessageStatisticsService
	}
	
	/**
	 * Fetches all message statistics as a list
	 */
	public static List<MessageStatistics> getAllMessageStatistics() {
		
		// TODO: Mocked data
		List<MessageStatistics> messageStatistics = new ArrayList<>();
		messageStatistics.add(new MessageStatistics("PERSONAL ASSISTANT", 2, 5));
		messageStatistics.add(new MessageStatistics("UNIVERSITY", 7, 4));
		messageStatistics.add(new MessageStatistics("BUS STATION", 10, 1));
		
		return messageStatistics;
	}
}

package com.university.cca.services;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.university.cca.entities.MessageStatistics;
import com.university.cca.repositories.MessageStatisticsRepository;

/**
 * Holds methods for fetching ambient message statistics.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class MessageStatisticsService {
	
	private static final int ZERO_VALUE = 0;

	private MessageStatisticsService() {
		// Prevent creating an object of type MessageStatisticsService
	}
	
	/**
	 * Fetches all message statistics, sort by ambient name and return them as list
	 */
	public static List<MessageStatistics> getMessageStatisticsSortedByAmbientName() {
		
		return getMessageStatistics()
				.values()
				.stream()
				.sorted(Comparator.comparing(MessageStatistics::getAmbientName))
				.collect(Collectors.toList());
	}
	
	/**
	 * Fetches all message statistics and return them as a hash map
	 */
	public static Map<String, MessageStatistics> getMessageStatistics() {
		
		return MessageStatisticsRepository.getMessageStatistics();
	}
	
	/**
	 * Checks for available message statistics.
	 * 
	 * @return false - If the size of the statistics is zero, then there are no available statistics.
	 * 		   true  - If the size of the statistics is greater than zero, then there are available statistics.
	 */
	public static boolean hasStatistics(List<MessageStatistics> statistics) {
		
		return statistics.size() > ZERO_VALUE;
	}
}

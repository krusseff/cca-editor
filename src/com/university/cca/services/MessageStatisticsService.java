package com.university.cca.services;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.university.cca.entities.MessageStatistics;
import com.university.cca.repositories.MessageStatisticsRepository;

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
	 * Fetches all message statistics, sort them by ambient name and return them as list
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
}

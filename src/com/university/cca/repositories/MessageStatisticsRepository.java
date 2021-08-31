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
	
	// Constants
	private static final int ZERO_VALUE = 0;
	private static final int ONE_VALUE = 1;
	
	private MessageStatisticsRepository() {
		// Prevent creating an object of type MessageStatisticsRepository
	}
	
	/**
	 * Fetches and calculates all message statistics and return them as a map
	 */
	public static Map<String, MessageStatistics> getMessageStatistics() {
		Map<String, MessageStatistics> statistics = new HashMap<>();

		for (Message message : getAllMessages()) {
			String senderKey = message.getSenderAmbient().toLowerCase();
			String recipientKey = message.getRecipientAmbient().toLowerCase();
			
			// Handle and calculate senders
			if (isAmbientExists(statistics, senderKey)) {
				statistics.put(senderKey, calculateSender(statistics, senderKey));
			} else {
				statistics.putIfAbsent(senderKey, addSender(message.getSenderAmbient()));
			}

			// Handle and calculate recipients
			if (isAmbientExists(statistics, recipientKey)) {
				statistics.put(recipientKey, calculateRecipient(statistics, recipientKey));
			} else {
				statistics.putIfAbsent(recipientKey, addRecipient(message.getRecipientAmbient()));
			}	
		}
		
		return statistics;
	}

	/**
	 * Fetches all messages from given data source
	 */
	private static List<Message> getAllMessages() {
		
		return AmbientCSVReader.getAllMessages();
	}
	
	/**
	 * Check for existing ambient name into the given map
	 */
	private static boolean isAmbientExists(Map<String, MessageStatistics> statistics, 
										   String ambientName) {
		
		return statistics.containsKey(ambientName.toLowerCase());
	}
	
	/**
	 * Increment sent count value of the given ambient sender with one and return it
	 */
	private static MessageStatistics calculateSender(Map<String, MessageStatistics> statistics,
													 String ambientSenderKey) {
		
		MessageStatistics messageStats = statistics.get(ambientSenderKey);
		int newSentMessagesCount = increment(messageStats.getSentCount());
		messageStats.setSentCount(newSentMessagesCount);
		
		return messageStats;
	}
	
	/**
	 * Increment received count value of the given ambient recipient with one and return it 
	 */
	private static MessageStatistics calculateRecipient(Map<String, MessageStatistics> statistics,
			 											String ambientRecipientKey) {
		
		MessageStatistics messageStats = statistics.get(ambientRecipientKey);
		int newReceivedMessagesCount = increment(messageStats.getReceivedCount());
		messageStats.setReceivedCount(newReceivedMessagesCount);
		
		return messageStats;
	}
	
	/**
	 * Create new message statistics object with one sent and zero received messages
	 */
	private static MessageStatistics addSender(String ambientSenderName) {
		
		return new MessageStatistics(ambientSenderName, ONE_VALUE, ZERO_VALUE);
	}
	
	/**
	 * Create new message statistics object with zero sent and one received messages
	 */
	private static MessageStatistics addRecipient(String ambientRecipientName) {
		
		return new MessageStatistics(ambientRecipientName, ZERO_VALUE, ONE_VALUE);
	}
	
	/**
	 * Increment a given value with one
	 */
	private static int increment(int value) {
		
		return value + ONE_VALUE;
	}
}

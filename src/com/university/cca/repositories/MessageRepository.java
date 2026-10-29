package com.university.cca.repositories;

import java.util.List;
import java.util.stream.Collectors;

import com.university.cca.entities.Message;
import com.university.cca.files.csv.AmbientCSVReader;

/**
 * Holds methods for fetching ambient messages from given datasource.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class MessageRepository {

	private MessageRepository() {
		// Prevent creating an object of type MessageRepository class
	}
	
	/**
	 * Returns all ambient messages from given data source
	 */
	public static List<Message> getAllMessages() {
		return AmbientCSVReader.getAllMessages();
	}

	/**
	 * Returns all ambient messages from the specified csv file
	 */
	public static List<Message> getAllMessages(String filePath) {
		return AmbientCSVReader.getAllMessages(filePath);
	}
	
	/**
	 * Returns ambient messages as a sorted string array in alphabetical order
	 */
	public static String[] getAmbientMessagesSorted() {
		return sortMessageTexts(getAllMessages());
	}

	/**
	 * Returns ambient messages as a sorted string array from the specified csv file
	 */
	public static String[] getAmbientMessagesSorted(String filePath) {
		return sortMessageTexts(getAllMessages(filePath));
	}

	/**
	 * Sort message texts alphabetically and return as array
	 */
	static String[] sortMessageTexts(List<Message> messages) {
		List<String> sortedMessages = messages.stream()
			.map(Message::getMessage)
			.sorted()
			.collect(Collectors.toList());
		return sortedMessages.toArray(new String[sortedMessages.size()]);
	}
}

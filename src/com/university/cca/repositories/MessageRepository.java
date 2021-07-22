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
		// Prevent creating an object of type MessageRepository
	}
	
	/**
	 * Returns all ambient messages from given data source
	 */
	public static List<Message> getAllMessages() {
		return AmbientCSVReader.getAllMessages();
	}
	
	/**
	 * Returns ambient messages as a sorted string array in alphabetical order
	 */
	public static String[] getAmbientMessagesSorted() {
		List<String> sortedMessages = getAllMessages()
			.stream()
			.map(Message::getMessage)
			.sorted()
			.collect(Collectors.toList());
		
		String[] messages = new String[sortedMessages.size()];
		
		return sortedMessages.toArray(messages);
	}
}

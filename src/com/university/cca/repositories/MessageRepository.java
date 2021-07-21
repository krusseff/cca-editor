package com.university.cca.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Holds methods for fetching ambient messages from given datasource.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class MessageRepository {

	private static final Logger logger = LoggerFactory.getLogger(MessageRepository.class);

	private MessageRepository() {
		// Prevent creating an object of type MessageRepository
	}
}

package com.university.cca.files.csv;

import com.opencsv.bean.CsvBindByPosition;

/**
 * CSV Positioned bean class that holds an information for message statistics.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CsvMessageStatisticsBean {

	@CsvBindByPosition(position = 0)
	private String ambientName;
	
	@CsvBindByPosition(position = 1)
	private int sentMessages;
	
	@CsvBindByPosition(position = 2)
	private int receivedMessages;
	
	public CsvMessageStatisticsBean() {
		// To be able to create an object without information for it
	}

	public CsvMessageStatisticsBean(String ambientName, int sentMessages, int receivedMessages) {
		this.ambientName = ambientName;
		this.sentMessages = sentMessages;
		this.receivedMessages = receivedMessages;
	}

	// Getters and Setters
	public String getAmbientName() {
		return ambientName;
	}

	public void setAmbientName(String ambientName) {
		this.ambientName = ambientName;
	}

	public int getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(int sentMessages) {
		this.sentMessages = sentMessages;
	}

	public int getReceivedMessages() {
		return receivedMessages;
	}

	public void setReceivedMessages(int receivedMessages) {
		this.receivedMessages = receivedMessages;
	}
}

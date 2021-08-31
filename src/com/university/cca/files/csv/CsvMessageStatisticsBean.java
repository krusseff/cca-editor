package com.university.cca.files.csv;

import com.opencsv.bean.CsvBindByName;

/**
 * CSV Positioned bean class that holds an information for message statistics.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CsvMessageStatisticsBean {

	@CsvBindByName(column = "ambient_name")
	private String ambientName;
	
	@CsvBindByName(column = "sent_messages")
	private int sentMessages;
	
	@CsvBindByName(column = "received_messages")
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

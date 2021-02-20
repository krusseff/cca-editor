package com.university.cca.files.csv;

import com.opencsv.bean.CsvBindByPosition;

/**
 * CSV Positioned bean class that holds an information for a specific message object into the ambient CSV file.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CsvMessageBean {

	@CsvBindByPosition(position = 0)
	private String senderAmbient;
	
	@CsvBindByPosition(position = 1)
	private String recipientAmbient;
	
	@CsvBindByPosition(position = 2)
	private String ambientMessage;
	
	public CsvMessageBean() {
		// To be able to create an object without information for it
	}
	
	public CsvMessageBean(String senderAmbient, String recipientAmbient, String ambientMessage) {
		this.senderAmbient = senderAmbient;
		this.recipientAmbient = recipientAmbient;
		this.ambientMessage = ambientMessage;
	}

	// Getters and setters
	public String getSenderAmbient() {
		return senderAmbient;
	}

	public void setSenderAmbient(String senderAmbient) {
		this.senderAmbient = senderAmbient;
	}

	public String getRecipientAmbient() {
		return recipientAmbient;
	}

	public void setRecipientAmbient(String recipientAmbient) {
		this.recipientAmbient = recipientAmbient;
	}

	public String getAmbientMessage() {
		return ambientMessage;
	}

	public void setAmbientMessage(String ambientMessage) {
		this.ambientMessage = ambientMessage;
	}
}

package com.university.cca.entities;

/**
 * Model class that holds an information for a message between two ambients
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class Message {

	private String senderAmbient;
	private String recipientAmbient;
	private String ambientMessage;
	
	public Message() {
		// To be able to create an object without information for it
	}
	
	public Message(String senderAmbient, String recipientAmbient, String ambientMessage) {
		this.senderAmbient = senderAmbient;
		this.recipientAmbient = recipientAmbient;
		this.ambientMessage = ambientMessage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ambientMessage == null) ? 0 : ambientMessage.hashCode());
		result = prime * result + ((recipientAmbient == null) ? 0 : recipientAmbient.hashCode());
		result = prime * result + ((senderAmbient == null) ? 0 : senderAmbient.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (ambientMessage == null) {
			if (other.ambientMessage != null)
				return false;
		} else if (!ambientMessage.equals(other.ambientMessage))
			return false;
		if (recipientAmbient == null) {
			if (other.recipientAmbient != null)
				return false;
		} else if (!recipientAmbient.equals(other.recipientAmbient))
			return false;
		if (senderAmbient == null) {
			if (other.senderAmbient != null)
				return false;
		} else if (!senderAmbient.equals(other.senderAmbient))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message [senderAmbient=" + senderAmbient + ", recipientAmbient=" + recipientAmbient + ", message="
				+ ambientMessage + "]";
	}

	// Getters and Setters
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

	public String getMessage() {
		return ambientMessage;
	}

	public void setMessage(String message) {
		this.ambientMessage = message;
	}
}

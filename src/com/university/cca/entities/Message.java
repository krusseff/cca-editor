package com.university.cca.entities;

/**
 * Model class that holds an information for a message between two ambients.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class Message {

	private String senderAmbient;
	private String recipientAmbient;
	private String passMessageTo;
	private String respondToMessage;
	private String ambientMessage;
	
	public Message() {
		// To be able to create an object without information for it
	}
	
	public Message(String senderAmbient, String recipientAmbient, 
				   String passMessageTo, String respondToMessage, String ambientMessage) {
		
		this.senderAmbient = senderAmbient;
		this.recipientAmbient = recipientAmbient;
		this.passMessageTo = passMessageTo;
		this.respondToMessage = respondToMessage;
		this.ambientMessage = ambientMessage;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ambientMessage == null) ? 0 : ambientMessage.hashCode());
		result = prime * result + ((passMessageTo == null) ? 0 : passMessageTo.hashCode());
		result = prime * result + ((recipientAmbient == null) ? 0 : recipientAmbient.hashCode());
		result = prime * result + ((respondToMessage == null) ? 0 : respondToMessage.hashCode());
		result = prime * result + ((senderAmbient == null) ? 0 : senderAmbient.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Message)) {
			return false;
		}
		Message other = (Message) obj;
		if (ambientMessage == null) {
			if (other.ambientMessage != null) {
				return false;
			}
		} else if (!ambientMessage.equals(other.ambientMessage)) {
			return false;
		}
		if (passMessageTo == null) {
			if (other.passMessageTo != null) {
				return false;
			}
		} else if (!passMessageTo.equals(other.passMessageTo)) {
			return false;
		}
		if (recipientAmbient == null) {
			if (other.recipientAmbient != null) {
				return false;
			}
		} else if (!recipientAmbient.equals(other.recipientAmbient)) {
			return false;
		}
		if (respondToMessage == null) {
			if (other.respondToMessage != null) {
				return false;
			}
		} else if (!respondToMessage.equals(other.respondToMessage)) {
			return false;
		}
		if (senderAmbient == null) {
			if (other.senderAmbient != null) {
				return false;
			}
		} else if (!senderAmbient.equals(other.senderAmbient)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		
		return "Message [senderAmbient=" + senderAmbient
			+ ", recipientAmbient=" + recipientAmbient
			+ ", passMessageTo=" + passMessageTo 
			+ ", respondToMessage=" + respondToMessage 
			+ ", ambientMessage=" + ambientMessage + "]";
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
	
	public String getPassMessageTo() {
		return passMessageTo;
	}

	public void setPassMessageTo(String passMessageTo) {
		this.passMessageTo = passMessageTo;
	}
	
	public String getRespondToMessage() {
		return respondToMessage;
	}

	public void setRespondToMessage(String respondToMessage) {
		this.respondToMessage = respondToMessage;
	}

	public String getMessage() {
		return ambientMessage;
	}

	public void setMessage(String message) {
		this.ambientMessage = message;
	}
}

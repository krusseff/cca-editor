package com.university.cca.entities;

/**
 * Model class that holds an information for messages statistics object.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class MessageStatistics {
	
	private String ambientName;
	private int sentCount;
	private int receivedCount;

	public MessageStatistics() {
		// To be able to create an object without information for it
	}
	
	public MessageStatistics(String ambientName, int sentCount, int receivedCount) {
		
		this.ambientName = ambientName;
		this.sentCount = sentCount;
		this.receivedCount = receivedCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ambientName == null) ? 0 : ambientName.hashCode());
		result = prime * result + receivedCount;
		result = prime * result + sentCount;
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
		if (!(obj instanceof MessageStatistics)) {
			return false;
		}
		MessageStatistics other = (MessageStatistics) obj;
		if (ambientName == null) {
			if (other.ambientName != null) {
				return false;
			}
		} else if (!ambientName.equals(other.ambientName)) {
			return false;
		}
		if (receivedCount != other.receivedCount) {
			return false;
		}
		if (sentCount != other.sentCount) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MessageStatistics ["
			+ "ambientName=" + ambientName 
			+ ", sentCount=" + sentCount 
			+ ", receivedCount=" + receivedCount 
			+ "]";
	}

	// Getters and Setters
	public String getAmbientName() {
		return ambientName;
	}

	public void setAmbientName(String ambientName) {
		this.ambientName = ambientName;
	}

	public int getSentCount() {
		return sentCount;
	}

	public void setSentCount(int sentCount) {
		this.sentCount = sentCount;
	}

	public int getReceivedCount() {
		return receivedCount;
	}

	public void setReceivedCount(int receivedCount) {
		this.receivedCount = receivedCount;
	}
}

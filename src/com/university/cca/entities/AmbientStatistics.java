package com.university.cca.entities;

/**
 * Model class that holds an information for ambient statistics object.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientStatistics {

	private int totalCount;
	private int staticCount;
	private int nonStaticCount;
	private int activeCount;
	private int inactiveCount;
	
	public AmbientStatistics() {
		// To be able to create an object without information for it
	}
	
	public AmbientStatistics(int totalCount, int staticCount, 
							 int nonStaticCount, int activeCount, 
							 int inactiveCount) {
		
		this.totalCount = totalCount;
		this.staticCount = staticCount;
		this.nonStaticCount = nonStaticCount;
		this.activeCount = activeCount;
		this.inactiveCount = inactiveCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + activeCount;
		result = prime * result + inactiveCount;
		result = prime * result + nonStaticCount;
		result = prime * result + staticCount;
		result = prime * result + totalCount;
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
		if (!(obj instanceof AmbientStatistics)) {
			return false;
		}
		AmbientStatistics other = (AmbientStatistics) obj;
		if (activeCount != other.activeCount) {
			return false;
		}
		if (inactiveCount != other.inactiveCount) {
			return false;
		}
		if (nonStaticCount != other.nonStaticCount) {
			return false;
		}
		if (staticCount != other.staticCount) {
			return false;
		}
		if (totalCount != other.totalCount) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "AmbientStatistics ["
			+ "totalCount=" + totalCount 
			+ ", staticCount=" + staticCount
			+ ", nonStaticCount=" + nonStaticCount 
			+ ", activeCount=" + activeCount
			+ ", inactiveCount=" + inactiveCount
			+ "]";
	}

	// Getters and Setters
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getStaticCount() {
		return staticCount;
	}

	public void setStaticCount(int staticCount) {
		this.staticCount = staticCount;
	}

	public int getNonStaticCount() {
		return nonStaticCount;
	}

	public void setNonStaticCount(int nonStaticCount) {
		this.nonStaticCount = nonStaticCount;
	}

	public int getActiveCount() {
		return activeCount;
	}

	public void setActiveCount(int activeCount) {
		this.activeCount = activeCount;
	}

	public int getInactiveCount() {
		return inactiveCount;
	}

	public void setInactiveCount(int inactiveCount) {
		this.inactiveCount = inactiveCount;
	}
}

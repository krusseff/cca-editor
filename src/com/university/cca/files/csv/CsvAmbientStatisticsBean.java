package com.university.cca.files.csv;

import com.opencsv.bean.CsvBindByName;

/**
 * CSV Positioned bean class that holds an information for ambient statistics.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CsvAmbientStatisticsBean {
	
	@CsvBindByName(column = "total")
	private int totalCount;
	
	@CsvBindByName(column = "static")
	private int staticCount;
	
	@CsvBindByName(column = "non-static")
	private int nonStaticCount;
	
	@CsvBindByName(column = "active")
	private int activeCount;
	
	@CsvBindByName(column = "inactive")
	private int inactiveCount;
	
	public CsvAmbientStatisticsBean() {
		// To be able to create an object without information for it
	}

	public CsvAmbientStatisticsBean(int totalCount, int staticCount, int nonStaticCount, 
									int activeCount, int inactiveCount) {
		
		this.totalCount = totalCount;
		this.staticCount = staticCount;
		this.nonStaticCount = nonStaticCount;
		this.activeCount = activeCount;
		this.inactiveCount = inactiveCount;
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

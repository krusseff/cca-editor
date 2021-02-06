package com.university.cca.files.csv;

import com.opencsv.bean.CsvBindByName;

/**
 * CSV Named bean class that holds an information for a specific ambient object into the ambient CSV file.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CsvAmbientBean {
	
	@CsvBindByName(column = "name")
	private String name;
	
	@CsvBindByName(column = "location")
	private String location;
	
	@CsvBindByName(column = "static")
	private boolean isStaticAmbient;
	
	@CsvBindByName(column = "parent")
	private String parentAmbient;
	
	public CsvAmbientBean() {
		// To be able to create an object without information for it
	}

	public CsvAmbientBean(String name, String location, boolean isStaticAmbient, String parentAmbient) {
		this.name = name;
		this.location = location;
		this.isStaticAmbient = isStaticAmbient;
		this.parentAmbient = parentAmbient;
	}

	// Getters and Setters
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public boolean isStaticAmbient() {
		return isStaticAmbient;
	}
	
	public void setStaticAmbient(boolean isStaticAmbient) {
		this.isStaticAmbient = isStaticAmbient;
	}

	public String getParentAmbient() {
		return parentAmbient;
	}

	public void setParentAmbient(String parentAmbient) {
		this.parentAmbient = parentAmbient;
	}
}


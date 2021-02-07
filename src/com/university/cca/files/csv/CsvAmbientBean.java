package com.university.cca.files.csv;

import com.opencsv.bean.CsvBindByPosition;
import com.university.cca.enums.AmbientType;

/**
 * CSV Positioned bean class that holds an information for a specific ambient object into the ambient CSV file.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CsvAmbientBean {
	
	@CsvBindByPosition(position = 0)
	private AmbientType ambientType;
	
	@CsvBindByPosition(position = 1)
	private String name;
	
	@CsvBindByPosition(position = 2)
	private String location;
	
	@CsvBindByPosition(position = 3)
	private boolean isStaticAmbient;
	
	@CsvBindByPosition(position = 4)
	private String parentAmbient;
	
	public CsvAmbientBean() {
		// To be able to create an object without information for it
	}

	public CsvAmbientBean(String name, String location, boolean isStaticAmbient, String parentAmbient, AmbientType ambientType) {
		this.name = name;
		this.location = location;
		this.isStaticAmbient = isStaticAmbient;
		this.parentAmbient = parentAmbient;
		this.ambientType = ambientType;
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

	public AmbientType getAmbientType() {
		return ambientType;
	}

	public void setAmbientType(AmbientType ambientType) {
		this.ambientType = ambientType;
	}
}

package com.university.cca.files.csv;

import com.opencsv.bean.CsvBindByPosition;
import com.university.cca.enums.AmbientType;

/**
 * CSV Positioned bean class that holds an information for an ambient object into the ambient CSV file.
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
	private String latitude;
	
	@CsvBindByPosition(position = 4)
	private String longitude;
	
	@CsvBindByPosition(position = 5)
	private boolean isStaticAmbient;
	
	@CsvBindByPosition(position = 6)
	private String parentAmbient;
	
	@CsvBindByPosition(position = 7)
	private boolean isActiveAmbient;
	
	public CsvAmbientBean() {
		// To be able to create an object without information for it
	}

	public CsvAmbientBean(String name, String location, String latitude, String longitude, 
						  boolean isStaticAmbient, String parentAmbient, 
						  AmbientType ambientType, boolean isActiveAmbient) {
		
		this.name = name;
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
		this.isStaticAmbient = isStaticAmbient;
		this.parentAmbient = parentAmbient;
		this.ambientType = ambientType;
		this.isActiveAmbient = isActiveAmbient;
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
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
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

	public boolean isActiveAmbient() {
		return isActiveAmbient;
	}

	public void setActiveAmbient(boolean isActiveAmbient) {
		this.isActiveAmbient = isActiveAmbient;
	}
}

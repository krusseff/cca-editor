package com.university.cca.entities;

import com.university.cca.enums.AmbientType;

/**
 * Model class that holds an information for a specific ambient object.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class Ambient {
	
	private String name;
	private String location;
	private String latitude; 
	private String longitude;
	private boolean isStaticAmbient;
	private String parentAmbient;
	private AmbientType ambientType;
	private boolean isActiveAmbient;
	
	public Ambient() {
		// To be able to create an object without information for it
	}

	public Ambient(String name, String location, String latitude, String longitude, 
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ambientType == null) ? 0 : ambientType.hashCode());
		result = prime * result + (isActiveAmbient ? 1231 : 1237);
		result = prime * result + (isStaticAmbient ? 1231 : 1237);
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parentAmbient == null) ? 0 : parentAmbient.hashCode());
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
		if (!(obj instanceof Ambient)) {
			return false;
		}
		Ambient other = (Ambient) obj;
		if (ambientType != other.ambientType) {
			return false;
		}
		if (isActiveAmbient != other.isActiveAmbient) {
			return false;
		}
		if (isStaticAmbient != other.isStaticAmbient) {
			return false;
		}
		if (latitude == null) {
			if (other.latitude != null) {
				return false;
			}
		} else if (!latitude.equals(other.latitude)) {
			return false;
		}
		if (location == null) {
			if (other.location != null) {
				return false;
			}
		} else if (!location.equals(other.location)) {
			return false;
		}
		if (longitude == null) {
			if (other.longitude != null) {
				return false;
			}
		} else if (!longitude.equals(other.longitude)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (parentAmbient == null) {
			if (other.parentAmbient != null) {
				return false;
			}
		} else if (!parentAmbient.equals(other.parentAmbient)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "Ambient ["
				+ "name=" + name + ", location=" + location + ", latitude=" + latitude + ", longitude="
				+ longitude + ", isStaticAmbient=" + isStaticAmbient + ", parentAmbient=" + parentAmbient
				+ ", ambientType=" + ambientType + ", isActiveAmbient=" + isActiveAmbient 
				+ "]";
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

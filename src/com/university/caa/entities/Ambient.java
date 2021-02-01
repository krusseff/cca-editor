package com.university.caa.entities;

public class Ambient {
	
	private String name;
	private String location;
	private boolean isStaticAmbient;
	private Ambient parentAmbient;
	
	public Ambient() {
		// To be able to create an object without information for it
	}

	public Ambient(String name, String location, boolean isStaticAmbient, Ambient parentAmbient) {
		this.name = name;
		this.location = location;
		this.isStaticAmbient = isStaticAmbient;
		this.parentAmbient = parentAmbient;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Ambient other = (Ambient) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ambient [name=" + name + ", location=" + location + ", isStaticAmbient=" + isStaticAmbient
				+ ", parentAmbient=" + parentAmbient + "]";
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
	
	public Ambient getParentAmbient() {
		return parentAmbient;
	}
	
	public void setParentAmbient(Ambient parentAmbient) {
		this.parentAmbient = parentAmbient;
	}
}

package com.university.cca.services;

import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.entities.Ambient;
import com.university.cca.enums.AmbientType;
import com.university.cca.repositories.AmbientRepository;

/**
 * Holds methods for fetching and validating ambients information
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientService {
	
    private static final Logger logger = LoggerFactory.getLogger(AmbientService.class);
    
    private static final String LATITUDE_REGEX ="^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$";
    private static final String LONGITUDE_REGEX ="^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$";

	private AmbientService() {
		// Prevent creating an object of type AmbientService
	}
	
	/**
	 * Creates and returns an ambient object
	 */
	public static Ambient constructAmbient(String name, 
										   String location, 
										   String latitude, 
										   String longitude,
										   boolean isStatic, 
										   Object parentAmbient,
										   AmbientType ambientType,
										   boolean isActiveAmbient) {
		
		String parent = String.valueOf(parentAmbient);
		Ambient ambient = new Ambient(name, location, latitude, longitude, isStatic, parent, ambientType, isActiveAmbient);
		
		logger.info("Ambient constructed: {}", ambient);
		
		return ambient;
	}
	
	/**
	 * Checks for already existing ambients with provided as an input parameter name
	 */
	public static boolean isExistingAmbient(String ambientName) {
		List<String> ambientNames = AmbientRepository.getAllAmbientNames();
		
		return ambientName != null && ambientNames.contains(ambientName);
	}
	
	/**
	 * Validates the fields of the newly created ambient object
	 */
	public static boolean isValidAmbient(String name, 
										 String location, 
										 String latitude, 
										 String longitude,
										 Object parentAmbient) {
		
		boolean isValidName = (name != null && !name.isEmpty());
		boolean isValidLocation = (location != null && !location.isEmpty());
		boolean isValidLatitude = isValidCoordinate(latitude, LATITUDE_REGEX);
		boolean isValidLongitude = isValidCoordinate(longitude, LONGITUDE_REGEX);
		boolean isValidParent = (parentAmbient != null);
		
		return isValidName && isValidLocation && 
			   isValidLatitude && isValidLongitude && 
			   isValidParent;
	}
	
	/**
	 * Validates the coordinates of the newly created ambient object
	 */
	private static boolean isValidCoordinate(String coordinate, 
											 String coordinateRegEx) {
		
		Pattern coordinatePattern = Pattern.compile(coordinateRegEx);
		
	    return coordinate != null && 
	    	   !coordinate.isEmpty() && 
	    	   coordinatePattern.matcher(coordinate).matches();
	}
}

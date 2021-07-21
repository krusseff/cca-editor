package com.university.cca.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.entities.Ambient;
import com.university.cca.files.csv.AmbientCSVReader;

/**
 * Holds methods for fetching ambient related information from given datasource.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientRepository {

	private static final Logger logger = LoggerFactory.getLogger(AmbientRepository.class);
	
	private static final String NULL_VALUE = null;
	
	private AmbientRepository() {
		// Prevent creating an object of type AmbientRepository
	}
	
	/**
	 * Method that returns parent ambient of the given ambient name
	 */
	public static String getParentAmbientName(String ambientName) {
		
		return AmbientCSVReader.getAllAmbients()
			.stream()
			.filter(ambient -> ambientName.equals(ambient.getName()))
			.map(Ambient::getParentAmbient)
			.findFirst()
			.orElse(NULL_VALUE);
	}
}

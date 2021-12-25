package com.university.cca.repositories;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.university.cca.entities.Ambient;
import com.university.cca.files.csv.AmbientCSVReader;

/**
 * Holds methods for fetching ambient related information from given datasource.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientRepository {

	private static final String NULL_VALUE = null;
	
	private AmbientRepository() {
		// Prevent creating an object of type AmbientRepository class
	}
	
	/**
	 * Fetch all ambients from given data source
	 */
	public static List<Ambient> getAllAmbients() {
		
		return AmbientCSVReader.getAllAmbients();
	}
	
	/**
	 * Fetch all ambients from the csv file sorted by ambient name
	 */
	public static List<Ambient> getAllAmbientsSortedByName() {
	
		return getAllAmbients()
			.stream()
			.sorted(Comparator.comparing(Ambient::getName))
			.collect(Collectors.toList());
	}
	
	/**
	 * Returns all ambient names
	 */
	public static List<String> getAllAmbientNames() {
		
		return getAllAmbients()
			.stream()
			.map(Ambient::getName)
			.collect(Collectors.toList());
	}

	/**
	 *  Returns only active ambient names
	 */
	public static List<String> getActiveAmbientNames() {
		return getAllAmbients()
			.stream()
			.filter(Ambient::isActiveAmbient)
			.map(Ambient::getName)
			.collect(Collectors.toList());
	}
	
	/**
	 *  Returns only active ambient names sorted alphabetically
	 */
	public static String[] getActiveAmbientNamesSorted() {
		List<String> sortedActiveAmbientNames = getActiveAmbientNames()
			.stream()
			.sorted()
			.collect(Collectors.toList());
		
		String[] ambientNames = new String[sortedActiveAmbientNames.size()];
		
		return sortedActiveAmbientNames.toArray(ambientNames);
	}
	
	/**
	 * Returns parent ambient of the given ambient name
	 */
	public static String getParentAmbientName(String ambientName) {
		
		return getAllAmbients()
			.stream()
			.filter(ambient -> ambientName.equals(ambient.getName()))
			.map(Ambient::getParentAmbient)
			.findFirst()
			.orElse(NULL_VALUE);
	}
}

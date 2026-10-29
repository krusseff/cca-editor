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
	 * Fetch all ambients from the specified csv file
	 */
	public static List<Ambient> getAllAmbients(String filePath) {
		return AmbientCSVReader.getAllAmbients(filePath);
	}
	
	/**
	 * Fetch all ambients from the csv file sorted by ambient name
	 */
	public static List<Ambient> getAllAmbientsSortedByName() {
		return sortByName(getAllAmbients());
	}

	/**
	 * Fetch all ambients from the specified csv file sorted by ambient name
	 */
	public static List<Ambient> getAllAmbientsSortedByName(String filePath) {
		return sortByName(getAllAmbients(filePath));
	}

	/**
	 * Sort ambients by name
	 */
	static List<Ambient> sortByName(List<Ambient> ambients) {
		return ambients.stream()
			.sorted(Comparator.comparing(Ambient::getName))
			.collect(Collectors.toList());
	}
	
	/**
	 * Returns all ambient names
	 */
	public static List<String> getAllAmbientNames() {
		return extractNames(getAllAmbients());
	}

	/**
	 * Returns all ambient names from the specified csv file
	 */
	public static List<String> getAllAmbientNames(String filePath) {
		return extractNames(getAllAmbients(filePath));
	}

	/**
	 * Extract ambient names from a list
	 */
	static List<String> extractNames(List<Ambient> ambients) {
		return ambients.stream()
			.map(Ambient::getName)
			.collect(Collectors.toList());
	}

	/**
	 *  Returns only active ambient names
	 */
	public static List<String> getActiveAmbientNames() {
		return extractActiveNames(getAllAmbients());
	}

	/**
	 *  Returns only active ambient names from the specified csv file
	 */
	public static List<String> getActiveAmbientNames(String filePath) {
		return extractActiveNames(getAllAmbients(filePath));
	}

	/**
	 * Extract active ambient names from a list
	 */
	public static List<String> extractActiveNames(List<Ambient> ambients) {
		return ambients.stream()
			.filter(Ambient::isActiveAmbient)
			.map(Ambient::getName)
			.collect(Collectors.toList());
	}
	
	/**
	 *  Returns only active ambient names sorted alphabetically
	 */
	public static String[] getActiveAmbientNamesSorted() {
		return toSortedArray(getActiveAmbientNames());
	}

	/**
	 *  Returns only active ambient names sorted alphabetically from the specified csv file
	 */
	public static String[] getActiveAmbientNamesSorted(String filePath) {
		return toSortedArray(getActiveAmbientNames(filePath));
	}

	/**
	 * Convert a list of names to a sorted array
	 */
	static String[] toSortedArray(List<String> names) {
		List<String> sorted = names.stream()
			.sorted()
			.collect(Collectors.toList());
		return sorted.toArray(new String[sorted.size()]);
	}
	
	/**
	 * Returns parent ambient of the given ambient name
	 */
	public static String getParentAmbientName(String ambientName) {
		return findParentName(getAllAmbients(), ambientName);
	}

	/**
	 * Returns parent ambient of the given ambient name from the specified csv file
	 */
	public static String getParentAmbientName(String ambientName, String filePath) {
		return findParentName(getAllAmbients(filePath), ambientName);
	}

	/**
	 * Find parent ambient name from a list
	 */
	public static String findParentName(List<Ambient> ambients, String ambientName) {
		return ambients.stream()
			.filter(ambient -> ambientName.equals(ambient.getName()))
			.map(Ambient::getParentAmbient)
			.findFirst()
			.orElse(NULL_VALUE);
	}
}

package com.university.cca.repositories;

import java.util.List;

import com.university.cca.entities.Ambient;
import com.university.cca.entities.AmbientStatistics;
import com.university.cca.files.csv.AmbientCSVReader;

/**
 * Holds methods for fetching ambient statistics from given datasource.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientStatisticsRepository {
	
	private AmbientStatisticsRepository() {
		// Prevent creating an object of type AmbientStatisticsRepository class
	}
	
	/**
	 * Fetches and calculates all ambient statistics
	 */
	public static AmbientStatistics getAmbientStatistics() {
		int totalCount = 0;
		int staticCount = 0;
		int nonStaticCount = 0;
		int activeCount = 0;
		int inactiveCount = 0;

		for (Ambient ambient : getAllAmbients()) {
			totalCount++;
			
			if (ambient.isStaticAmbient()) staticCount++;
			if (!ambient.isStaticAmbient()) nonStaticCount++;
			if (ambient.isActiveAmbient()) activeCount++;
			if (!ambient.isActiveAmbient()) inactiveCount++;
		}
		
		return new AmbientStatistics(
			totalCount, staticCount, nonStaticCount, activeCount, inactiveCount
		);
	}
	
	/**
	 * Fetches all ambients from given data source
	 */
	private static List<Ambient> getAllAmbients() {
		
		return AmbientCSVReader.getAllAmbients();
	}
}

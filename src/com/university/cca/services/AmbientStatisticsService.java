package com.university.cca.services;

import java.util.ArrayList;
import java.util.List;

import com.university.cca.entities.AmbientStatistics;
import com.university.cca.repositories.AmbientStatisticsRepository;

/**
 * Holds methods for fetching ambient statistics.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientStatisticsService {
	
	private static final int ZERO_VALUE = 0;
	
	private AmbientStatisticsService() {
		// Prevent creating an object of type AmbientStatisticsService
	}

	/**
	 * Fetches all ambient statistics as a list
	 */
	public static List<AmbientStatistics> getAllAmbientStatistics() {
		List<AmbientStatistics> ambientStats = new ArrayList<>();
		ambientStats.add(AmbientStatisticsRepository.getAmbientStatistics());
		
		return ambientStats;
	}
	
	/**
	 * Fetches all ambient statistics as an object
	 */
	public static AmbientStatistics getAmbientStatistics() {
		
		return AmbientStatisticsRepository.getAmbientStatistics();
	}
	
	/**
	 * Checks for available ambient statistics.
	 * 
	 * @return false - If the total count of the statistics is zero, then there are no available statistics.
	 * 		   true  - If the total count of the statistics is greater than zero, then there are available statistics.
	 */
	public static boolean hasStatistics(AmbientStatistics ambientStats) {

		return ambientStats.getTotalCount() > ZERO_VALUE;
	}
}

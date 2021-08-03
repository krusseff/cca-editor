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
}

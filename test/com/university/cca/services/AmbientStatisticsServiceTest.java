package com.university.cca.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.university.cca.entities.AmbientStatistics;

public class AmbientStatisticsServiceTest {

	@Test
	public void testHasStatisticsWithPositiveTotal() {
		AmbientStatistics stats = new AmbientStatistics(10, 6, 4, 8, 2);
		
		assertTrue(AmbientStatisticsService.hasStatistics(stats));
	}

	@Test
	public void testHasStatisticsWithZeroTotal() {
		AmbientStatistics stats = new AmbientStatistics(0, 0, 0, 0, 0);
		
		assertFalse(AmbientStatisticsService.hasStatistics(stats));
	}

	@Test
	public void testHasStatisticsWithTotalCountOfOne() {
		AmbientStatistics stats = new AmbientStatistics(1, 0, 1, 1, 0);
		
		assertTrue(AmbientStatisticsService.hasStatistics(stats));
	}
}

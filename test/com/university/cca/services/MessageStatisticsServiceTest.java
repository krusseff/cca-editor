package com.university.cca.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.university.cca.entities.MessageStatistics;

public class MessageStatisticsServiceTest {

	@Test
	public void testHasStatisticsWithNonEmptyList() {
		List<MessageStatistics> stats = Arrays.asList(
			new MessageStatistics("Hospital1", 5, 3)
		);
		
		assertTrue(MessageStatisticsService.hasStatistics(stats));
	}

	@Test
	public void testHasStatisticsWithEmptyList() {
		List<MessageStatistics> stats = Collections.emptyList();
		
		assertFalse(MessageStatisticsService.hasStatistics(stats));
	}

	@Test
	public void testHasStatisticsWithMultipleEntries() {
		List<MessageStatistics> stats = Arrays.asList(
			new MessageStatistics("Hospital1", 5, 3),
			new MessageStatistics("School1", 2, 1)
		);
		
		assertTrue(MessageStatisticsService.hasStatistics(stats));
	}
}

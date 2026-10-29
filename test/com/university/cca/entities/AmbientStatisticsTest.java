package com.university.cca.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AmbientStatisticsTest {

	@Test
	public void testDefaultConstructor() {
		AmbientStatistics stats = new AmbientStatistics();
		
		assertNotNull(stats);
		assertEquals(0, stats.getTotalCount());
		assertEquals(0, stats.getStaticCount());
		assertEquals(0, stats.getNonStaticCount());
		assertEquals(0, stats.getActiveCount());
		assertEquals(0, stats.getInactiveCount());
	}

	@Test
	public void testParameterizedConstructor() {
		AmbientStatistics stats = new AmbientStatistics(10, 6, 4, 8, 2);
		
		assertEquals(10, stats.getTotalCount());
		assertEquals(6, stats.getStaticCount());
		assertEquals(4, stats.getNonStaticCount());
		assertEquals(8, stats.getActiveCount());
		assertEquals(2, stats.getInactiveCount());
	}

	@Test
	public void testSetters() {
		AmbientStatistics stats = new AmbientStatistics();
		
		stats.setTotalCount(20);
		stats.setStaticCount(12);
		stats.setNonStaticCount(8);
		stats.setActiveCount(15);
		stats.setInactiveCount(5);
		
		assertEquals(20, stats.getTotalCount());
		assertEquals(12, stats.getStaticCount());
		assertEquals(8, stats.getNonStaticCount());
		assertEquals(15, stats.getActiveCount());
		assertEquals(5, stats.getInactiveCount());
	}

	@Test
	public void testEqualsIdenticalObjects() {
		AmbientStatistics s1 = new AmbientStatistics(10, 6, 4, 8, 2);
		AmbientStatistics s2 = new AmbientStatistics(10, 6, 4, 8, 2);
		
		assertEquals(s1, s2);
		assertEquals(s1.hashCode(), s2.hashCode());
	}

	@Test
	public void testEqualsSameObject() {
		AmbientStatistics stats = new AmbientStatistics(10, 6, 4, 8, 2);
		
		assertEquals(stats, stats);
	}

	@Test
	public void testNotEqualsNull() {
		AmbientStatistics stats = new AmbientStatistics(10, 6, 4, 8, 2);
		
		assertNotEquals(stats, null);
	}

	@Test
	public void testNotEqualsDifferentClass() {
		AmbientStatistics stats = new AmbientStatistics(10, 6, 4, 8, 2);
		
		assertNotEquals(stats, "not a stats");
	}

	@Test
	public void testNotEqualsDifferentTotalCount() {
		AmbientStatistics s1 = new AmbientStatistics(10, 6, 4, 8, 2);
		AmbientStatistics s2 = new AmbientStatistics(99, 6, 4, 8, 2);
		
		assertNotEquals(s1, s2);
	}

	@Test
	public void testNotEqualsDifferentStaticCount() {
		AmbientStatistics s1 = new AmbientStatistics(10, 6, 4, 8, 2);
		AmbientStatistics s2 = new AmbientStatistics(10, 99, 4, 8, 2);
		
		assertNotEquals(s1, s2);
	}

	@Test
	public void testNotEqualsDifferentNonStaticCount() {
		AmbientStatistics s1 = new AmbientStatistics(10, 6, 4, 8, 2);
		AmbientStatistics s2 = new AmbientStatistics(10, 6, 99, 8, 2);
		
		assertNotEquals(s1, s2);
	}

	@Test
	public void testNotEqualsDifferentActiveCount() {
		AmbientStatistics s1 = new AmbientStatistics(10, 6, 4, 8, 2);
		AmbientStatistics s2 = new AmbientStatistics(10, 6, 4, 99, 2);
		
		assertNotEquals(s1, s2);
	}

	@Test
	public void testNotEqualsDifferentInactiveCount() {
		AmbientStatistics s1 = new AmbientStatistics(10, 6, 4, 8, 2);
		AmbientStatistics s2 = new AmbientStatistics(10, 6, 4, 8, 99);
		
		assertNotEquals(s1, s2);
	}

	@Test
	public void testToStringContainsFields() {
		AmbientStatistics stats = new AmbientStatistics(10, 6, 4, 8, 2);
		String str = stats.toString();
		
		assertTrue(str.contains("totalCount=10"));
		assertTrue(str.contains("staticCount=6"));
		assertTrue(str.contains("nonStaticCount=4"));
		assertTrue(str.contains("activeCount=8"));
		assertTrue(str.contains("inactiveCount=2"));
	}

	@Test
	public void testHashCodeConsistency() {
		AmbientStatistics stats = new AmbientStatistics(10, 6, 4, 8, 2);
		
		assertEquals(stats.hashCode(), stats.hashCode());
	}
}

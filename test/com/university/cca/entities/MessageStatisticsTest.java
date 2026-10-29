package com.university.cca.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MessageStatisticsTest {

	@Test
	public void testDefaultConstructor() {
		MessageStatistics stats = new MessageStatistics();
		
		assertNotNull(stats);
		assertNull(stats.getAmbientName());
		assertEquals(0, stats.getSentCount());
		assertEquals(0, stats.getReceivedCount());
	}

	@Test
	public void testParameterizedConstructor() {
		MessageStatistics stats = new MessageStatistics("Hospital1", 5, 3);
		
		assertEquals("Hospital1", stats.getAmbientName());
		assertEquals(5, stats.getSentCount());
		assertEquals(3, stats.getReceivedCount());
	}

	@Test
	public void testSetters() {
		MessageStatistics stats = new MessageStatistics();
		
		stats.setAmbientName("Bus1");
		stats.setSentCount(10);
		stats.setReceivedCount(7);
		
		assertEquals("Bus1", stats.getAmbientName());
		assertEquals(10, stats.getSentCount());
		assertEquals(7, stats.getReceivedCount());
	}

	@Test
	public void testEqualsIdenticalObjects() {
		MessageStatistics s1 = new MessageStatistics("Hospital1", 5, 3);
		MessageStatistics s2 = new MessageStatistics("Hospital1", 5, 3);
		
		assertEquals(s1, s2);
		assertEquals(s1.hashCode(), s2.hashCode());
	}

	@Test
	public void testEqualsSameObject() {
		MessageStatistics stats = new MessageStatistics("Hospital1", 5, 3);
		
		assertEquals(stats, stats);
	}

	@Test
	public void testNotEqualsNull() {
		MessageStatistics stats = new MessageStatistics("Hospital1", 5, 3);
		
		assertNotEquals(stats, null);
	}

	@Test
	public void testNotEqualsDifferentClass() {
		MessageStatistics stats = new MessageStatistics("Hospital1", 5, 3);
		
		assertNotEquals(stats, "not stats");
	}

	@Test
	public void testNotEqualsDifferentAmbientName() {
		MessageStatistics s1 = new MessageStatistics("Hospital1", 5, 3);
		MessageStatistics s2 = new MessageStatistics("School1", 5, 3);
		
		assertNotEquals(s1, s2);
	}

	@Test
	public void testNotEqualsDifferentSentCount() {
		MessageStatistics s1 = new MessageStatistics("Hospital1", 5, 3);
		MessageStatistics s2 = new MessageStatistics("Hospital1", 99, 3);
		
		assertNotEquals(s1, s2);
	}

	@Test
	public void testNotEqualsDifferentReceivedCount() {
		MessageStatistics s1 = new MessageStatistics("Hospital1", 5, 3);
		MessageStatistics s2 = new MessageStatistics("Hospital1", 5, 99);
		
		assertNotEquals(s1, s2);
	}

	@Test
	public void testEqualsWithNullNames() {
		MessageStatistics s1 = new MessageStatistics(null, 5, 3);
		MessageStatistics s2 = new MessageStatistics(null, 5, 3);
		
		assertEquals(s1, s2);
	}

	@Test
	public void testNotEqualsOneNullNameOneNot() {
		MessageStatistics s1 = new MessageStatistics(null, 5, 3);
		MessageStatistics s2 = new MessageStatistics("Hospital1", 5, 3);
		
		assertNotEquals(s1, s2);
	}

	@Test
	public void testToStringContainsFields() {
		MessageStatistics stats = new MessageStatistics("Hospital1", 5, 3);
		String str = stats.toString();
		
		assertTrue(str.contains("Hospital1"));
		assertTrue(str.contains("sentCount=5"));
		assertTrue(str.contains("receivedCount=3"));
	}

	@Test
	public void testHashCodeConsistency() {
		MessageStatistics stats = new MessageStatistics("Hospital1", 5, 3);
		
		assertEquals(stats.hashCode(), stats.hashCode());
	}
}

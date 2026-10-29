package com.university.cca.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.university.cca.enums.AmbientType;

public class AmbientTest {

	@Test
	public void testDefaultConstructor() {
		Ambient ambient = new Ambient();
		
		assertNotNull(ambient);
		assertNull(ambient.getName());
		assertNull(ambient.getLocation());
		assertNull(ambient.getLatitude());
		assertNull(ambient.getLongitude());
		assertFalse(ambient.isStaticAmbient());
		assertNull(ambient.getParentAmbient());
		assertNull(ambient.getAmbientType());
		assertFalse(ambient.isActiveAmbient());
	}

	@Test
	public void testParameterizedConstructor() {
		Ambient ambient = createTestAmbient();
		
		assertEquals("Hospital1", ambient.getName());
		assertEquals("Plovdiv", ambient.getLocation());
		assertEquals("42.15", ambient.getLatitude());
		assertEquals("24.75", ambient.getLongitude());
		assertTrue(ambient.isStaticAmbient());
		assertEquals("City1", ambient.getParentAmbient());
		assertEquals(AmbientType.HOSPITAL, ambient.getAmbientType());
		assertTrue(ambient.isActiveAmbient());
	}

	@Test
	public void testSetters() {
		Ambient ambient = new Ambient();
		
		ambient.setName("School1");
		ambient.setLocation("Sofia");
		ambient.setLatitude("42.69");
		ambient.setLongitude("23.31");
		ambient.setStaticAmbient(true);
		ambient.setParentAmbient("City2");
		ambient.setAmbientType(AmbientType.SCHOOL);
		ambient.setActiveAmbient(false);
		
		assertEquals("School1", ambient.getName());
		assertEquals("Sofia", ambient.getLocation());
		assertEquals("42.69", ambient.getLatitude());
		assertEquals("23.31", ambient.getLongitude());
		assertTrue(ambient.isStaticAmbient());
		assertEquals("City2", ambient.getParentAmbient());
		assertEquals(AmbientType.SCHOOL, ambient.getAmbientType());
		assertFalse(ambient.isActiveAmbient());
	}

	@Test
	public void testEqualsSameObject() {
		Ambient ambient = createTestAmbient();
		
		assertEquals(ambient, ambient);
	}

	@Test
	public void testEqualsIdenticalObjects() {
		Ambient a1 = createTestAmbient();
		Ambient a2 = createTestAmbient();
		
		assertEquals(a1, a2);
		assertEquals(a1.hashCode(), a2.hashCode());
	}

	@Test
	public void testNotEqualsNull() {
		Ambient ambient = createTestAmbient();
		
		assertNotEquals(ambient, null);
	}

	@Test
	public void testNotEqualsDifferentClass() {
		Ambient ambient = createTestAmbient();
		
		assertNotEquals(ambient, "not an ambient");
	}

	@Test
	public void testNotEqualsDifferentName() {
		Ambient a1 = createTestAmbient();
		Ambient a2 = createTestAmbient();
		a2.setName("DifferentName");
		
		assertNotEquals(a1, a2);
	}

	@Test
	public void testNotEqualsDifferentLocation() {
		Ambient a1 = createTestAmbient();
		Ambient a2 = createTestAmbient();
		a2.setLocation("Varna");
		
		assertNotEquals(a1, a2);
	}

	@Test
	public void testNotEqualsDifferentType() {
		Ambient a1 = createTestAmbient();
		Ambient a2 = createTestAmbient();
		a2.setAmbientType(AmbientType.SCHOOL);
		
		assertNotEquals(a1, a2);
	}

	@Test
	public void testNotEqualsDifferentActiveFlag() {
		Ambient a1 = createTestAmbient();
		Ambient a2 = createTestAmbient();
		a2.setActiveAmbient(false);
		
		assertNotEquals(a1, a2);
	}

	@Test
	public void testNotEqualsDifferentStaticFlag() {
		Ambient a1 = createTestAmbient();
		Ambient a2 = createTestAmbient();
		a2.setStaticAmbient(false);
		
		assertNotEquals(a1, a2);
	}

	@Test
	public void testNotEqualsDifferentLatitude() {
		Ambient a1 = createTestAmbient();
		Ambient a2 = createTestAmbient();
		a2.setLatitude("99.99");
		
		assertNotEquals(a1, a2);
	}

	@Test
	public void testNotEqualsDifferentLongitude() {
		Ambient a1 = createTestAmbient();
		Ambient a2 = createTestAmbient();
		a2.setLongitude("99.99");
		
		assertNotEquals(a1, a2);
	}

	@Test
	public void testNotEqualsDifferentParent() {
		Ambient a1 = createTestAmbient();
		Ambient a2 = createTestAmbient();
		a2.setParentAmbient("OtherCity");
		
		assertNotEquals(a1, a2);
	}

	@Test
	public void testEqualsWithNullFields() {
		Ambient a1 = new Ambient();
		Ambient a2 = new Ambient();
		
		assertEquals(a1, a2);
		assertEquals(a1.hashCode(), a2.hashCode());
	}

	@Test
	public void testEqualsOneNullNameOneNot() {
		Ambient a1 = new Ambient();
		Ambient a2 = new Ambient();
		a2.setName("SomeName");
		
		assertNotEquals(a1, a2);
	}

	@Test
	public void testEqualsOneNullLocationOneNot() {
		Ambient a1 = new Ambient();
		Ambient a2 = new Ambient();
		a2.setLocation("SomeLocation");
		
		assertNotEquals(a1, a2);
	}

	@Test
	public void testEqualsOneNullLatitudeOneNot() {
		Ambient a1 = new Ambient();
		Ambient a2 = new Ambient();
		a2.setLatitude("42.0");
		
		assertNotEquals(a1, a2);
	}

	@Test
	public void testEqualsOneNullLongitudeOneNot() {
		Ambient a1 = new Ambient();
		Ambient a2 = new Ambient();
		a2.setLongitude("24.0");
		
		assertNotEquals(a1, a2);
	}

	@Test
	public void testEqualsOneNullParentOneNot() {
		Ambient a1 = new Ambient();
		Ambient a2 = new Ambient();
		a2.setParentAmbient("Parent");
		
		assertNotEquals(a1, a2);
	}

	@Test
	public void testEqualsOneNullTypeOneNot() {
		Ambient a1 = new Ambient();
		Ambient a2 = new Ambient();
		a2.setAmbientType(AmbientType.CITY);
		
		assertNotEquals(a1, a2);
	}

	@Test
	public void testHashCodeConsistency() {
		Ambient ambient = createTestAmbient();
		int hash1 = ambient.hashCode();
		int hash2 = ambient.hashCode();
		
		assertEquals(hash1, hash2);
	}

	@Test
	public void testToStringContainsFields() {
		Ambient ambient = createTestAmbient();
		String str = ambient.toString();
		
		assertTrue(str.contains("Hospital1"));
		assertTrue(str.contains("Plovdiv"));
		assertTrue(str.contains("42.15"));
		assertTrue(str.contains("24.75"));
		assertTrue(str.contains("City1"));
		assertTrue(str.contains("HOSPITAL"));
	}

	private Ambient createTestAmbient() {
		return new Ambient(
			"Hospital1", "Plovdiv", "42.15", "24.75",
			true, "City1", AmbientType.HOSPITAL, true
		);
	}
}

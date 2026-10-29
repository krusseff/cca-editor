package com.university.cca.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.university.cca.entities.Ambient;
import com.university.cca.enums.AmbientType;

public class AmbientServiceTest {

	// --- constructAmbient tests ---

	@Test
	public void testConstructAmbientReturnsNonNull() {
		Ambient ambient = AmbientService.constructAmbient(
			"Hospital1", "Plovdiv", "42.15", "24.75",
			true, "City1", AmbientType.HOSPITAL, true
		);
		
		assertNotNull(ambient);
	}

	@Test
	public void testConstructAmbientSetsAllFields() {
		Ambient ambient = AmbientService.constructAmbient(
			"School1", "Sofia", "42.69", "23.31",
			false, "City2", AmbientType.SCHOOL, true
		);
		
		assertEquals("School1", ambient.getName());
		assertEquals("Sofia", ambient.getLocation());
		assertEquals("42.69", ambient.getLatitude());
		assertEquals("23.31", ambient.getLongitude());
		assertFalse(ambient.isStaticAmbient());
		assertEquals("City2", ambient.getParentAmbient());
		assertEquals(AmbientType.SCHOOL, ambient.getAmbientType());
		assertTrue(ambient.isActiveAmbient());
	}

	@Test
	public void testConstructAmbientConvertsParentToString() {
		Ambient ambient = AmbientService.constructAmbient(
			"Bus1", "Plovdiv", "42.15", "24.75",
			true, Integer.valueOf(42), AmbientType.BUS, true
		);
		
		assertEquals("42", ambient.getParentAmbient());
	}

	// --- isValidAmbient tests ---

	@Test
	public void testIsValidAmbientAllValid() {
		assertTrue(AmbientService.isValidAmbient(
			"Hospital1", "Plovdiv", "42.15", "24.75", "City1"
		));
	}

	@Test
	public void testIsValidAmbientNullName() {
		assertFalse(AmbientService.isValidAmbient(
			null, "Plovdiv", "42.15", "24.75", "City1"
		));
	}

	@Test
	public void testIsValidAmbientEmptyName() {
		assertFalse(AmbientService.isValidAmbient(
			"", "Plovdiv", "42.15", "24.75", "City1"
		));
	}

	@Test
	public void testIsValidAmbientNullLocation() {
		assertFalse(AmbientService.isValidAmbient(
			"Hospital1", null, "42.15", "24.75", "City1"
		));
	}

	@Test
	public void testIsValidAmbientEmptyLocation() {
		assertFalse(AmbientService.isValidAmbient(
			"Hospital1", "", "42.15", "24.75", "City1"
		));
	}

	@Test
	public void testIsValidAmbientNullParent() {
		assertFalse(AmbientService.isValidAmbient(
			"Hospital1", "Plovdiv", "42.15", "24.75", null
		));
	}

	// --- Latitude validation ---

	@Test
	public void testIsValidAmbientNullLatitude() {
		assertFalse(AmbientService.isValidAmbient(
			"Hospital1", "Plovdiv", null, "24.75", "City1"
		));
	}

	@Test
	public void testIsValidAmbientEmptyLatitude() {
		assertFalse(AmbientService.isValidAmbient(
			"Hospital1", "Plovdiv", "", "24.75", "City1"
		));
	}

	@Test
	public void testIsValidAmbientLatitudeZero() {
		assertTrue(AmbientService.isValidAmbient(
			"Hospital1", "Plovdiv", "0", "24.75", "City1"
		));
	}

	@Test
	public void testIsValidAmbientLatitude90() {
		assertTrue(AmbientService.isValidAmbient(
			"Hospital1", "Plovdiv", "90", "24.75", "City1"
		));
	}

	@Test
	public void testIsValidAmbientLatitudeNegative90() {
		assertTrue(AmbientService.isValidAmbient(
			"Hospital1", "Plovdiv", "-90", "24.75", "City1"
		));
	}

	@Test
	public void testIsValidAmbientLatitudeAbove90() {
		assertFalse(AmbientService.isValidAmbient(
			"Hospital1", "Plovdiv", "91", "24.75", "City1"
		));
	}

	@Test
	public void testIsValidAmbientLatitudeBelow90() {
		assertFalse(AmbientService.isValidAmbient(
			"Hospital1", "Plovdiv", "-91", "24.75", "City1"
		));
	}

	@Test
	public void testIsValidAmbientLatitudeWithDecimals() {
		assertTrue(AmbientService.isValidAmbient(
			"Hospital1", "Plovdiv", "42.123456", "24.75", "City1"
		));
	}

	@Test
	public void testIsValidAmbientLatitudeNonNumeric() {
		assertFalse(AmbientService.isValidAmbient(
			"Hospital1", "Plovdiv", "abc", "24.75", "City1"
		));
	}

	@Test
	public void testIsValidAmbientPositiveLatitudeWithPlus() {
		assertTrue(AmbientService.isValidAmbient(
			"Hospital1", "Plovdiv", "+42.15", "24.75", "City1"
		));
	}

	// --- Longitude validation ---

	@Test
	public void testIsValidAmbientNullLongitude() {
		assertFalse(AmbientService.isValidAmbient(
			"Hospital1", "Plovdiv", "42.15", null, "City1"
		));
	}

	@Test
	public void testIsValidAmbientEmptyLongitude() {
		assertFalse(AmbientService.isValidAmbient(
			"Hospital1", "Plovdiv", "42.15", "", "City1"
		));
	}

	@Test
	public void testIsValidAmbientLongitudeZero() {
		assertTrue(AmbientService.isValidAmbient(
			"Hospital1", "Plovdiv", "42.15", "0", "City1"
		));
	}

	@Test
	public void testIsValidAmbientLongitude180() {
		assertTrue(AmbientService.isValidAmbient(
			"Hospital1", "Plovdiv", "42.15", "180", "City1"
		));
	}

	@Test
	public void testIsValidAmbientLongitudeNegative180() {
		assertTrue(AmbientService.isValidAmbient(
			"Hospital1", "Plovdiv", "42.15", "-180", "City1"
		));
	}

	@Test
	public void testIsValidAmbientLongitudeAbove180() {
		assertFalse(AmbientService.isValidAmbient(
			"Hospital1", "Plovdiv", "42.15", "181", "City1"
		));
	}

	@Test
	public void testIsValidAmbientLongitudeBelow180() {
		assertFalse(AmbientService.isValidAmbient(
			"Hospital1", "Plovdiv", "42.15", "-181", "City1"
		));
	}

	@Test
	public void testIsValidAmbientLongitudeWithDecimals() {
		assertTrue(AmbientService.isValidAmbient(
			"Hospital1", "Plovdiv", "42.15", "24.123456", "City1"
		));
	}

	@Test
	public void testIsValidAmbientLongitudeNonNumeric() {
		assertFalse(AmbientService.isValidAmbient(
			"Hospital1", "Plovdiv", "42.15", "xyz", "City1"
		));
	}
}

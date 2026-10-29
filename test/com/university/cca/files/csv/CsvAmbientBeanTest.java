package com.university.cca.files.csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.university.cca.enums.AmbientType;

public class CsvAmbientBeanTest {

	@Test
	public void testDefaultConstructor() {
		CsvAmbientBean bean = new CsvAmbientBean();

		assertNull(bean.getName());
		assertNull(bean.getLocation());
		assertNull(bean.getLatitude());
		assertNull(bean.getLongitude());
		assertFalse(bean.isStaticAmbient());
		assertNull(bean.getParentAmbient());
		assertNull(bean.getAmbientType());
		assertFalse(bean.isActiveAmbient());
	}

	@Test
	public void testParameterizedConstructor() {
		CsvAmbientBean bean = new CsvAmbientBean(
			"Hospital", "Lovech", "43.1369", "24.7174",
			true, "City", AmbientType.HOSPITAL, true
		);

		assertEquals("Hospital", bean.getName());
		assertEquals("Lovech", bean.getLocation());
		assertEquals("43.1369", bean.getLatitude());
		assertEquals("24.7174", bean.getLongitude());
		assertTrue(bean.isStaticAmbient());
		assertEquals("City", bean.getParentAmbient());
		assertEquals(AmbientType.HOSPITAL, bean.getAmbientType());
		assertTrue(bean.isActiveAmbient());
	}

	@Test
	public void testSettersAndGetters() {
		CsvAmbientBean bean = new CsvAmbientBean();

		bean.setName("Sensor1");
		bean.setLocation("Building A");
		bean.setLatitude("42.6977");
		bean.setLongitude("23.3219");
		bean.setStaticAmbient(true);
		bean.setParentAmbient("Building");
		bean.setAmbientType(AmbientType.TEMPERATURE_SENSOR);
		bean.setActiveAmbient(false);

		assertEquals("Sensor1", bean.getName());
		assertEquals("Building A", bean.getLocation());
		assertEquals("42.6977", bean.getLatitude());
		assertEquals("23.3219", bean.getLongitude());
		assertTrue(bean.isStaticAmbient());
		assertEquals("Building", bean.getParentAmbient());
		assertEquals(AmbientType.TEMPERATURE_SENSOR, bean.getAmbientType());
		assertFalse(bean.isActiveAmbient());
	}

	@Test
	public void testSetNameOverwritesPreviousValue() {
		CsvAmbientBean bean = new CsvAmbientBean();
		bean.setName("First");
		bean.setName("Second");
		assertEquals("Second", bean.getName());
	}

	@Test
	public void testStaticAmbientDefaultIsFalse() {
		CsvAmbientBean bean = new CsvAmbientBean();
		assertFalse(bean.isStaticAmbient());
	}

	@Test
	public void testActiveAmbientDefaultIsFalse() {
		CsvAmbientBean bean = new CsvAmbientBean();
		assertFalse(bean.isActiveAmbient());
	}

	@Test
	public void testSetStaticAmbientToTrue() {
		CsvAmbientBean bean = new CsvAmbientBean();
		bean.setStaticAmbient(true);
		assertTrue(bean.isStaticAmbient());
	}

	@Test
	public void testSetActiveAmbientToTrue() {
		CsvAmbientBean bean = new CsvAmbientBean();
		bean.setActiveAmbient(true);
		assertTrue(bean.isActiveAmbient());
	}

	@Test
	public void testAllAmbientTypes() {
		CsvAmbientBean bean = new CsvAmbientBean();
		for (AmbientType type : AmbientType.values()) {
			bean.setAmbientType(type);
			assertEquals(type, bean.getAmbientType());
		}
	}

	@Test
	public void testNullValues() {
		CsvAmbientBean bean = new CsvAmbientBean();
		bean.setName(null);
		bean.setLocation(null);
		bean.setLatitude(null);
		bean.setLongitude(null);
		bean.setParentAmbient(null);
		bean.setAmbientType(null);

		assertNull(bean.getName());
		assertNull(bean.getLocation());
		assertNull(bean.getLatitude());
		assertNull(bean.getLongitude());
		assertNull(bean.getParentAmbient());
		assertNull(bean.getAmbientType());
	}

	@Test
	public void testEmptyStringValues() {
		CsvAmbientBean bean = new CsvAmbientBean();
		bean.setName("");
		bean.setLocation("");
		bean.setLatitude("");
		bean.setLongitude("");
		bean.setParentAmbient("");

		assertEquals("", bean.getName());
		assertEquals("", bean.getLocation());
		assertEquals("", bean.getLatitude());
		assertEquals("", bean.getLongitude());
		assertEquals("", bean.getParentAmbient());
	}
}

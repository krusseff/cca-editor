package com.university.cca.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AmbientTypeTest {

	@Test
	public void testAllValuesExist() {
		AmbientType[] values = AmbientType.values();
		
		assertNotNull(values);
		assertEquals(45, values.length);
	}

	@Test
	public void testValueOfKnownTypes() {
		assertEquals(AmbientType.CITY, AmbientType.valueOf("CITY"));
		assertEquals(AmbientType.HOSPITAL, AmbientType.valueOf("HOSPITAL"));
		assertEquals(AmbientType.SCHOOL, AmbientType.valueOf("SCHOOL"));
		assertEquals(AmbientType.BUS, AmbientType.valueOf("BUS"));
		assertEquals(AmbientType.DRONE, AmbientType.valueOf("DRONE"));
		assertEquals(AmbientType.TRACTOR, AmbientType.valueOf("TRACTOR"));
		assertEquals(AmbientType.OTHER, AmbientType.valueOf("OTHER"));
	}

	@Test
	public void testValueOfInvalidThrows() {
		assertThrows(IllegalArgumentException.class, () ->
			AmbientType.valueOf("NONEXISTENT")
		);
	}

	@Test
	public void testSensorTypesExist() {
		assertNotNull(AmbientType.valueOf("CAMERA_SENSOR"));
		assertNotNull(AmbientType.valueOf("SMOKE_SENSOR"));
		assertNotNull(AmbientType.valueOf("TEMPERATURE_SENSOR"));
		assertNotNull(AmbientType.valueOf("OXYGEN_SENSOR"));
		assertNotNull(AmbientType.valueOf("AUDIO_SENSOR"));
		assertNotNull(AmbientType.valueOf("OTHER_SENSOR"));
	}

	@Test
	public void testAgricultureTypesExist() {
		assertNotNull(AmbientType.valueOf("ORCHARD"));
		assertNotNull(AmbientType.valueOf("GARDEN"));
		assertNotNull(AmbientType.valueOf("GREENHOUSE"));
		assertNotNull(AmbientType.valueOf("TRACTOR"));
		assertNotNull(AmbientType.valueOf("PLANTER"));
		assertNotNull(AmbientType.valueOf("HARVESTER"));
		assertNotNull(AmbientType.valueOf("MOWER"));
		assertNotNull(AmbientType.valueOf("BALER"));
	}

	@Test
	public void testVehicleTypesExist() {
		assertNotNull(AmbientType.valueOf("CAR"));
		assertNotNull(AmbientType.valueOf("TAXI"));
		assertNotNull(AmbientType.valueOf("LORRY"));
		assertNotNull(AmbientType.valueOf("TRAIN"));
		assertNotNull(AmbientType.valueOf("MOTORBIKE"));
		assertNotNull(AmbientType.valueOf("BUS"));
	}
}

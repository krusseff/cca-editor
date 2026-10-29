package com.university.cca.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.university.cca.entities.Ambient;
import com.university.cca.entities.AmbientStatistics;
import com.university.cca.enums.AmbientType;

public class AmbientStatisticsRepositoryTest {

	// --- calculateStatistics ---

	@Test
	public void testCalculateStatistics_emptyList() {
		AmbientStatistics stats = AmbientStatisticsRepository.calculateStatistics(Collections.emptyList());

		assertNotNull(stats);
		assertEquals(0, stats.getTotalCount());
		assertEquals(0, stats.getStaticCount());
		assertEquals(0, stats.getNonStaticCount());
		assertEquals(0, stats.getActiveCount());
		assertEquals(0, stats.getInactiveCount());
	}

	@Test
	public void testCalculateStatistics_singleStaticActive() {
		Ambient ambient = createAmbient("Hospital1", true, true);

		AmbientStatistics stats = AmbientStatisticsRepository.calculateStatistics(
			Collections.singletonList(ambient)
		);

		assertEquals(1, stats.getTotalCount());
		assertEquals(1, stats.getStaticCount());
		assertEquals(0, stats.getNonStaticCount());
		assertEquals(1, stats.getActiveCount());
		assertEquals(0, stats.getInactiveCount());
	}

	@Test
	public void testCalculateStatistics_singleNonStaticInactive() {
		Ambient ambient = createAmbient("Drone1", false, false);

		AmbientStatistics stats = AmbientStatisticsRepository.calculateStatistics(
			Collections.singletonList(ambient)
		);

		assertEquals(1, stats.getTotalCount());
		assertEquals(0, stats.getStaticCount());
		assertEquals(1, stats.getNonStaticCount());
		assertEquals(0, stats.getActiveCount());
		assertEquals(1, stats.getInactiveCount());
	}

	@Test
	public void testCalculateStatistics_mixedAmbients() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Hospital1", true, true),
			createAmbient("Car1", false, true),
			createAmbient("Sensor1", true, false),
			createAmbient("Drone1", false, false),
			createAmbient("City1", true, true)
		);

		AmbientStatistics stats = AmbientStatisticsRepository.calculateStatistics(ambients);

		assertEquals(5, stats.getTotalCount());
		assertEquals(3, stats.getStaticCount());
		assertEquals(2, stats.getNonStaticCount());
		assertEquals(3, stats.getActiveCount());
		assertEquals(2, stats.getInactiveCount());
	}

	@Test
	public void testCalculateStatistics_allStatic() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("A", true, true),
			createAmbient("B", true, false),
			createAmbient("C", true, true)
		);

		AmbientStatistics stats = AmbientStatisticsRepository.calculateStatistics(ambients);

		assertEquals(3, stats.getTotalCount());
		assertEquals(3, stats.getStaticCount());
		assertEquals(0, stats.getNonStaticCount());
	}

	@Test
	public void testCalculateStatistics_allActive() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("A", true, true),
			createAmbient("B", false, true)
		);

		AmbientStatistics stats = AmbientStatisticsRepository.calculateStatistics(ambients);

		assertEquals(2, stats.getActiveCount());
		assertEquals(0, stats.getInactiveCount());
	}

	@Test
	public void testCalculateStatistics_allInactive() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("A", true, false),
			createAmbient("B", false, false)
		);

		AmbientStatistics stats = AmbientStatisticsRepository.calculateStatistics(ambients);

		assertEquals(0, stats.getActiveCount());
		assertEquals(2, stats.getInactiveCount());
	}

	@Test
	public void testCalculateStatistics_totalEqualsStaticPlusNonStatic() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("A", true, true),
			createAmbient("B", false, false),
			createAmbient("C", true, true),
			createAmbient("D", false, true)
		);

		AmbientStatistics stats = AmbientStatisticsRepository.calculateStatistics(ambients);

		assertEquals(stats.getTotalCount(), stats.getStaticCount() + stats.getNonStaticCount());
		assertEquals(stats.getTotalCount(), stats.getActiveCount() + stats.getInactiveCount());
	}

	// --- helpers ---

	private Ambient createAmbient(String name, boolean isStatic, boolean isActive) {
		Ambient ambient = new Ambient();
		ambient.setName(name);
		ambient.setLocation("Loc");
		ambient.setLatitude("42.0");
		ambient.setLongitude("23.0");
		ambient.setStaticAmbient(isStatic);
		ambient.setActiveAmbient(isActive);
		ambient.setParentAmbient("");
		ambient.setAmbientType(AmbientType.CITY);
		return ambient;
	}
}

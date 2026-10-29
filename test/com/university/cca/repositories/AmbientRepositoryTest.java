package com.university.cca.repositories;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Files;
import java.nio.file.Path;

import com.university.cca.entities.Ambient;
import com.university.cca.enums.AmbientType;

public class AmbientRepositoryTest {

	@TempDir
	Path tempFolder;

	// --- getAllAmbients(filePath) ---

	@Test
	public void testGetAllAmbients_returnsAmbientsFromFile() throws IOException {
		File csv = createAmbientsCsv(
			"\"HOSPITAL\",\"Hospital1\",\"Lovech\",\"43.0\",\"24.0\",\"true\",\"City1\",\"true\"\n" +
			"\"CITY\",\"City1\",\"Bulgaria\",\"42.0\",\"23.0\",\"true\",\"\",\"true\"\n"
		);

		List<Ambient> ambients = AmbientRepository.getAllAmbients(csv.getAbsolutePath());

		assertEquals(2, ambients.size());
		assertEquals("Hospital1", ambients.get(0).getName());
		assertEquals("City1", ambients.get(1).getName());
	}

	@Test
	public void testGetAllAmbients_emptyFile() throws IOException {
		File csv = Files.createFile(tempFolder.resolve("empty.csv")).toFile();

		List<Ambient> ambients = AmbientRepository.getAllAmbients(csv.getAbsolutePath());

		assertNotNull(ambients);
		assertTrue(ambients.isEmpty());
	}

	// --- getAllAmbientsSortedByName(filePath) ---

	@Test
	public void testGetAllAmbientsSortedByName_sortedAlphabetically() throws IOException {
		File csv = createAmbientsCsv(
			"\"HOSPITAL\",\"Zeta\",\"Loc\",\"43.0\",\"24.0\",\"true\",\"\",\"true\"\n" +
			"\"CITY\",\"Alpha\",\"Loc\",\"42.0\",\"23.0\",\"true\",\"\",\"true\"\n" +
			"\"DRONE\",\"Mango\",\"Loc\",\"41.0\",\"22.0\",\"false\",\"\",\"true\"\n"
		);

		List<Ambient> sorted = AmbientRepository.getAllAmbientsSortedByName(csv.getAbsolutePath());

		assertEquals(3, sorted.size());
		assertEquals("Alpha", sorted.get(0).getName());
		assertEquals("Mango", sorted.get(1).getName());
		assertEquals("Zeta", sorted.get(2).getName());
	}

	@Test
	public void testGetAllAmbientsSortedByName_emptyFile() throws IOException {
		File csv = Files.createFile(tempFolder.resolve("empty.csv")).toFile();

		List<Ambient> sorted = AmbientRepository.getAllAmbientsSortedByName(csv.getAbsolutePath());

		assertTrue(sorted.isEmpty());
	}

	// --- sortByName (package-visible) ---

	@Test
	public void testSortByName_sortsCorrectly() {
		Ambient a = createAmbient("Zebra", true, true, "");
		Ambient b = createAmbient("Apple", true, true, "");
		Ambient c = createAmbient("Banana", true, true, "");

		List<Ambient> sorted = AmbientRepository.sortByName(Arrays.asList(a, b, c));

		assertEquals("Apple", sorted.get(0).getName());
		assertEquals("Banana", sorted.get(1).getName());
		assertEquals("Zebra", sorted.get(2).getName());
	}

	@Test
	public void testSortByName_emptyList() {
		List<Ambient> sorted = AmbientRepository.sortByName(Collections.emptyList());
		assertTrue(sorted.isEmpty());
	}

	// --- getAllAmbientNames(filePath) ---

	@Test
	public void testGetAllAmbientNames_returnsNames() throws IOException {
		File csv = createAmbientsCsv(
			"\"HOSPITAL\",\"Hospital1\",\"Loc\",\"43.0\",\"24.0\",\"true\",\"\",\"true\"\n" +
			"\"CITY\",\"City1\",\"Loc\",\"42.0\",\"23.0\",\"true\",\"\",\"true\"\n"
		);

		List<String> names = AmbientRepository.getAllAmbientNames(csv.getAbsolutePath());

		assertEquals(2, names.size());
		assertTrue(names.contains("Hospital1"));
		assertTrue(names.contains("City1"));
	}

	// --- extractNames (package-visible) ---

	@Test
	public void testExtractNames_mapsToNames() {
		Ambient a = createAmbient("Sensor1", true, true, "");
		Ambient b = createAmbient("Gateway1", true, true, "");

		List<String> names = AmbientRepository.extractNames(Arrays.asList(a, b));

		assertEquals(Arrays.asList("Sensor1", "Gateway1"), names);
	}

	@Test
	public void testExtractNames_emptyList() {
		assertTrue(AmbientRepository.extractNames(Collections.emptyList()).isEmpty());
	}

	// --- getActiveAmbientNames(filePath) ---

	@Test
	public void testGetActiveAmbientNames_filtersActiveOnly() throws IOException {
		File csv = createAmbientsCsv(
			"\"HOSPITAL\",\"Active1\",\"Loc\",\"43.0\",\"24.0\",\"true\",\"\",\"true\"\n" +
			"\"CITY\",\"Inactive1\",\"Loc\",\"42.0\",\"23.0\",\"true\",\"\",\"false\"\n" +
			"\"DRONE\",\"Active2\",\"Loc\",\"41.0\",\"22.0\",\"false\",\"\",\"true\"\n"
		);

		List<String> activeNames = AmbientRepository.getActiveAmbientNames(csv.getAbsolutePath());

		assertEquals(2, activeNames.size());
		assertTrue(activeNames.contains("Active1"));
		assertTrue(activeNames.contains("Active2"));
	}

	// --- extractActiveNames (package-visible) ---

	@Test
	public void testExtractActiveNames_filtersActive() {
		Ambient active = createAmbient("A", true, true, "");
		Ambient inactive = createAmbient("B", true, false, "");
		Ambient active2 = createAmbient("C", false, true, "");

		List<String> activeNames = AmbientRepository.extractActiveNames(Arrays.asList(active, inactive, active2));

		assertEquals(2, activeNames.size());
		assertTrue(activeNames.contains("A"));
		assertTrue(activeNames.contains("C"));
	}

	@Test
	public void testExtractActiveNames_allInactive() {
		Ambient a = createAmbient("A", true, false, "");
		Ambient b = createAmbient("B", true, false, "");

		assertTrue(AmbientRepository.extractActiveNames(Arrays.asList(a, b)).isEmpty());
	}

	@Test
	public void testExtractActiveNames_emptyList() {
		assertTrue(AmbientRepository.extractActiveNames(Collections.emptyList()).isEmpty());
	}

	// --- getActiveAmbientNamesSorted(filePath) ---

	@Test
	public void testGetActiveAmbientNamesSorted_sortedAlphabetically() throws IOException {
		File csv = createAmbientsCsv(
			"\"HOSPITAL\",\"Zeta\",\"Loc\",\"43.0\",\"24.0\",\"true\",\"\",\"true\"\n" +
			"\"CITY\",\"Alpha\",\"Loc\",\"42.0\",\"23.0\",\"true\",\"\",\"true\"\n" +
			"\"DRONE\",\"InactiveOne\",\"Loc\",\"41.0\",\"22.0\",\"false\",\"\",\"false\"\n" +
			"\"CAR\",\"Mango\",\"Loc\",\"40.0\",\"21.0\",\"false\",\"\",\"true\"\n"
		);

		String[] sorted = AmbientRepository.getActiveAmbientNamesSorted(csv.getAbsolutePath());

		assertArrayEquals(new String[]{"Alpha", "Mango", "Zeta"}, sorted);
	}

	// --- toSortedArray (package-visible) ---

	@Test
	public void testToSortedArray_sortsAndConverts() {
		String[] result = AmbientRepository.toSortedArray(Arrays.asList("Zeta", "Alpha", "Mango"));
		assertArrayEquals(new String[]{"Alpha", "Mango", "Zeta"}, result);
	}

	@Test
	public void testToSortedArray_emptyList() {
		String[] result = AmbientRepository.toSortedArray(Collections.emptyList());
		assertEquals(0, result.length);
	}

	// --- getParentAmbientName(name, filePath) ---

	@Test
	public void testGetParentAmbientName_findsParent() throws IOException {
		File csv = createAmbientsCsv(
			"\"HOSPITAL\",\"Hospital1\",\"Loc\",\"43.0\",\"24.0\",\"true\",\"City1\",\"true\"\n" +
			"\"CITY\",\"City1\",\"Loc\",\"42.0\",\"23.0\",\"true\",\"Country\",\"true\"\n"
		);

		String parent = AmbientRepository.getParentAmbientName("Hospital1", csv.getAbsolutePath());

		assertEquals("City1", parent);
	}

	@Test
	public void testGetParentAmbientName_notFound() throws IOException {
		File csv = createAmbientsCsv(
			"\"HOSPITAL\",\"Hospital1\",\"Loc\",\"43.0\",\"24.0\",\"true\",\"City1\",\"true\"\n"
		);

		String parent = AmbientRepository.getParentAmbientName("NonExistent", csv.getAbsolutePath());

		assertNull(parent);
	}

	// --- findParentName (package-visible) ---

	@Test
	public void testFindParentName_found() {
		Ambient child = createAmbient("Hospital1", true, true, "City1");

		String parent = AmbientRepository.findParentName(Arrays.asList(child), "Hospital1");

		assertEquals("City1", parent);
	}

	@Test
	public void testFindParentName_notFound() {
		Ambient a = createAmbient("Hospital1", true, true, "City1");

		String parent = AmbientRepository.findParentName(Arrays.asList(a), "Unknown");

		assertNull(parent);
	}

	@Test
	public void testFindParentName_emptyList() {
		assertNull(AmbientRepository.findParentName(Collections.emptyList(), "Any"));
	}

	@Test
	public void testFindParentName_emptyParent() {
		Ambient root = createAmbient("Root", true, true, "");

		String parent = AmbientRepository.findParentName(Arrays.asList(root), "Root");

		assertEquals("", parent);
	}

	// --- helpers ---

	private File createAmbientsCsv(String content) throws IOException {
		File csv = Files.createFile(tempFolder.resolve("ambients.csv")).toFile();
		try (FileWriter writer = new FileWriter(csv)) {
			writer.write(content);
		}
		return csv;
	}

	private Ambient createAmbient(String name, boolean isStatic, boolean isActive, String parent) {
		Ambient ambient = new Ambient();
		ambient.setName(name);
		ambient.setLocation("TestLocation");
		ambient.setLatitude("42.0");
		ambient.setLongitude("23.0");
		ambient.setStaticAmbient(isStatic);
		ambient.setActiveAmbient(isActive);
		ambient.setParentAmbient(parent);
		ambient.setAmbientType(AmbientType.CITY);
		return ambient;
	}
}

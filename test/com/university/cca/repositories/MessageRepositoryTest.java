package com.university.cca.repositories;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.university.cca.entities.Message;

public class MessageRepositoryTest {

	@TempDir
	Path tempFolder;

	// --- getAllMessages(filePath) ---

	@Test
	public void testGetAllMessages_returnsMessagesFromFile() throws IOException {
		File csv = createMessagesCsv(
			"\"SensorA\",\"Gateway\",\"Hub\",\"OK\",\"Temperature is 25C\"\n" +
			"\"Drone1\",\"HQ\",\"null\",\"null\",\"Scan complete\"\n"
		);

		List<Message> messages = MessageRepository.getAllMessages(csv.getAbsolutePath());

		assertEquals(2, messages.size());
		assertEquals("SensorA", messages.get(0).getSenderAmbient());
		assertEquals("Drone1", messages.get(1).getSenderAmbient());
	}

	@Test
	public void testGetAllMessages_emptyFile() throws IOException {
		File csv = Files.createFile(tempFolder.resolve("empty.csv")).toFile();

		List<Message> messages = MessageRepository.getAllMessages(csv.getAbsolutePath());

		assertNotNull(messages);
		assertTrue(messages.isEmpty());
	}

	// --- getAmbientMessagesSorted(filePath) ---

	@Test
	public void testGetAmbientMessagesSorted_sortedAlphabetically() throws IOException {
		File csv = createMessagesCsv(
			"\"A\",\"B\",\"null\",\"null\",\"Zeta message\"\n" +
			"\"C\",\"D\",\"null\",\"null\",\"Alpha message\"\n" +
			"\"E\",\"F\",\"null\",\"null\",\"Mango message\"\n"
		);

		String[] sorted = MessageRepository.getAmbientMessagesSorted(csv.getAbsolutePath());

		assertArrayEquals(new String[]{"Alpha message", "Mango message", "Zeta message"}, sorted);
	}

	@Test
	public void testGetAmbientMessagesSorted_emptyFile() throws IOException {
		File csv = Files.createFile(tempFolder.resolve("empty.csv")).toFile();

		String[] sorted = MessageRepository.getAmbientMessagesSorted(csv.getAbsolutePath());

		assertEquals(0, sorted.length);
	}

	// --- sortMessageTexts (package-visible) ---

	@Test
	public void testSortMessageTexts_sortsCorrectly() {
		Message m1 = new Message("A", "B", "null", "null", "Zebra");
		Message m2 = new Message("C", "D", "null", "null", "Apple");
		Message m3 = new Message("E", "F", "null", "null", "Banana");

		String[] sorted = MessageRepository.sortMessageTexts(Arrays.asList(m1, m2, m3));

		assertArrayEquals(new String[]{"Apple", "Banana", "Zebra"}, sorted);
	}

	@Test
	public void testSortMessageTexts_emptyList() {
		String[] sorted = MessageRepository.sortMessageTexts(Collections.emptyList());
		assertEquals(0, sorted.length);
	}

	@Test
	public void testSortMessageTexts_singleMessage() {
		Message m = new Message("A", "B", "null", "null", "Only message");
		String[] sorted = MessageRepository.sortMessageTexts(Arrays.asList(m));
		assertArrayEquals(new String[]{"Only message"}, sorted);
	}

	// --- helpers ---

	private File createMessagesCsv(String content) throws IOException {
		File csv = Files.createFile(tempFolder.resolve("messages.csv")).toFile();
		try (FileWriter writer = new FileWriter(csv)) {
			writer.write(content);
		}
		return csv;
	}
}

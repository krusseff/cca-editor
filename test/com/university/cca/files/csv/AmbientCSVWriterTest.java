package com.university.cca.files.csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Files;
import java.nio.file.Path;

import com.university.cca.entities.Ambient;
import com.university.cca.entities.AmbientStatistics;
import com.university.cca.entities.Message;
import com.university.cca.entities.MessageStatistics;
import com.university.cca.enums.AmbientType;

public class AmbientCSVWriterTest {

	@TempDir
	Path tempFolder;

	// --- writeToCsv (raw string) ---

	@Test
	public void testWriteToCsv_writesStringData() throws IOException {
		File csvFile = new File(tempFolder.toFile(), "output.csv");
		String data = "\"test\",\"data\",\"here\"";

		AmbientCSVWriter.writeToCsv(data, csvFile.getAbsolutePath());

		assertTrue(csvFile.exists());
		assertTrue(csvFile.length() > 0);
	}

	@Test
	public void testWriteToCsv_createsFileIfNotExists() {
		File csvFile = new File(tempFolder.toFile(), "new_file.csv");

		AmbientCSVWriter.writeToCsv("data", csvFile.getAbsolutePath());

		assertTrue(csvFile.exists());
	}

	// --- writeAmbientToCsv (overloaded with path) ---

	@Test
	public void testWriteAmbientToCsv_singleAmbient() {
		File csvFile = new File(tempFolder.toFile(), "ambient.csv");
		Ambient ambient = createTestAmbient("Hospital1", AmbientType.HOSPITAL);

		AmbientCSVWriter.writeAmbientToCsv(ambient, csvFile.getAbsolutePath());

		assertTrue(csvFile.exists());
		assertTrue(csvFile.length() > 0);
	}

	@Test
	public void testWriteAmbientToCsv_roundTrip() {
		File csvFile = new File(tempFolder.toFile(), "ambient_rt.csv");
		Ambient original = createTestAmbient("RoundTrip", AmbientType.CITY);

		AmbientCSVWriter.writeAmbientToCsv(original, csvFile.getAbsolutePath());
		List<Ambient> readBack = AmbientCSVReader.getAllAmbients(csvFile.getAbsolutePath());

		assertEquals(1, readBack.size());
		Ambient restored = readBack.get(0);
		assertEquals(original.getName(), restored.getName());
		assertEquals(original.getLocation(), restored.getLocation());
		assertEquals(original.getLatitude(), restored.getLatitude());
		assertEquals(original.getLongitude(), restored.getLongitude());
		assertEquals(original.isStaticAmbient(), restored.isStaticAmbient());
		assertEquals(original.isActiveAmbient(), restored.isActiveAmbient());
		assertEquals(original.getParentAmbient(), restored.getParentAmbient());
		assertEquals(original.getAmbientType(), restored.getAmbientType());
	}

	// --- writeAmbientsToCsv (list) ---

	@Test
	public void testWriteAmbientsToCsv_multipleAmbients() {
		File csvFile = new File(tempFolder.toFile(), "ambients_multi.csv");
		List<Ambient> ambients = Arrays.asList(
			createTestAmbient("A1", AmbientType.HOSPITAL),
			createTestAmbient("A2", AmbientType.CITY),
			createTestAmbient("A3", AmbientType.DRONE)
		);

		AmbientCSVWriter.writeAmbientsToCsv(csvFile.getAbsolutePath(), ambients);
		List<Ambient> readBack = AmbientCSVReader.getAllAmbients(csvFile.getAbsolutePath());

		assertEquals(3, readBack.size());
		assertEquals("A1", readBack.get(0).getName());
		assertEquals("A2", readBack.get(1).getName());
		assertEquals("A3", readBack.get(2).getName());
	}

	@Test
	public void testWriteAmbientsToCsv_emptyList() {
		File csvFile = new File(tempFolder.toFile(), "ambients_empty.csv");
		List<Ambient> empty = Collections.emptyList();

		AmbientCSVWriter.writeAmbientsToCsv(csvFile.getAbsolutePath(), empty);

		assertTrue(csvFile.exists());
		List<Ambient> readBack = AmbientCSVReader.getAllAmbients(csvFile.getAbsolutePath());
		assertTrue(readBack.isEmpty());
	}

	@Test
	public void testWriteAmbientsToCsv_preservesAllFields() {
		File csvFile = new File(tempFolder.toFile(), "ambients_full.csv");
		Ambient ambient = new Ambient();
		ambient.setName("FullTest");
		ambient.setLocation("TestCity");
		ambient.setLatitude("42.6977");
		ambient.setLongitude("23.3219");
		ambient.setStaticAmbient(false);
		ambient.setActiveAmbient(true);
		ambient.setParentAmbient("Country");
		ambient.setAmbientType(AmbientType.TEMPERATURE_SENSOR);

		AmbientCSVWriter.writeAmbientsToCsv(csvFile.getAbsolutePath(), Collections.singletonList(ambient));
		List<Ambient> readBack = AmbientCSVReader.getAllAmbients(csvFile.getAbsolutePath());

		assertEquals(1, readBack.size());
		Ambient restored = readBack.get(0);
		assertEquals("FullTest", restored.getName());
		assertEquals("TestCity", restored.getLocation());
		assertEquals("42.6977", restored.getLatitude());
		assertEquals("23.3219", restored.getLongitude());
		assertEquals(false, restored.isStaticAmbient());
		assertTrue(restored.isActiveAmbient());
		assertEquals("Country", restored.getParentAmbient());
		assertEquals(AmbientType.TEMPERATURE_SENSOR, restored.getAmbientType());
	}

	// --- writeMessageToCsv (overloaded with path) ---

	@Test
	public void testWriteMessageToCsv_singleMessage() {
		File csvFile = new File(tempFolder.toFile(), "message.csv");
		Message message = createTestMessage("Sender1", "Recipient1", "msg1");

		AmbientCSVWriter.writeMessageToCsv(message, csvFile.getAbsolutePath());

		assertTrue(csvFile.exists());
		assertTrue(csvFile.length() > 0);
	}

	@Test
	public void testWriteMessageToCsv_roundTrip() {
		File csvFile = new File(tempFolder.toFile(), "message_rt.csv");
		Message original = createTestMessage("Drone", "HQ", "Scan complete");

		AmbientCSVWriter.writeMessageToCsv(original, csvFile.getAbsolutePath());
		List<Message> readBack = AmbientCSVReader.getAllMessages(csvFile.getAbsolutePath());

		assertEquals(1, readBack.size());
		Message restored = readBack.get(0);
		assertEquals(original.getSenderAmbient(), restored.getSenderAmbient());
		assertEquals(original.getRecipientAmbient(), restored.getRecipientAmbient());
		assertEquals(original.getPassMessageTo(), restored.getPassMessageTo());
		assertEquals(original.getRespondToMessage(), restored.getRespondToMessage());
		assertEquals(original.getMessage(), restored.getMessage());
	}

	// --- writeMessagesToCsv (list) ---

	@Test
	public void testWriteMessagesToCsv_multipleMessages() {
		File csvFile = new File(tempFolder.toFile(), "messages_multi.csv");
		List<Message> messages = Arrays.asList(
			createTestMessage("A", "B", "hello"),
			createTestMessage("C", "D", "world"),
			createTestMessage("E", "F", "test")
		);

		AmbientCSVWriter.writeMessagesToCsv(csvFile.getAbsolutePath(), messages);
		List<Message> readBack = AmbientCSVReader.getAllMessages(csvFile.getAbsolutePath());

		assertEquals(3, readBack.size());
		assertEquals("A", readBack.get(0).getSenderAmbient());
		assertEquals("C", readBack.get(1).getSenderAmbient());
		assertEquals("E", readBack.get(2).getSenderAmbient());
	}

	@Test
	public void testWriteMessagesToCsv_emptyList() {
		File csvFile = new File(tempFolder.toFile(), "messages_empty.csv");
		List<Message> empty = Collections.emptyList();

		AmbientCSVWriter.writeMessagesToCsv(csvFile.getAbsolutePath(), empty);

		assertTrue(csvFile.exists());
		List<Message> readBack = AmbientCSVReader.getAllMessages(csvFile.getAbsolutePath());
		assertTrue(readBack.isEmpty());
	}

	// --- writeAmbientStatisticsToCsv ---

	@Test
	public void testWriteAmbientStatisticsToCsv_writesFile() {
		File csvFile = new File(tempFolder.toFile(), "ambient_stats.csv");
		AmbientStatistics stats = new AmbientStatistics(10, 6, 4, 8, 2);

		AmbientCSVWriter.writeAmbientStatisticsToCsv(stats, csvFile.getAbsolutePath());

		assertTrue(csvFile.exists());
		assertTrue(csvFile.length() > 0);
	}

	// --- writeMessageStatisticsToCsv ---

	@Test
	public void testWriteMessageStatisticsToCsv_writesFile() {
		File csvFile = new File(tempFolder.toFile(), "message_stats.csv");
		List<MessageStatistics> stats = Arrays.asList(
			new MessageStatistics("Hospital", 5, 3),
			new MessageStatistics("Drone", 10, 7)
		);

		AmbientCSVWriter.writeMessageStatisticsToCsv(stats, csvFile.getAbsolutePath());

		assertTrue(csvFile.exists());
		assertTrue(csvFile.length() > 0);
	}

	@Test
	public void testWriteMessageStatisticsToCsv_emptyList() {
		File csvFile = new File(tempFolder.toFile(), "message_stats_empty.csv");
		List<MessageStatistics> empty = new ArrayList<>();

		AmbientCSVWriter.writeMessageStatisticsToCsv(empty, csvFile.getAbsolutePath());

		assertTrue(csvFile.exists());
	}

	// --- write + append behaviour ---

	@Test
	public void testWriteAmbientToCsv_appendsMultipleCalls() {
		File csvFile = new File(tempFolder.toFile(), "append_test.csv");
		Ambient a1 = createTestAmbient("First", AmbientType.HOSPITAL);
		Ambient a2 = createTestAmbient("Second", AmbientType.CITY);

		AmbientCSVWriter.writeAmbientToCsv(a1, csvFile.getAbsolutePath());
		AmbientCSVWriter.writeAmbientToCsv(a2, csvFile.getAbsolutePath());

		List<Ambient> readBack = AmbientCSVReader.getAllAmbients(csvFile.getAbsolutePath());
		assertEquals(2, readBack.size());
		assertEquals("First", readBack.get(0).getName());
		assertEquals("Second", readBack.get(1).getName());
	}

	// --- helpers ---

	private Ambient createTestAmbient(String name, AmbientType type) {
		Ambient ambient = new Ambient();
		ambient.setName(name);
		ambient.setLocation("TestLocation");
		ambient.setLatitude("42.0");
		ambient.setLongitude("23.0");
		ambient.setStaticAmbient(true);
		ambient.setActiveAmbient(true);
		ambient.setParentAmbient("Parent");
		ambient.setAmbientType(type);
		return ambient;
	}

	private Message createTestMessage(String sender, String recipient, String messageText) {
		Message message = new Message();
		message.setSenderAmbient(sender);
		message.setRecipientAmbient(recipient);
		message.setPassMessageTo("Hub");
		message.setRespondToMessage("OK");
		message.setMessage(messageText);
		return message;
	}
}

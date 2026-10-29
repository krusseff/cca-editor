package com.university.cca.files.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.university.cca.entities.Ambient;
import com.university.cca.entities.Message;
import com.university.cca.enums.AmbientType;

public class AmbientCSVReaderTest {

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	// --- getAllAmbients(filePath) ---

	@Test
	public void testGetAllAmbients_singleAmbient() throws IOException {
		File csvFile = tempFolder.newFile("ambients.csv");
		writeToFile(csvFile, "\"HOSPITAL\",\"CityHospital\",\"Lovech\",\"43.1369\",\"24.7174\",\"true\",\"City\",\"true\"\n");

		List<Ambient> ambients = AmbientCSVReader.getAllAmbients(csvFile.getAbsolutePath());

		assertEquals(1, ambients.size());
		Ambient ambient = ambients.get(0);
		assertEquals(AmbientType.HOSPITAL, ambient.getAmbientType());
		assertEquals("CityHospital", ambient.getName());
		assertEquals("Lovech", ambient.getLocation());
		assertEquals("43.1369", ambient.getLatitude());
		assertEquals("24.7174", ambient.getLongitude());
		assertTrue(ambient.isStaticAmbient());
		assertEquals("City", ambient.getParentAmbient());
		assertTrue(ambient.isActiveAmbient());
	}

	@Test
	public void testGetAllAmbients_multipleAmbients() throws IOException {
		File csvFile = tempFolder.newFile("ambients.csv");
		StringBuilder sb = new StringBuilder();
		sb.append("\"HOSPITAL\",\"Hospital1\",\"Loc1\",\"43.0\",\"24.0\",\"true\",\"City\",\"true\"\n");
		sb.append("\"CITY\",\"City1\",\"Loc2\",\"42.0\",\"23.0\",\"true\",\"\",\"true\"\n");
		sb.append("\"DRONE\",\"Drone1\",\"Loc3\",\"41.0\",\"22.0\",\"false\",\"City1\",\"true\"\n");
		writeToFile(csvFile, sb.toString());

		List<Ambient> ambients = AmbientCSVReader.getAllAmbients(csvFile.getAbsolutePath());

		assertEquals(3, ambients.size());
		assertEquals("Hospital1", ambients.get(0).getName());
		assertEquals("City1", ambients.get(1).getName());
		assertEquals("Drone1", ambients.get(2).getName());
	}

	@Test
	public void testGetAllAmbients_emptyFile() throws IOException {
		File csvFile = tempFolder.newFile("empty_ambients.csv");

		List<Ambient> ambients = AmbientCSVReader.getAllAmbients(csvFile.getAbsolutePath());

		assertNotNull(ambients);
		assertTrue(ambients.isEmpty());
	}

	@Test
	public void testGetAllAmbients_nonExistentFile() {
		String fakePath = new File(tempFolder.getRoot(), "nonexistent.csv").getAbsolutePath();

		List<Ambient> ambients = AmbientCSVReader.getAllAmbients(fakePath);

		assertNotNull(ambients);
		assertTrue(ambients.isEmpty());
	}

	@Test
	public void testGetAllAmbients_inactiveAmbient() throws IOException {
		File csvFile = tempFolder.newFile("ambients.csv");
		writeToFile(csvFile, "\"CAMERA_SENSOR\",\"Cam1\",\"Entrance\",\"43.0\",\"24.0\",\"true\",\"Building\",\"false\"\n");

		List<Ambient> ambients = AmbientCSVReader.getAllAmbients(csvFile.getAbsolutePath());

		assertEquals(1, ambients.size());
		assertEquals("Cam1", ambients.get(0).getName());
		assertEquals(AmbientType.CAMERA_SENSOR, ambients.get(0).getAmbientType());
		assertTrue(ambients.get(0).isStaticAmbient());
		assertEquals(false, ambients.get(0).isActiveAmbient());
	}

	@Test
	public void testGetAllAmbients_nonStaticAmbient() throws IOException {
		File csvFile = tempFolder.newFile("ambients.csv");
		writeToFile(csvFile, "\"CAR\",\"Car1\",\"Highway\",\"42.5\",\"23.5\",\"false\",\"City\",\"true\"\n");

		List<Ambient> ambients = AmbientCSVReader.getAllAmbients(csvFile.getAbsolutePath());

		assertEquals(1, ambients.size());
		assertEquals("Car1", ambients.get(0).getName());
		assertEquals(AmbientType.CAR, ambients.get(0).getAmbientType());
		assertEquals(false, ambients.get(0).isStaticAmbient());
		assertTrue(ambients.get(0).isActiveAmbient());
	}

	// --- getAllMessages(filePath) ---

	@Test
	public void testGetAllMessages_singleMessage() throws IOException {
		File csvFile = tempFolder.newFile("messages.csv");
		writeToFile(csvFile, "\"SensorA\",\"Gateway\",\"Hub\",\"OK\",\"Temperature is 25C\"\n");

		List<Message> messages = AmbientCSVReader.getAllMessages(csvFile.getAbsolutePath());

		assertEquals(1, messages.size());
		Message msg = messages.get(0);
		assertEquals("SensorA", msg.getSenderAmbient());
		assertEquals("Gateway", msg.getRecipientAmbient());
		assertEquals("Hub", msg.getPassMessageTo());
		assertEquals("OK", msg.getRespondToMessage());
		assertEquals("Temperature is 25C", msg.getMessage());
	}

	@Test
	public void testGetAllMessages_multipleMessages() throws IOException {
		File csvFile = tempFolder.newFile("messages.csv");
		StringBuilder sb = new StringBuilder();
		sb.append("\"A\",\"B\",\"C\",\"yes\",\"msg1\"\n");
		sb.append("\"D\",\"E\",\"F\",\"no\",\"msg2\"\n");
		sb.append("\"G\",\"H\",\"I\",\"maybe\",\"msg3\"\n");
		writeToFile(csvFile, sb.toString());

		List<Message> messages = AmbientCSVReader.getAllMessages(csvFile.getAbsolutePath());

		assertEquals(3, messages.size());
		assertEquals("A", messages.get(0).getSenderAmbient());
		assertEquals("D", messages.get(1).getSenderAmbient());
		assertEquals("G", messages.get(2).getSenderAmbient());
	}

	@Test
	public void testGetAllMessages_emptyFile() throws IOException {
		File csvFile = tempFolder.newFile("empty_messages.csv");

		List<Message> messages = AmbientCSVReader.getAllMessages(csvFile.getAbsolutePath());

		assertNotNull(messages);
		assertTrue(messages.isEmpty());
	}

	@Test
	public void testGetAllMessages_nonExistentFile() {
		String fakePath = new File(tempFolder.getRoot(), "nonexistent.csv").getAbsolutePath();

		List<Message> messages = AmbientCSVReader.getAllMessages(fakePath);

		assertNotNull(messages);
		assertTrue(messages.isEmpty());
	}

	// --- helpers ---

	private void writeToFile(File file, String content) throws IOException {
		try (FileWriter writer = new FileWriter(file)) {
			writer.write(content);
		}
	}
}

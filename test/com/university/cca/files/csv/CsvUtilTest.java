package com.university.cca.files.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.university.cca.entities.Ambient;
import com.university.cca.entities.AmbientStatistics;
import com.university.cca.entities.Message;
import com.university.cca.entities.MessageStatistics;
import com.university.cca.enums.AmbientType;

public class CsvUtilTest {

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	// --- convertToAmbient ---

	@Test
	public void testConvertToAmbient_allFieldsMapped() {
		CsvAmbientBean bean = new CsvAmbientBean(
			"Hospital", "Lovech", "43.1369", "24.7174",
			true, "City", AmbientType.HOSPITAL, true
		);

		Ambient ambient = CsvUtil.convertToAmbient(bean);

		assertEquals("Hospital", ambient.getName());
		assertEquals("Lovech", ambient.getLocation());
		assertEquals("43.1369", ambient.getLatitude());
		assertEquals("24.7174", ambient.getLongitude());
		assertTrue(ambient.isStaticAmbient());
		assertEquals("City", ambient.getParentAmbient());
		assertEquals(AmbientType.HOSPITAL, ambient.getAmbientType());
		assertTrue(ambient.isActiveAmbient());
	}

	@Test
	public void testConvertToAmbient_defaultBean() {
		CsvAmbientBean bean = new CsvAmbientBean();

		Ambient ambient = CsvUtil.convertToAmbient(bean);

		assertNotNull(ambient);
		assertEquals(null, ambient.getName());
		assertFalse(ambient.isStaticAmbient());
		assertFalse(ambient.isActiveAmbient());
	}

	// --- convertToCsvAmbientBean ---

	@Test
	public void testConvertToCsvAmbientBean_allFieldsMapped() {
		Ambient ambient = createTestAmbient();

		CsvAmbientBean bean = CsvUtil.convertToCsvAmbientBean(ambient);

		assertEquals("TestAmbient", bean.getName());
		assertEquals("Sofia", bean.getLocation());
		assertEquals("42.6977", bean.getLatitude());
		assertEquals("23.3219", bean.getLongitude());
		assertTrue(bean.isStaticAmbient());
		assertEquals("Country", bean.getParentAmbient());
		assertEquals(AmbientType.CITY, bean.getAmbientType());
		assertTrue(bean.isActiveAmbient());
	}

	@Test
	public void testConvertToAmbient_roundTrip() {
		Ambient original = createTestAmbient();

		CsvAmbientBean bean = CsvUtil.convertToCsvAmbientBean(original);
		Ambient restored = CsvUtil.convertToAmbient(bean);

		assertEquals(original.getName(), restored.getName());
		assertEquals(original.getLocation(), restored.getLocation());
		assertEquals(original.getLatitude(), restored.getLatitude());
		assertEquals(original.getLongitude(), restored.getLongitude());
		assertEquals(original.isStaticAmbient(), restored.isStaticAmbient());
		assertEquals(original.isActiveAmbient(), restored.isActiveAmbient());
		assertEquals(original.getParentAmbient(), restored.getParentAmbient());
		assertEquals(original.getAmbientType(), restored.getAmbientType());
	}

	// --- convertToMessage ---

	@Test
	public void testConvertToMessage_allFieldsMapped() {
		CsvMessageBean bean = new CsvMessageBean(
			"SensorA", "Gateway", "Hub", "OK", "Temperature is 25C"
		);

		Message message = CsvUtil.convertToMessage(bean);

		assertEquals("SensorA", message.getSenderAmbient());
		assertEquals("Gateway", message.getRecipientAmbient());
		assertEquals("Hub", message.getPassMessageTo());
		assertEquals("OK", message.getRespondToMessage());
		assertEquals("Temperature is 25C", message.getMessage());
	}

	@Test
	public void testConvertToMessage_defaultBean() {
		CsvMessageBean bean = new CsvMessageBean();

		Message message = CsvUtil.convertToMessage(bean);

		assertNotNull(message);
		assertEquals(null, message.getSenderAmbient());
		assertEquals(null, message.getRecipientAmbient());
	}

	// --- convertToCsvMessageBean ---

	@Test
	public void testConvertToCsvMessageBean_allFieldsMapped() {
		Message message = createTestMessage();

		CsvMessageBean bean = CsvUtil.convertToCsvMessageBean(message);

		assertEquals("Drone1", bean.getSenderAmbient());
		assertEquals("OperationsCenter", bean.getRecipientAmbient());
		assertEquals("HQ", bean.getPassMessageTo());
		assertEquals("Acknowledged", bean.getRespondToMessage());
		assertEquals("Scan complete", bean.getAmbientMessage());
	}

	@Test
	public void testConvertToMessage_roundTrip() {
		Message original = createTestMessage();

		CsvMessageBean bean = CsvUtil.convertToCsvMessageBean(original);
		Message restored = CsvUtil.convertToMessage(bean);

		assertEquals(original.getSenderAmbient(), restored.getSenderAmbient());
		assertEquals(original.getRecipientAmbient(), restored.getRecipientAmbient());
		assertEquals(original.getPassMessageTo(), restored.getPassMessageTo());
		assertEquals(original.getRespondToMessage(), restored.getRespondToMessage());
		assertEquals(original.getMessage(), restored.getMessage());
	}

	// --- convertToCsvAmbientStatisticsBean ---

	@Test
	public void testConvertToCsvAmbientStatisticsBean_allFieldsMapped() {
		AmbientStatistics stats = new AmbientStatistics(100, 60, 40, 80, 20);

		CsvAmbientStatisticsBean bean = CsvUtil.convertToCsvAmbientStatisticsBean(stats);

		assertEquals(100, bean.getTotalCount());
		assertEquals(60, bean.getStaticCount());
		assertEquals(40, bean.getNonStaticCount());
		assertEquals(80, bean.getActiveCount());
		assertEquals(20, bean.getInactiveCount());
	}

	@Test
	public void testConvertToCsvAmbientStatisticsBean_zeroCounts() {
		AmbientStatistics stats = new AmbientStatistics(0, 0, 0, 0, 0);

		CsvAmbientStatisticsBean bean = CsvUtil.convertToCsvAmbientStatisticsBean(stats);

		assertEquals(0, bean.getTotalCount());
		assertEquals(0, bean.getStaticCount());
		assertEquals(0, bean.getNonStaticCount());
		assertEquals(0, bean.getActiveCount());
		assertEquals(0, bean.getInactiveCount());
	}

	// --- convertToCsvMessageStatisticsBean ---

	@Test
	public void testConvertToCsvMessageStatisticsBean_allFieldsMapped() {
		MessageStatistics stats = new MessageStatistics("Hospital", 15, 8);

		CsvMessageStatisticsBean bean = CsvUtil.convertToCsvMessageStatisticsBean(stats);

		assertEquals("Hospital", bean.getAmbientName());
		assertEquals(15, bean.getSentMessages());
		assertEquals(8, bean.getReceivedMessages());
	}

	@Test
	public void testConvertToCsvMessageStatisticsBean_zeroCounts() {
		MessageStatistics stats = new MessageStatistics("Empty", 0, 0);

		CsvMessageStatisticsBean bean = CsvUtil.convertToCsvMessageStatisticsBean(stats);

		assertEquals("Empty", bean.getAmbientName());
		assertEquals(0, bean.getSentMessages());
		assertEquals(0, bean.getReceivedMessages());
	}

	// --- createFileIfDoesNotExist ---

	@Test
	public void testCreateFileIfDoesNotExist_createsNewFile() {
		String filePath = new File(tempFolder.getRoot(), "new_test.csv").getAbsolutePath();
		assertFalse(new File(filePath).exists());

		CsvUtil.createFileIfDoesNotExist(filePath);

		assertTrue(new File(filePath).exists());
	}

	@Test
	public void testCreateFileIfDoesNotExist_existingFileNotOverwritten() throws IOException {
		File existingFile = tempFolder.newFile("existing.csv");
		assertTrue(existingFile.exists());

		CsvUtil.createFileIfDoesNotExist(existingFile.getAbsolutePath());

		assertTrue(existingFile.exists());
	}

	// --- isFileCreated ---

	@Test
	public void testIsFileCreated_existingFile() throws IOException {
		File file = tempFolder.newFile("check.csv");

		assertTrue(CsvUtil.isFileCreated(file.getAbsolutePath()));
	}

	@Test
	public void testIsFileCreated_nonExistingFile() {
		String fakePath = new File(tempFolder.getRoot(), "nonexistent.csv").getAbsolutePath();

		assertFalse(CsvUtil.isFileCreated(fakePath));
	}

	// --- helpers ---

	private Ambient createTestAmbient() {
		Ambient ambient = new Ambient();
		ambient.setName("TestAmbient");
		ambient.setLocation("Sofia");
		ambient.setLatitude("42.6977");
		ambient.setLongitude("23.3219");
		ambient.setStaticAmbient(true);
		ambient.setActiveAmbient(true);
		ambient.setParentAmbient("Country");
		ambient.setAmbientType(AmbientType.CITY);
		return ambient;
	}

	private Message createTestMessage() {
		Message message = new Message();
		message.setSenderAmbient("Drone1");
		message.setRecipientAmbient("OperationsCenter");
		message.setPassMessageTo("HQ");
		message.setRespondToMessage("Acknowledged");
		message.setMessage("Scan complete");
		return message;
	}
}

package com.university.cca.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.university.cca.entities.Message;
import com.university.cca.entities.MessageStatistics;

public class MessageStatisticsRepositoryTest {

	// --- calculateStatistics ---

	@Test
	public void testCalculateStatistics_emptyList() {
		Map<String, MessageStatistics> stats = MessageStatisticsRepository.calculateStatistics(
			Collections.emptyList()
		);

		assertNotNull(stats);
		assertTrue(stats.isEmpty());
	}

	@Test
	public void testCalculateStatistics_singleMessage() {
		Message msg = new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine");

		Map<String, MessageStatistics> stats = MessageStatisticsRepository.calculateStatistics(
			Collections.singletonList(msg)
		);

		assertEquals(2, stats.size());

		MessageStatistics senderStats = stats.get("hospital1");
		assertNotNull(senderStats);
		assertEquals("Hospital1", senderStats.getAmbientName());
		assertEquals(1, senderStats.getSentCount());
		assertEquals(0, senderStats.getReceivedCount());

		MessageStatistics recipientStats = stats.get("pharmacy1");
		assertNotNull(recipientStats);
		assertEquals("Pharmacy1", recipientStats.getAmbientName());
		assertEquals(0, recipientStats.getSentCount());
		assertEquals(1, recipientStats.getReceivedCount());
	}

	@Test
	public void testCalculateStatistics_twoMessagesFromSameSender() {
		List<Message> messages = Arrays.asList(
			new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine"),
			new Message("Hospital1", "School1", "null", "null", "request_checkup")
		);

		Map<String, MessageStatistics> stats = MessageStatisticsRepository.calculateStatistics(messages);

		MessageStatistics hospitalStats = stats.get("hospital1");
		assertEquals(2, hospitalStats.getSentCount());
		assertEquals(0, hospitalStats.getReceivedCount());
	}

	@Test
	public void testCalculateStatistics_bidirectionalMessages() {
		List<Message> messages = Arrays.asList(
			new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine"),
			new Message("Pharmacy1", "Hospital1", "null", "request_medicine", "medicine_ready")
		);

		Map<String, MessageStatistics> stats = MessageStatisticsRepository.calculateStatistics(messages);

		MessageStatistics hospitalStats = stats.get("hospital1");
		assertEquals(1, hospitalStats.getSentCount());
		assertEquals(1, hospitalStats.getReceivedCount());

		MessageStatistics pharmacyStats = stats.get("pharmacy1");
		assertEquals(1, pharmacyStats.getSentCount());
		assertEquals(1, pharmacyStats.getReceivedCount());
	}

	@Test
	public void testCalculateStatistics_caseInsensitiveKeys() {
		List<Message> messages = Arrays.asList(
			new Message("Hospital1", "Pharmacy1", "null", "null", "msg1"),
			new Message("hospital1", "pharmacy1", "null", "null", "msg2")
		);

		Map<String, MessageStatistics> stats = MessageStatisticsRepository.calculateStatistics(messages);

		MessageStatistics hospitalStats = stats.get("hospital1");
		assertNotNull(hospitalStats);
		assertEquals(2, hospitalStats.getSentCount());
	}

	@Test
	public void testCalculateStatistics_multipleDistinctAmbients() {
		List<Message> messages = Arrays.asList(
			new Message("A", "B", "null", "null", "msg1"),
			new Message("C", "D", "null", "null", "msg2"),
			new Message("E", "F", "null", "null", "msg3")
		);

		Map<String, MessageStatistics> stats = MessageStatisticsRepository.calculateStatistics(messages);

		assertEquals(6, stats.size());
		assertEquals(1, stats.get("a").getSentCount());
		assertEquals(0, stats.get("a").getReceivedCount());
		assertEquals(0, stats.get("b").getSentCount());
		assertEquals(1, stats.get("b").getReceivedCount());
	}

	@Test
	public void testCalculateStatistics_ambientIsBothSenderAndRecipient() {
		List<Message> messages = Arrays.asList(
			new Message("Hub", "Sensor1", "null", "null", "poll"),
			new Message("Sensor1", "Hub", "null", "poll", "data"),
			new Message("Hub", "Sensor2", "null", "null", "poll"),
			new Message("Sensor2", "Hub", "null", "poll", "data")
		);

		Map<String, MessageStatistics> stats = MessageStatisticsRepository.calculateStatistics(messages);

		MessageStatistics hubStats = stats.get("hub");
		assertEquals(2, hubStats.getSentCount());
		assertEquals(2, hubStats.getReceivedCount());
	}

	@Test
	public void testCalculateStatistics_singleAmbientSendsMultiple() {
		List<Message> messages = Arrays.asList(
			new Message("Drone", "HQ", "null", "null", "scan1"),
			new Message("Drone", "HQ", "null", "null", "scan2"),
			new Message("Drone", "HQ", "null", "null", "scan3")
		);

		Map<String, MessageStatistics> stats = MessageStatisticsRepository.calculateStatistics(messages);

		assertEquals(3, stats.get("drone").getSentCount());
		assertEquals(0, stats.get("drone").getReceivedCount());
		assertEquals(0, stats.get("hq").getSentCount());
		assertEquals(3, stats.get("hq").getReceivedCount());
	}
}

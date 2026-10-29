package com.university.cca.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.university.cca.entities.Message;
import com.university.cca.exceptions.AmbientMessageNotFoundException;

public class CCAGeneratorV1Test {

	// --- generateSendReceiveOneAmbient ---

	@Test
	public void testGenerateSendReceiveOneAmbient_basicScenario() throws AmbientMessageNotFoundException {
		Message sent = new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine");
		Message response = new Message("Pharmacy1", "Hospital1", "null", "request_medicine", "medicine_ready");
		List<Message> messages = Arrays.asList(sent, response);

		String result = CCAGeneratorV1.generateSendReceiveOneAmbient(messages, sent);

		assertNotNull(result);
		assertTrue(result.contains("Hospital1"), "Should contain sender ambient");
		assertTrue(result.contains("Pharmacy1::send(request_medicine)"), "Should contain send with message");
		assertTrue(result.contains("Pharmacy1::recv(request_medicine,medicine_ready)"), "Should contain recv with response");
	}

	@Test
	public void testGenerateSendReceiveOneAmbient_specialCharsReplaced() throws AmbientMessageNotFoundException {
		Message sent = new Message("Bus Station", "Coffee Shop", "null", "null", "do you have coffee");
		Message response = new Message("Coffee Shop", "Bus Station", "null", "do you have coffee", "yes we do");
		List<Message> messages = Arrays.asList(sent, response);

		String result = CCAGeneratorV1.generateSendReceiveOneAmbient(messages, sent);

		assertNotNull(result);
		assertTrue(result.contains("Bus_Station"), "Sender spaces should become underscores");
		assertTrue(result.contains("Coffee_Shop"), "Recipient spaces should become underscores");
		assertTrue(result.contains("send(do_you_have_coffee)"), "Send params should have underscores");
		assertTrue(result.contains("recv(do_you_have_coffee,yes_we_do)"), "Recv params should have underscores");
	}

	@Test
	public void testGenerateSendReceiveOneAmbient_templateStructure() throws AmbientMessageNotFoundException {
		Message sent = new Message("A", "B", "null", "null", "hello");
		Message response = new Message("B", "A", "null", "hello", "world");
		List<Message> messages = Arrays.asList(sent, response);

		String result = CCAGeneratorV1.generateSendReceiveOneAmbient(messages, sent);

		assertTrue(result.contains("["), "Should have ambient block opening bracket");
		assertTrue(result.contains("]"), "Should have ambient block closing bracket");
		assertTrue(result.contains("::send("), "Should contain sibling send syntax");
		assertTrue(result.contains("::recv("), "Should contain sibling recv syntax");
		assertTrue(result.contains(".0"), "Should end processes with .0");
	}

	@Test
	public void testGenerateSendReceiveOneAmbient_recipientNotFound() {
		Message sent = new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine");
		List<Message> messages = Collections.singletonList(sent);

		assertThrows(AmbientMessageNotFoundException.class, () ->
			CCAGeneratorV1.generateSendReceiveOneAmbient(messages, sent)
		);
	}

	@Test
	public void testGenerateSendReceiveOneAmbient_emptyMessageList() {
		Message sent = new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine");

		assertThrows(AmbientMessageNotFoundException.class, () ->
			CCAGeneratorV1.generateSendReceiveOneAmbient(Collections.emptyList(), sent)
		);
	}

	@Test
	public void testGenerateSendReceiveOneAmbient_multipleResponsesPicksCorrect() throws AmbientMessageNotFoundException {
		Message sent = new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine");
		Message wrongResponse = new Message("Pharmacy1", "Hospital1", "null", "wrong_topic", "irrelevant");
		Message correctResponse = new Message("Pharmacy1", "Hospital1", "null", "request_medicine", "medicine_ready");
		List<Message> messages = Arrays.asList(sent, wrongResponse, correctResponse);

		String result = CCAGeneratorV1.generateSendReceiveOneAmbient(messages, sent);

		assertTrue(result.contains("medicine_ready"), "Should use the correct response");
	}

	// --- generateReceiveSendOneAmbient ---

	@Test
	public void testGenerateReceiveSendOneAmbient_basicScenario() {
		Message response = new Message("Pharmacy1", "Hospital1", "null", "request_medicine", "medicine_ready");

		String result = CCAGeneratorV1.generateReceiveSendOneAmbient(response);

		assertNotNull(result);
		assertTrue(result.contains("Pharmacy1"), "Should contain sender ambient");
		assertTrue(result.contains("Hospital1::recv(request_medicine)"), "Should contain recv with respondTo");
		assertTrue(result.contains("Hospital1::send(request_medicine,medicine_ready)"), "Should contain send with respondTo");
	}

	@Test
	public void testGenerateReceiveSendOneAmbient_specialCharsReplaced() {
		Message response = new Message("Coffee Shop", "Bus Station", "null", "want coffee", "here it is");

		String result = CCAGeneratorV1.generateReceiveSendOneAmbient(response);

		assertTrue(result.contains("Coffee_Shop"), "Sender spaces should become underscores");
		assertTrue(result.contains("Bus_Station"), "Recipient spaces should become underscores");
		assertTrue(result.contains("recv(want_coffee)"), "Recv params should have underscores");
		assertTrue(result.contains("send(want_coffee,here_it_is)"), "Send params should have underscores");
	}

	@Test
	public void testGenerateReceiveSendOneAmbient_templateStructure() {
		Message response = new Message("B", "A", "null", "hello", "world");

		String result = CCAGeneratorV1.generateReceiveSendOneAmbient(response);

		assertTrue(result.contains("["), "Should have ambient block opening bracket");
		assertTrue(result.contains("]"), "Should have ambient block closing bracket");
		assertTrue(result.contains("::recv("), "Should contain sibling recv syntax");
		assertTrue(result.contains("::send("), "Should contain sibling send syntax");
		assertTrue(result.contains(".0"), "Should end process with .0");
	}

	@Test
	public void testGenerateReceiveSendOneAmbient_longAmbientNames() {
		Message response = new Message(
			"UniversityOfPlovdivLibrary", "CityPharmacyCenter",
			"null", "book_request", "book_available"
		);

		String result = CCAGeneratorV1.generateReceiveSendOneAmbient(response);

		assertTrue(result.contains("UniversityOfPlovdivLibrary"), "Should contain full sender name");
		assertTrue(result.contains("CityPharmacyCenter"), "Should contain full recipient name");
	}

	// --- V1 output format validation ---

	@Test
	public void testSendReceiveProducesValidCCASyntax() throws AmbientMessageNotFoundException {
		Message sent = new Message("Sensor1", "Gateway1", "null", "null", "temperature_25");
		Message response = new Message("Gateway1", "Sensor1", "null", "temperature_25", "ack");
		List<Message> messages = Arrays.asList(sent, response);

		String result = CCAGeneratorV1.generateSendReceiveOneAmbient(messages, sent);

		assertTrue(result.contains("Gateway1::send(temperature_25).0"), "Send should target sibling");
		assertTrue(result.contains("Gateway1::recv(temperature_25,ack).0"), "Recv should target sibling");
	}

	@Test
	public void testReceiveSendProducesValidCCASyntax() {
		Message response = new Message("Gateway1", "Sensor1", "null", "temperature_25", "ack");

		String result = CCAGeneratorV1.generateReceiveSendOneAmbient(response);

		assertTrue(result.contains("Sensor1::recv(temperature_25)."), "Recv should use respondTo as param");
		assertTrue(result.contains("Sensor1::send(temperature_25,ack).0"), "Send should use CSV format params");
	}
}

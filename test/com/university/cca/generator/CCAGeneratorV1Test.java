package com.university.cca.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

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
		assertTrue("Should contain sender ambient", result.contains("Hospital1"));
		assertTrue("Should contain send with message", result.contains("Pharmacy1::send(request_medicine)"));
		assertTrue("Should contain recv with response", result.contains("Pharmacy1::recv(request_medicine,medicine_ready)"));
	}

	@Test
	public void testGenerateSendReceiveOneAmbient_specialCharsReplaced() throws AmbientMessageNotFoundException {
		Message sent = new Message("Bus Station", "Coffee Shop", "null", "null", "do you have coffee");
		Message response = new Message("Coffee Shop", "Bus Station", "null", "do you have coffee", "yes we do");
		List<Message> messages = Arrays.asList(sent, response);

		String result = CCAGeneratorV1.generateSendReceiveOneAmbient(messages, sent);

		assertNotNull(result);
		assertTrue("Sender spaces should become underscores", result.contains("Bus_Station"));
		assertTrue("Recipient spaces should become underscores", result.contains("Coffee_Shop"));
		assertTrue("Send params should have underscores", result.contains("send(do_you_have_coffee)"));
		assertTrue("Recv params should have underscores", result.contains("recv(do_you_have_coffee,yes_we_do)"));
	}

	@Test
	public void testGenerateSendReceiveOneAmbient_templateStructure() throws AmbientMessageNotFoundException {
		Message sent = new Message("A", "B", "null", "null", "hello");
		Message response = new Message("B", "A", "null", "hello", "world");
		List<Message> messages = Arrays.asList(sent, response);

		String result = CCAGeneratorV1.generateSendReceiveOneAmbient(messages, sent);

		assertTrue("Should have ambient block opening bracket", result.contains("["));
		assertTrue("Should have ambient block closing bracket", result.contains("]"));
		assertTrue("Should contain sibling send syntax", result.contains("::send("));
		assertTrue("Should contain sibling recv syntax", result.contains("::recv("));
		assertTrue("Should end processes with .0", result.contains(".0"));
	}

	@Test(expected = AmbientMessageNotFoundException.class)
	public void testGenerateSendReceiveOneAmbient_recipientNotFound() throws AmbientMessageNotFoundException {
		Message sent = new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine");
		List<Message> messages = Collections.singletonList(sent);

		CCAGeneratorV1.generateSendReceiveOneAmbient(messages, sent);
	}

	@Test(expected = AmbientMessageNotFoundException.class)
	public void testGenerateSendReceiveOneAmbient_emptyMessageList() throws AmbientMessageNotFoundException {
		Message sent = new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine");

		CCAGeneratorV1.generateSendReceiveOneAmbient(Collections.emptyList(), sent);
	}

	@Test
	public void testGenerateSendReceiveOneAmbient_multipleResponsesPicksCorrect() throws AmbientMessageNotFoundException {
		Message sent = new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine");
		Message wrongResponse = new Message("Pharmacy1", "Hospital1", "null", "wrong_topic", "irrelevant");
		Message correctResponse = new Message("Pharmacy1", "Hospital1", "null", "request_medicine", "medicine_ready");
		List<Message> messages = Arrays.asList(sent, wrongResponse, correctResponse);

		String result = CCAGeneratorV1.generateSendReceiveOneAmbient(messages, sent);

		assertTrue("Should use the correct response", result.contains("medicine_ready"));
	}

	// --- generateReceiveSendOneAmbient ---

	@Test
	public void testGenerateReceiveSendOneAmbient_basicScenario() {
		Message response = new Message("Pharmacy1", "Hospital1", "null", "request_medicine", "medicine_ready");

		String result = CCAGeneratorV1.generateReceiveSendOneAmbient(response);

		assertNotNull(result);
		assertTrue("Should contain sender ambient", result.contains("Pharmacy1"));
		assertTrue("Should contain recv with respondTo", result.contains("Hospital1::recv(request_medicine)"));
		assertTrue("Should contain send with respondTo", result.contains("Hospital1::send(request_medicine,medicine_ready)"));
	}

	@Test
	public void testGenerateReceiveSendOneAmbient_specialCharsReplaced() {
		Message response = new Message("Coffee Shop", "Bus Station", "null", "want coffee", "here it is");

		String result = CCAGeneratorV1.generateReceiveSendOneAmbient(response);

		assertTrue("Sender spaces should become underscores", result.contains("Coffee_Shop"));
		assertTrue("Recipient spaces should become underscores", result.contains("Bus_Station"));
		assertTrue("Recv params should have underscores", result.contains("recv(want_coffee)"));
		assertTrue("Send params should have underscores", result.contains("send(want_coffee,here_it_is)"));
	}

	@Test
	public void testGenerateReceiveSendOneAmbient_templateStructure() {
		Message response = new Message("B", "A", "null", "hello", "world");

		String result = CCAGeneratorV1.generateReceiveSendOneAmbient(response);

		assertTrue("Should have ambient block opening bracket", result.contains("["));
		assertTrue("Should have ambient block closing bracket", result.contains("]"));
		assertTrue("Should contain sibling recv syntax", result.contains("::recv("));
		assertTrue("Should contain sibling send syntax", result.contains("::send("));
		assertTrue("Should end process with .0", result.contains(".0"));
	}

	@Test
	public void testGenerateReceiveSendOneAmbient_longAmbientNames() {
		Message response = new Message(
			"UniversityOfPlovdivLibrary", "CityPharmacyCenter",
			"null", "book_request", "book_available"
		);

		String result = CCAGeneratorV1.generateReceiveSendOneAmbient(response);

		assertTrue("Should contain full sender name", result.contains("UniversityOfPlovdivLibrary"));
		assertTrue("Should contain full recipient name", result.contains("CityPharmacyCenter"));
	}

	// --- V1 output format validation ---

	@Test
	public void testSendReceiveProducesValidCCASyntax() throws AmbientMessageNotFoundException {
		Message sent = new Message("Sensor1", "Gateway1", "null", "null", "temperature_25");
		Message response = new Message("Gateway1", "Sensor1", "null", "temperature_25", "ack");
		List<Message> messages = Arrays.asList(sent, response);

		String result = CCAGeneratorV1.generateSendReceiveOneAmbient(messages, sent);

		assertTrue("Send should target sibling", result.contains("Gateway1::send(temperature_25).0"));
		assertTrue("Recv should target sibling", result.contains("Gateway1::recv(temperature_25,ack).0"));
	}

	@Test
	public void testReceiveSendProducesValidCCASyntax() {
		Message response = new Message("Gateway1", "Sensor1", "null", "temperature_25", "ack");

		String result = CCAGeneratorV1.generateReceiveSendOneAmbient(response);

		assertTrue("Recv should use respondTo as param", result.contains("Sensor1::recv(temperature_25)."));
		assertTrue("Send should use CSV format params", result.contains("Sensor1::send(temperature_25,ack).0"));
	}
}

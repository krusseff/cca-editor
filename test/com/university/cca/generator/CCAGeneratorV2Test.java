package com.university.cca.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.university.cca.entities.Message;
import com.university.cca.exceptions.AmbientMessageNotFoundException;
import com.university.cca.files.cca.TemplateCCAReader;

public class CCAGeneratorV2Test {

	// --- processAmbientMessages: sender path ---

	@Test
	public void testProcessAmbientMessages_senderGeneratesSendAndReceive() throws AmbientMessageNotFoundException {
		Message sent = new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine");
		Message response = new Message("Pharmacy1", "Hospital1", "null", "request_medicine", "medicine_ready");
		List<Message> messages = Arrays.asList(sent, response);
		String delimiter = TemplateCCAReader.readAmbientDelimiterTemplate();

		String result = CCAGeneratorV2.processAmbientMessages("Hospital1", messages, messages, delimiter);

		assertNotNull(result);
		assertTrue("Should contain send to sibling", result.contains("Pharmacy1::send(request_medicine)"));
		assertTrue("Should contain recv from sibling", result.contains("Pharmacy1::recv(request_medicine,medicine_ready)"));
	}

	@Test
	public void testProcessAmbientMessages_senderSpecialCharsReplaced() throws AmbientMessageNotFoundException {
		Message sent = new Message("Bus Station", "Coffee Shop", "null", "null", "want coffee");
		Message response = new Message("Coffee Shop", "Bus Station", "null", "want coffee", "here it is");
		List<Message> messages = Arrays.asList(sent, response);
		String delimiter = TemplateCCAReader.readAmbientDelimiterTemplate();

		String result = CCAGeneratorV2.processAmbientMessages("Bus Station", messages, messages, delimiter);

		assertTrue("Should have underscored recipient", result.contains("Coffee_Shop::send(want_coffee)"));
		assertTrue("Should have underscored recv params", result.contains("Coffee_Shop::recv(want_coffee,here_it_is)"));
	}

	// --- processAmbientMessages: recipient path ---

	@Test
	public void testProcessAmbientMessages_recipientGeneratesReceiveAndSend() throws AmbientMessageNotFoundException {
		Message response = new Message("Pharmacy1", "Hospital1", "null", "request_medicine", "medicine_ready");
		List<Message> messages = Collections.singletonList(response);
		String delimiter = TemplateCCAReader.readAmbientDelimiterTemplate();

		String result = CCAGeneratorV2.processAmbientMessages("Pharmacy1", messages, messages, delimiter);

		assertNotNull(result);
		assertTrue("Should contain recv from sibling", result.contains("Hospital1::recv(request_medicine)."));
		assertTrue("Should contain send to sibling", result.contains("Hospital1::send(request_medicine,medicine_ready).0"));
	}

	@Test
	public void testProcessAmbientMessages_recipientSpecialCharsReplaced() throws AmbientMessageNotFoundException {
		Message response = new Message("Coffee Shop", "Bus Station", "null", "want coffee", "here it is");
		List<Message> messages = Collections.singletonList(response);
		String delimiter = TemplateCCAReader.readAmbientDelimiterTemplate();

		String result = CCAGeneratorV2.processAmbientMessages("Coffee Shop", messages, messages, delimiter);

		assertTrue("Should have underscored recv", result.contains("Bus_Station::recv(want_coffee)."));
		assertTrue("Should have underscored send", result.contains("Bus_Station::send(want_coffee,here_it_is).0"));
	}

	// --- processAmbientMessages: mixed sender + recipient ---

	@Test
	public void testProcessAmbientMessages_mixedSenderAndRecipient() throws AmbientMessageNotFoundException {
		Message sentByHospital = new Message("Hospital1", "Pharmacy1", "null", "null", "need_medicine");
		Message responseToHospital = new Message("Pharmacy1", "Hospital1", "null", "need_medicine", "medicine_sent");
		Message sentBySchool = new Message("School1", "Hospital1", "null", "null", "checkup_request");
		Message responseFromHospital = new Message("Hospital1", "School1", "null", "checkup_request", "checkup_done");

		List<Message> hospitalMessages = Arrays.asList(sentByHospital, responseFromHospital);
		List<Message> allMessages = Arrays.asList(sentByHospital, responseToHospital, sentBySchool, responseFromHospital);
		String delimiter = TemplateCCAReader.readAmbientDelimiterTemplate();

		String result = CCAGeneratorV2.processAmbientMessages("Hospital1", hospitalMessages, allMessages, delimiter);

		assertNotNull(result);
		assertTrue("Should have send for sender path", result.contains("Pharmacy1::send(need_medicine)"));
		assertTrue("Should have recv for sender path", result.contains("Pharmacy1::recv(need_medicine,medicine_sent)"));
		assertTrue("Should have recv for recipient path", result.contains("School1::recv(checkup_request)."));
		assertTrue("Should have send for recipient path", result.contains("School1::send(checkup_request,checkup_done).0"));
	}

	// --- processAmbientMessages: empty and edge cases ---

	@Test
	public void testProcessAmbientMessages_emptyMessageList() throws AmbientMessageNotFoundException {
		String delimiter = TemplateCCAReader.readAmbientDelimiterTemplate();

		String result = CCAGeneratorV2.processAmbientMessages("Hospital1", Collections.emptyList(), Collections.emptyList(), delimiter);

		assertNotNull(result);
		assertEquals("Empty messages should produce empty result", "", result.trim());
	}

	@Test(expected = AmbientMessageNotFoundException.class)
	public void testProcessAmbientMessages_missingRecipientThrowsException() throws AmbientMessageNotFoundException {
		Message sent = new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine");
		List<Message> messages = Collections.singletonList(sent);
		String delimiter = TemplateCCAReader.readAmbientDelimiterTemplate();

		CCAGeneratorV2.processAmbientMessages("Hospital1", messages, messages, delimiter);
	}

	@Test
	public void testProcessAmbientMessages_passMessageToIsLogged() throws AmbientMessageNotFoundException {
		Message passMsg = new Message("Hospital1", "Pharmacy1", "School1", "null", "forward_request");
		List<Message> messages = Collections.singletonList(passMsg);
		String delimiter = TemplateCCAReader.readAmbientDelimiterTemplate();

		String result = CCAGeneratorV2.processAmbientMessages("Hospital1", messages, messages, delimiter);

		assertNotNull(result);
	}

	// --- processAmbientMessages: multiple senders ---

	@Test
	public void testProcessAmbientMessages_multipleSendMessages() throws AmbientMessageNotFoundException {
		Message sent1 = new Message("Hospital1", "Pharmacy1", "null", "null", "need_medicine");
		Message response1 = new Message("Pharmacy1", "Hospital1", "null", "need_medicine", "medicine_ready");
		Message sent2 = new Message("Hospital1", "School1", "null", "null", "send_report");
		Message response2 = new Message("School1", "Hospital1", "null", "send_report", "report_received");

		List<Message> hospitalMessages = Arrays.asList(sent1, sent2);
		List<Message> allMessages = Arrays.asList(sent1, response1, sent2, response2);
		String delimiter = TemplateCCAReader.readAmbientDelimiterTemplate();

		String result = CCAGeneratorV2.processAmbientMessages("Hospital1", hospitalMessages, allMessages, delimiter);

		assertTrue("Should have send to Pharmacy1", result.contains("Pharmacy1::send(need_medicine)"));
		assertTrue("Should have recv from Pharmacy1", result.contains("Pharmacy1::recv(need_medicine,medicine_ready)"));
		assertTrue("Should have send to School1", result.contains("School1::send(send_report)"));
		assertTrue("Should have recv from School1", result.contains("School1::recv(send_report,report_received)"));
	}

	// --- processAmbientMessages: output structure ---

	@Test
	public void testProcessAmbientMessages_outputContainsOnlySiblingCommunication() throws AmbientMessageNotFoundException {
		Message sent = new Message("A", "B", "null", "null", "msg1");
		Message response = new Message("B", "A", "null", "msg1", "msg2");
		List<Message> messages = Arrays.asList(sent, response);
		String delimiter = TemplateCCAReader.readAmbientDelimiterTemplate();

		String result = CCAGeneratorV2.processAmbientMessages("A", messages, messages, delimiter);

		assertTrue("Should use sibling communication (::)", result.contains("::send("));
		assertTrue("Should use sibling communication (::)", result.contains("::recv("));
		assertTrue("Should end processes with .0", result.contains(".0"));
		assertFalse("Should not contain parent comm (@)", result.contains("@send("));
		assertFalse("Should not contain child comm (#)", result.contains("#send("));
	}

	@Test
	public void testProcessAmbientMessages_outputDoesNotEndWithDelimiter() throws AmbientMessageNotFoundException {
		Message sent = new Message("Hospital1", "Pharmacy1", "null", "null", "request");
		Message response = new Message("Pharmacy1", "Hospital1", "null", "request", "response");
		List<Message> messages = Arrays.asList(sent, response);
		String delimiter = TemplateCCAReader.readAmbientDelimiterTemplate();

		String result = CCAGeneratorV2.processAmbientMessages("Hospital1", messages, messages, delimiter);

		String trimmed = result.trim();
		assertFalse("Result should not end with delimiter after removeLastChar",
			trimmed.endsWith(delimiter.trim()));
	}
}

package com.university.cca.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.university.cca.entities.Message;

public class MessageServiceTest {

	// --- constructMessage tests ---

	@Test
	public void testConstructMessageReturnsNonNull() {
		Message message = MessageService.constructMessage(
			"Hospital1", "Pharmacy1", "null", "null", "request_medicine"
		);
		
		assertNotNull(message);
	}

	@Test
	public void testConstructMessageSetsAllFields() {
		Message message = MessageService.constructMessage(
			"Hospital1", "Pharmacy1", "null", "null", "request_medicine"
		);
		
		assertEquals("Hospital1", message.getSenderAmbient());
		assertEquals("Pharmacy1", message.getRecipientAmbient());
		assertEquals("null", message.getPassMessageTo());
		assertEquals("null", message.getRespondToMessage());
		assertEquals("request_medicine", message.getMessage());
	}

	@Test
	public void testConstructMessageConvertsObjectsToString() {
		Message message = MessageService.constructMessage(
			Integer.valueOf(1), Integer.valueOf(2),
			Integer.valueOf(3), Integer.valueOf(4), "test_message"
		);
		
		assertEquals("1", message.getSenderAmbient());
		assertEquals("2", message.getRecipientAmbient());
		assertEquals("3", message.getPassMessageTo());
		assertEquals("4", message.getRespondToMessage());
	}

	// --- isValidMessageInfo tests ---

	@Test
	public void testIsValidMessageInfoAllValid() {
		assertTrue(MessageService.isValidMessageInfo(
			"Hospital1", "Pharmacy1", "null", "null", "request_medicine"
		));
	}

	@Test
	public void testIsValidMessageInfoNullSender() {
		assertFalse(MessageService.isValidMessageInfo(
			null, "Pharmacy1", "null", "null", "request_medicine"
		));
	}

	@Test
	public void testIsValidMessageInfoNullRecipient() {
		assertFalse(MessageService.isValidMessageInfo(
			"Hospital1", null, "null", "null", "request_medicine"
		));
	}

	@Test
	public void testIsValidMessageInfoNullMessage() {
		assertFalse(MessageService.isValidMessageInfo(
			"Hospital1", "Pharmacy1", "null", "null", null
		));
	}

	@Test
	public void testIsValidMessageInfoEmptyMessage() {
		assertFalse(MessageService.isValidMessageInfo(
			"Hospital1", "Pharmacy1", "null", "null", ""
		));
	}

	@Test
	public void testIsValidMessageInfoNullPassMessageTo() {
		assertTrue(MessageService.isValidMessageInfo(
			"Hospital1", "Pharmacy1", null, "null", "request_medicine"
		));
	}

	@Test
	public void testIsValidMessageInfoEmptyPassMessageTo() {
		assertFalse(MessageService.isValidMessageInfo(
			"Hospital1", "Pharmacy1", "", "null", "request_medicine"
		));
	}

	@Test
	public void testIsValidMessageInfoNullRespondTo() {
		assertTrue(MessageService.isValidMessageInfo(
			"Hospital1", "Pharmacy1", "null", null, "request_medicine"
		));
	}

	@Test
	public void testIsValidMessageInfoEmptyRespondTo() {
		assertFalse(MessageService.isValidMessageInfo(
			"Hospital1", "Pharmacy1", "null", "", "request_medicine"
		));
	}

	// --- isValidMessageLength tests ---

	@Test
	public void testIsValidMessageLengthValid() {
		assertTrue(MessageService.isValidMessageLength("request_medicine"));
	}

	@Test
	public void testIsValidMessageLengthEmptyString() {
		assertFalse(MessageService.isValidMessageLength(""));
	}

	@Test
	public void testIsValidMessageLengthSingleChar() {
		assertTrue(MessageService.isValidMessageLength("a"));
	}

	@Test
	public void testIsValidMessageLengthExactly500() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 500; i++) {
			sb.append("a");
		}
		
		assertTrue(MessageService.isValidMessageLength(sb.toString()));
	}

	@Test
	public void testIsValidMessageLengthExceeds500() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 501; i++) {
			sb.append("a");
		}
		
		assertFalse(MessageService.isValidMessageLength(sb.toString()));
	}
}

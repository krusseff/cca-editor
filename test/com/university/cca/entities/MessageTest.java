package com.university.cca.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MessageTest {

	@Test
	public void testDefaultConstructor() {
		Message message = new Message();
		
		assertNotNull(message);
		assertNull(message.getSenderAmbient());
		assertNull(message.getRecipientAmbient());
		assertNull(message.getPassMessageTo());
		assertNull(message.getRespondToMessage());
		assertNull(message.getMessage());
	}

	@Test
	public void testParameterizedConstructor() {
		Message message = createTestMessage();
		
		assertEquals("Hospital1", message.getSenderAmbient());
		assertEquals("Pharmacy1", message.getRecipientAmbient());
		assertEquals("null", message.getPassMessageTo());
		assertEquals("null", message.getRespondToMessage());
		assertEquals("request_medicine", message.getMessage());
	}

	@Test
	public void testSetters() {
		Message message = new Message();
		
		message.setSenderAmbient("Bus1");
		message.setRecipientAmbient("BusStation1");
		message.setPassMessageTo("null");
		message.setRespondToMessage("arrived");
		message.setMessage("departing");
		
		assertEquals("Bus1", message.getSenderAmbient());
		assertEquals("BusStation1", message.getRecipientAmbient());
		assertEquals("null", message.getPassMessageTo());
		assertEquals("arrived", message.getRespondToMessage());
		assertEquals("departing", message.getMessage());
	}

	@Test
	public void testEqualsSameObject() {
		Message message = createTestMessage();
		
		assertEquals(message, message);
	}

	@Test
	public void testEqualsIdenticalObjects() {
		Message m1 = createTestMessage();
		Message m2 = createTestMessage();
		
		assertEquals(m1, m2);
		assertEquals(m1.hashCode(), m2.hashCode());
	}

	@Test
	public void testNotEqualsNull() {
		Message message = createTestMessage();
		
		assertNotEquals(message, null);
	}

	@Test
	public void testNotEqualsDifferentClass() {
		Message message = createTestMessage();
		
		assertNotEquals(message, "not a message");
	}

	@Test
	public void testNotEqualsDifferentSender() {
		Message m1 = createTestMessage();
		Message m2 = createTestMessage();
		m2.setSenderAmbient("OtherSender");
		
		assertNotEquals(m1, m2);
	}

	@Test
	public void testNotEqualsDifferentRecipient() {
		Message m1 = createTestMessage();
		Message m2 = createTestMessage();
		m2.setRecipientAmbient("OtherRecipient");
		
		assertNotEquals(m1, m2);
	}

	@Test
	public void testNotEqualsDifferentPassMessageTo() {
		Message m1 = createTestMessage();
		Message m2 = createTestMessage();
		m2.setPassMessageTo("SomeAmbient");
		
		assertNotEquals(m1, m2);
	}

	@Test
	public void testNotEqualsDifferentRespondTo() {
		Message m1 = createTestMessage();
		Message m2 = createTestMessage();
		m2.setRespondToMessage("some_response");
		
		assertNotEquals(m1, m2);
	}

	@Test
	public void testNotEqualsDifferentMessage() {
		Message m1 = createTestMessage();
		Message m2 = createTestMessage();
		m2.setMessage("different_msg");
		
		assertNotEquals(m1, m2);
	}

	@Test
	public void testEqualsWithNullFields() {
		Message m1 = new Message();
		Message m2 = new Message();
		
		assertEquals(m1, m2);
		assertEquals(m1.hashCode(), m2.hashCode());
	}

	@Test
	public void testEqualsOneNullSenderOneNot() {
		Message m1 = new Message();
		Message m2 = new Message();
		m2.setSenderAmbient("Sender");
		
		assertNotEquals(m1, m2);
	}

	@Test
	public void testEqualsOneNullRecipientOneNot() {
		Message m1 = new Message();
		Message m2 = new Message();
		m2.setRecipientAmbient("Recipient");
		
		assertNotEquals(m1, m2);
	}

	@Test
	public void testEqualsOneNullPassMsgToOneNot() {
		Message m1 = new Message();
		Message m2 = new Message();
		m2.setPassMessageTo("Target");
		
		assertNotEquals(m1, m2);
	}

	@Test
	public void testEqualsOneNullRespondToOneNot() {
		Message m1 = new Message();
		Message m2 = new Message();
		m2.setRespondToMessage("Response");
		
		assertNotEquals(m1, m2);
	}

	@Test
	public void testEqualsOneNullMessageOneNot() {
		Message m1 = new Message();
		Message m2 = new Message();
		m2.setMessage("Message");
		
		assertNotEquals(m1, m2);
	}

	@Test
	public void testHashCodeConsistency() {
		Message message = createTestMessage();
		int hash1 = message.hashCode();
		int hash2 = message.hashCode();
		
		assertEquals(hash1, hash2);
	}

	@Test
	public void testToStringContainsFields() {
		Message message = createTestMessage();
		String str = message.toString();
		
		assertTrue(str.contains("Hospital1"));
		assertTrue(str.contains("Pharmacy1"));
		assertTrue(str.contains("request_medicine"));
	}

	@Test
	public void testEqualsSymmetric() {
		Message m1 = createTestMessage();
		Message m2 = createTestMessage();
		
		assertTrue(m1.equals(m2) && m2.equals(m1));
	}

	@Test
	public void testEqualsTransitive() {
		Message m1 = createTestMessage();
		Message m2 = createTestMessage();
		Message m3 = createTestMessage();
		
		assertTrue(m1.equals(m2));
		assertTrue(m2.equals(m3));
		assertTrue(m1.equals(m3));
	}

	@Test
	public void testNotEqualsWithSelfReflexiveFalse() {
		Message message = createTestMessage();
		
		assertFalse(message.equals(null));
	}

	private Message createTestMessage() {
		return new Message(
			"Hospital1", "Pharmacy1", "null", "null", "request_medicine"
		);
	}
}

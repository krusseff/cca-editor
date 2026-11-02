package com.university.cca.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.university.cca.entities.Ambient;
import com.university.cca.entities.Message;
import com.university.cca.enums.AmbientType;
import com.university.cca.enums.CommunicationType;
import com.university.cca.exceptions.AmbientMessageNotFoundException;

public class GeneratorUtilTest {

	// --- replaceUnsupportedChars (String) ---

	@Test
	public void testReplaceUnsupportedCharsAlphanumericUnchanged() {
		assertEquals("Hospital1", GeneratorUtil.replaceUnsupportedChars("Hospital1"));
	}

	@Test
	public void testReplaceUnsupportedCharsSpacesReplaced() {
		assertEquals("Bus_Station_1", GeneratorUtil.replaceUnsupportedChars("Bus Station 1"));
	}

	@Test
	public void testReplaceUnsupportedCharsSpecialCharsReplaced() {
		assertEquals("hello_world_", GeneratorUtil.replaceUnsupportedChars("hello-world!"));
	}

	@Test
	public void testReplaceUnsupportedCharsEmptyString() {
		assertEquals("", GeneratorUtil.replaceUnsupportedChars(""));
	}

	@Test
	public void testReplaceUnsupportedCharsAllSpecialChars() {
		assertEquals("___", GeneratorUtil.replaceUnsupportedChars("!@#"));
	}

	@Test
	public void testReplaceUnsupportedCharsMultipleConsecutiveSpecialChars() {
		assertEquals("a___b", GeneratorUtil.replaceUnsupportedChars("a---b"));
	}

	// --- replaceUnsupportedChars (Message) ---

	@Test
	public void testReplaceUnsupportedCharsMessage() {
		Message message = new Message(
			"Bus Station", "Coffee Shop", "null", "null", "request coffee"
		);

		Message converted = GeneratorUtil.replaceUnsupportedChars(message);

		assertEquals("Bus_Station", converted.getSenderAmbient());
		assertEquals("Coffee_Shop", converted.getRecipientAmbient());
		assertEquals("request_coffee", converted.getMessage());
	}

	@Test
	public void testReplaceUnsupportedCharsMessageAlreadyClean() {
		Message message = new Message(
			"Hospital1", "Pharmacy1", "null", "null", "request_medicine"
		);

		Message converted = GeneratorUtil.replaceUnsupportedChars(message);

		assertEquals("Hospital1", converted.getSenderAmbient());
		assertEquals("Pharmacy1", converted.getRecipientAmbient());
		assertEquals("request_medicine", converted.getMessage());
	}

	// --- toCSVFormat ---

	@Test
	public void testToCSVFormatTwoValues() {
		assertEquals("hello,world", GeneratorUtil.toCSVFormat("hello", "world"));
	}

	@Test
	public void testToCSVFormatEmptyValues() {
		assertEquals(",", GeneratorUtil.toCSVFormat("", ""));
	}

	@Test
	public void testToCSVFormatWithCommaInValues() {
		assertEquals("a,b,c,d", GeneratorUtil.toCSVFormat("a,b", "c,d"));
	}

	// --- removeLastChar ---

	@Test
	public void testRemoveLastCharRemovesMatchingChar() {
		assertEquals("hello | world", GeneratorUtil.removeLastChar("hello | world |", "|"));
	}

	@Test
	public void testRemoveLastCharNoMatch() {
		assertEquals("hello | world", GeneratorUtil.removeLastChar("hello | world", "|"));
	}

	@Test
	public void testRemoveLastCharEmptyString() {
		assertEquals("", GeneratorUtil.removeLastChar("", "|"));
	}

	@Test
	public void testRemoveLastCharNullString() {
		assertEquals(null, GeneratorUtil.removeLastChar(null, "|"));
	}

	@Test
	public void testRemoveLastCharTrimsWhitespace() {
		assertEquals("hello | world", GeneratorUtil.removeLastChar("  hello | world |  ", "|"));
	}

	@Test
	public void testRemoveLastCharSingleCharMatching() {
		assertEquals("", GeneratorUtil.removeLastChar("|", "|"));
	}

	// --- isSender ---

	@Test
	public void testIsSenderMatchesSenderWithNullResponseAndPassMsgTo() {
		assertTrue(GeneratorUtil.isSender("Hospital1", "Hospital1", "null", "null"));
	}

	@Test
	public void testIsSenderDifferentSender() {
		assertFalse(GeneratorUtil.isSender("School1", "Hospital1", "null", "null"));
	}

	@Test
	public void testIsSenderWithNonNullRespondTo() {
		assertFalse(GeneratorUtil.isSender("Hospital1", "Hospital1", "some_response", "null"));
	}

	@Test
	public void testIsSenderWithNonNullPassMsgTo() {
		assertFalse(GeneratorUtil.isSender("Hospital1", "Hospital1", "null", "some_target"));
	}

	@Test
	public void testIsSenderCaseInsensitiveNull() {
		assertTrue(GeneratorUtil.isSender("Hospital1", "Hospital1", "NULL", "NULL"));
	}

	// --- isRecipient ---

	@Test
	public void testIsRecipientMatchesWithRespondTo() {
		assertTrue(GeneratorUtil.isRecipient("Hospital1", "Hospital1", "some_response", "null"));
	}

	@Test
	public void testIsRecipientDifferentSender() {
		assertFalse(GeneratorUtil.isRecipient("School1", "Hospital1", "some_response", "null"));
	}

	@Test
	public void testIsRecipientWithNullRespondTo() {
		assertFalse(GeneratorUtil.isRecipient("Hospital1", "Hospital1", "null", "null"));
	}

	@Test
	public void testIsRecipientWithNonNullPassMsgTo() {
		assertFalse(GeneratorUtil.isRecipient("Hospital1", "Hospital1", "some_response", "some_target"));
	}

	// --- hasPassMsgTo ---

	@Test
	public void testHasPassMsgToTrue() {
		assertTrue(GeneratorUtil.hasPassMsgTo("Hospital1", "Hospital1", "some_target"));
	}

	@Test
	public void testHasPassMsgToNullValue() {
		assertFalse(GeneratorUtil.hasPassMsgTo("Hospital1", "Hospital1", "null"));
	}

	@Test
	public void testHasPassMsgToDifferentSender() {
		assertFalse(GeneratorUtil.hasPassMsgTo("School1", "Hospital1", "some_target"));
	}

	// --- getMessagesByAmbient ---

	@Test
	public void testGetMessagesByAmbientReturnsSenderMessages() {
		List<Message> messages = Arrays.asList(
			new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine"),
			new Message("School1", "Hospital1", "null", "null", "request_checkup"),
			new Message("Bus1", "BusStation1", "null", "null", "arrived")
		);

		List<Message> result = GeneratorUtil.getMessagesByAmbient("Hospital1", messages);

		assertEquals(2, result.size());
	}

	@Test
	public void testGetMessagesByAmbientNoMatch() {
		List<Message> messages = Arrays.asList(
			new Message("Bus1", "BusStation1", "null", "null", "arrived")
		);

		List<Message> result = GeneratorUtil.getMessagesByAmbient("Hospital1", messages);

		assertTrue(result.isEmpty());
	}

	@Test
	public void testGetMessagesByAmbientEmptyList() {
		List<Message> result = GeneratorUtil.getMessagesByAmbient("Hospital1", Collections.emptyList());

		assertTrue(result.isEmpty());
	}

	// --- hasMessages ---

	@Test
	public void testHasMessagesTrue() {
		List<Message> messages = Arrays.asList(
			new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine")
		);

		assertTrue(GeneratorUtil.hasMessages(messages, "Hospital1"));
	}

	@Test
	public void testHasMessagesTrueAsRecipient() {
		List<Message> messages = Arrays.asList(
			new Message("Pharmacy1", "Hospital1", "null", "null", "deliver_medicine")
		);

		assertTrue(GeneratorUtil.hasMessages(messages, "Hospital1"));
	}

	@Test
	public void testHasMessagesFalse() {
		List<Message> messages = Arrays.asList(
			new Message("Bus1", "BusStation1", "null", "null", "arrived")
		);

		assertFalse(GeneratorUtil.hasMessages(messages, "Hospital1"));
	}

	@Test
	public void testHasMessagesEmptyList() {
		assertFalse(GeneratorUtil.hasMessages(Collections.emptyList(), "Hospital1"));
	}

	// --- findMessageRecipient ---

	@Test
	public void testFindMessageRecipientFound() throws AmbientMessageNotFoundException {
		Message sent = new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine");
		Message response = new Message("Pharmacy1", "Hospital1", "null", "request_medicine", "medicine_ready");

		List<Message> messages = Arrays.asList(sent, response);

		Message found = GeneratorUtil.findMessageRecipient(messages, sent);

		assertNotNull(found);
		assertEquals("Pharmacy1", found.getSenderAmbient());
		assertEquals("Hospital1", found.getRecipientAmbient());
		assertEquals("request_medicine", found.getRespondToMessage());
	}

	@Test
	public void testFindMessageRecipientNotFound() {
		Message sent = new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine");

		List<Message> messages = Arrays.asList(sent);

		assertThrows(AmbientMessageNotFoundException.class, () ->
			GeneratorUtil.findMessageRecipient(messages, sent)
		);
	}

	@Test
	public void testFindMessageRecipientEmptyList() {
		Message sent = new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine");

		assertThrows(AmbientMessageNotFoundException.class, () ->
			GeneratorUtil.findMessageRecipient(Collections.emptyList(), sent)
		);
	}

	// --- isLastAmbientMessage ---

	@Test
	public void testIsLastAmbientMessageNotLast() {
		Message m1 = new Message("Hospital1", "Pharmacy1", "null", "null", "msg1");
		Message m2 = new Message("Hospital1", "Pharmacy1", "null", "null", "msg2");
		List<Message> messages = Arrays.asList(m1, m2);

		assertTrue(GeneratorUtil.isLastAmbientMessage(messages, m1));
	}

	@Test
	public void testIsLastAmbientMessageIsLast() {
		Message m1 = new Message("Hospital1", "Pharmacy1", "null", "null", "msg1");
		Message m2 = new Message("Hospital1", "Pharmacy1", "null", "null", "msg2");
		List<Message> messages = Arrays.asList(m1, m2);

		assertFalse(GeneratorUtil.isLastAmbientMessage(messages, m2));
	}

	// --- getMessagesActiveAmbients (with allAmbients) ---

	@Test
	public void testGetMessagesActiveAmbients_allActiveWithParents() {
		List<Ambient> allAmbients = Arrays.asList(
			createAmbient("City1", true, true, "Country1"),
			createAmbient("Hospital1", true, true, "City1"),
			createAmbient("Pharmacy1", true, true, "City1"),
			createAmbient("Country1", true, true, "")
		);

		List<Message> messages = Arrays.asList(
			new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine")
		);

		List<Message> result = GeneratorUtil.getMessagesActiveAmbients(messages, allAmbients);

		assertEquals(1, result.size());
		assertEquals("Hospital1", result.get(0).getSenderAmbient());
	}

	@Test
	public void testGetMessagesActiveAmbients_inactiveSenderFiltered() {
		List<Ambient> allAmbients = Arrays.asList(
			createAmbient("City1", true, true, "Country1"),
			createAmbient("Hospital1", true, false, "City1"),
			createAmbient("Pharmacy1", true, true, "City1"),
			createAmbient("Country1", true, true, "")
		);

		List<Message> messages = Arrays.asList(
			new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine")
		);

		List<Message> result = GeneratorUtil.getMessagesActiveAmbients(messages, allAmbients);

		assertTrue(result.isEmpty());
	}

	@Test
	public void testGetMessagesActiveAmbients_inactiveRecipientFiltered() {
		List<Ambient> allAmbients = Arrays.asList(
			createAmbient("City1", true, true, "Country1"),
			createAmbient("Hospital1", true, true, "City1"),
			createAmbient("Pharmacy1", true, false, "City1"),
			createAmbient("Country1", true, true, "")
		);

		List<Message> messages = Arrays.asList(
			new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine")
		);

		List<Message> result = GeneratorUtil.getMessagesActiveAmbients(messages, allAmbients);

		assertTrue(result.isEmpty());
	}

	@Test
	public void testGetMessagesActiveAmbients_inactiveParentFiltered() {
		List<Ambient> allAmbients = Arrays.asList(
			createAmbient("City1", true, false, "Country1"),
			createAmbient("Hospital1", true, true, "City1"),
			createAmbient("Pharmacy1", true, true, "City1"),
			createAmbient("Country1", true, true, "")
		);

		List<Message> messages = Arrays.asList(
			new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine")
		);

		List<Message> result = GeneratorUtil.getMessagesActiveAmbients(messages, allAmbients);

		assertTrue(result.isEmpty());
	}

	@Test
	public void testGetMessagesActiveAmbients_noParentFiltered() {
		List<Ambient> allAmbients = Arrays.asList(
			createAmbient("Hospital1", true, true, "NonExistentCity"),
			createAmbient("Pharmacy1", true, true, "NonExistentCity")
		);

		List<Message> messages = Arrays.asList(
			new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine")
		);

		List<Message> result = GeneratorUtil.getMessagesActiveAmbients(messages, allAmbients);

		assertTrue(result.isEmpty());
	}

	@Test
	public void testGetMessagesActiveAmbients_emptyMessages() {
		List<Ambient> allAmbients = Arrays.asList(
			createAmbient("Hospital1", true, true, "City1"),
			createAmbient("City1", true, true, "")
		);

		List<Message> result = GeneratorUtil.getMessagesActiveAmbients(
			Collections.emptyList(), allAmbients
		);

		assertTrue(result.isEmpty());
	}

	@Test
	public void testGetMessagesActiveAmbients_mixedActiveInactive() {
		List<Ambient> allAmbients = Arrays.asList(
			createAmbient("Country1", true, true, ""),
			createAmbient("City1", true, true, "Country1"),
			createAmbient("Hospital1", true, true, "City1"),
			createAmbient("Pharmacy1", true, true, "City1"),
			createAmbient("School1", true, false, "City1"),
			createAmbient("Bus1", false, true, "City1")
		);

		List<Message> messages = Arrays.asList(
			new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine"),
			new Message("Hospital1", "School1", "null", "null", "request_checkup"),
			new Message("Bus1", "Hospital1", "null", "null", "patient_arriving")
		);

		List<Message> result = GeneratorUtil.getMessagesActiveAmbients(messages, allAmbients);

		assertEquals(2, result.size());
		assertEquals("Hospital1", result.get(0).getSenderAmbient());
		assertEquals("Pharmacy1", result.get(0).getRecipientAmbient());
		assertEquals("Bus1", result.get(1).getSenderAmbient());
	}

	@Test
	public void testGetMessagesActiveAmbients_differentSenderRecipientParents() {
		List<Ambient> allAmbients = Arrays.asList(
			createAmbient("Country1", true, true, ""),
			createAmbient("CityA", true, true, "Country1"),
			createAmbient("CityB", true, true, "Country1"),
			createAmbient("Hospital1", true, true, "CityA"),
			createAmbient("Pharmacy1", true, true, "CityB")
		);

		List<Message> messages = Arrays.asList(
			new Message("Hospital1", "Pharmacy1", "null", "null", "request_medicine")
		);

		List<Message> result = GeneratorUtil.getMessagesActiveAmbients(messages, allAmbients);

		assertEquals(1, result.size());
	}

	// --- detectCommunicationType (with allAmbients) ---

	@Test
	public void testDetectCommunicationType_sibling() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Hospital", true, true, "City"),
			createAmbient("Pharmacy", true, true, "City"),
			createAmbient("City", true, true, "")
		);

		assertEquals(CommunicationType.SIBLING,
			GeneratorUtil.detectCommunicationType("Hospital", "Pharmacy", ambients));
	}

	@Test
	public void testDetectCommunicationType_parentToChild() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("University", true, true, "City"),
			createAmbient("FMI", true, true, "University"),
			createAmbient("City", true, true, "")
		);

		assertEquals(CommunicationType.PARENT_TO_CHILD,
			GeneratorUtil.detectCommunicationType("University", "FMI", ambients));
	}

	@Test
	public void testDetectCommunicationType_childToParent() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("University", true, true, "City"),
			createAmbient("FMI", true, true, "University"),
			createAmbient("City", true, true, "")
		);

		assertEquals(CommunicationType.CHILD_TO_PARENT,
			GeneratorUtil.detectCommunicationType("FMI", "University", ambients));
	}

	@Test
	public void testDetectCommunicationType_local() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Hospital", true, true, "City")
		);

		assertEquals(CommunicationType.LOCAL,
			GeneratorUtil.detectCommunicationType("Hospital", "Hospital", ambients));
	}

	@Test
	public void testDetectCommunicationType_unsupported_differentParents() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("A", true, true, "CityA"),
			createAmbient("B", true, true, "CityB"),
			createAmbient("CityA", true, true, "Country"),
			createAmbient("CityB", true, true, "Country"),
			createAmbient("Country", true, true, "")
		);

		assertEquals(CommunicationType.UNSUPPORTED,
			GeneratorUtil.detectCommunicationType("A", "B", ambients));
	}

	@Test
	public void testDetectCommunicationType_nullSender() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Hospital", true, true, "City")
		);

		assertEquals(CommunicationType.UNSUPPORTED,
			GeneratorUtil.detectCommunicationType(null, "Hospital", ambients));
	}

	@Test
	public void testDetectCommunicationType_nullRecipient() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Hospital", true, true, "City")
		);

		assertEquals(CommunicationType.UNSUPPORTED,
			GeneratorUtil.detectCommunicationType("Hospital", null, ambients));
	}

	@Test
	public void testDetectCommunicationType_bothNull() {
		assertEquals(CommunicationType.UNSUPPORTED,
			GeneratorUtil.detectCommunicationType(null, null, Collections.emptyList()));
	}

	@Test
	public void testDetectCommunicationType_inverseConsistency() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("University", true, true, "City"),
			createAmbient("FMI", true, true, "University"),
			createAmbient("City", true, true, "")
		);

		CommunicationType uniToFmi = GeneratorUtil.detectCommunicationType("University", "FMI", ambients);
		CommunicationType fmiToUni = GeneratorUtil.detectCommunicationType("FMI", "University", ambients);

		assertEquals(uniToFmi.inverse(), fmiToUni,
			"Swapping sender and recipient should give the inverse communication type");
	}

	// --- isInitiator ---

	@Test
	public void testIsInitiator_senderMatchesRespondToNull() {
		Message msg = new Message("Hospital", "Pharmacy", "null", "null", "request");

		assertTrue(GeneratorUtil.isInitiator("Hospital", msg));
	}

	@Test
	public void testIsInitiator_senderMatchesRespondToNotNull() {
		Message msg = new Message("Hospital", "Pharmacy", "null", "request", "response");

		assertFalse(GeneratorUtil.isInitiator("Hospital", msg));
	}

	@Test
	public void testIsInitiator_senderDoesNotMatch() {
		Message msg = new Message("Hospital", "Pharmacy", "null", "null", "request");

		assertFalse(GeneratorUtil.isInitiator("Pharmacy", msg));
	}

	@Test
	public void testIsInitiator_ignoresPassMsgTo() {
		Message msg = new Message("Hospital", "Pharmacy", "School", "null", "request");

		assertTrue(GeneratorUtil.isInitiator("Hospital", msg),
			"isInitiator should not check passMessageTo");
	}

	@Test
	public void testIsInitiator_caseInsensitiveNull() {
		Message msg = new Message("Hospital", "Pharmacy", "null", "NULL", "request");

		assertTrue(GeneratorUtil.isInitiator("Hospital", msg));
	}

	// --- isResponder ---

	@Test
	public void testIsResponder_senderMatchesRespondToNotNull() {
		Message msg = new Message("Pharmacy", "Hospital", "null", "request", "response");

		assertTrue(GeneratorUtil.isResponder("Pharmacy", msg));
	}

	@Test
	public void testIsResponder_senderMatchesRespondToNull() {
		Message msg = new Message("Pharmacy", "Hospital", "null", "null", "request");

		assertFalse(GeneratorUtil.isResponder("Pharmacy", msg));
	}

	@Test
	public void testIsResponder_senderDoesNotMatch() {
		Message msg = new Message("Pharmacy", "Hospital", "null", "request", "response");

		assertFalse(GeneratorUtil.isResponder("Hospital", msg));
	}

	@Test
	public void testIsResponder_caseInsensitiveNull() {
		Message msg = new Message("Pharmacy", "Hospital", "null", "NULL", "response");

		assertFalse(GeneratorUtil.isResponder("Pharmacy", msg),
			"NULL (uppercase) should be treated as null/no respondTo");
	}

	// --- isForwarder ---

	@Test
	public void testIsForwarder_senderMatchesPassMsgToNotNull() {
		Message msg = new Message("B", "C", "A", "null", "forwarded");

		assertTrue(GeneratorUtil.isForwarder("B", msg));
	}

	@Test
	public void testIsForwarder_senderMatchesPassMsgToNull() {
		Message msg = new Message("B", "C", "null", "null", "request");

		assertFalse(GeneratorUtil.isForwarder("B", msg));
	}

	@Test
	public void testIsForwarder_senderDoesNotMatch() {
		Message msg = new Message("B", "C", "A", "null", "forwarded");

		assertFalse(GeneratorUtil.isForwarder("A", msg));
	}

	@Test
	public void testIsForwarder_hasRespondToIsNotForwarder() {
		Message msg = new Message("B", "A", "null", "request", "response");

		assertFalse(GeneratorUtil.isForwarder("B", msg),
			"A responder (respondTo != null) should not be a forwarder");
	}

	@Test
	public void testIsForwarder_caseInsensitiveNull() {
		Message msg = new Message("B", "C", "NULL", "NULL", "forwarded");

		assertFalse(GeneratorUtil.isForwarder("B", msg),
			"NULL (uppercase) in passMsgTo should be treated as null");
	}

	// --- helpers ---

	private Ambient createAmbient(String name, boolean isStatic, boolean isActive, String parent) {
		Ambient ambient = new Ambient();
		ambient.setName(name);
		ambient.setLocation("Loc");
		ambient.setLatitude("42.0");
		ambient.setLongitude("23.0");
		ambient.setStaticAmbient(isStatic);
		ambient.setActiveAmbient(isActive);
		ambient.setParentAmbient(parent);
		ambient.setAmbientType(AmbientType.CITY);
		return ambient;
	}
}

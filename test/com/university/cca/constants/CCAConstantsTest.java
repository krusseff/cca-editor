package com.university.cca.constants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class CCAConstantsTest {

	@Test
	public void testStatusConstants() {
		assertEquals(0, CCAConstants.CCA_STATUS_SUCCESS);
		assertEquals(1, CCAConstants.CCA_STATUS_FAILURE);
	}

	@Test
	public void testNullPlaceholderConstants() {
		assertEquals("null", CCAConstants.PASS_MESSAGE_TO_NULL);
		assertEquals("null", CCAConstants.MSG_RESPOND_TO_NULL);
	}

	@Test
	public void testTemplatePlaceholderConstants() {
		assertEquals("AMBIENT_SENDER", CCAConstants.AMBIENT_SENDER_PLACEHOLDER);
		assertEquals("AMBIENT_RECEIPIENT", CCAConstants.AMBIENT_RECEIPIENT_PLACEHOLDER);
		assertEquals("SEND_PARAMETERS", CCAConstants.SEND_PARAMETERS_PLACEHOLDER);
		assertEquals("RECEIVE_PARAMETERS", CCAConstants.RECEIVE_PARAMETERS_PLACEHOLDER);
		assertEquals("MESSAGES_PLACEHOLDER", CCAConstants.MESSAGES_PLACEHOLDER);
	}

	@Test
	public void testTemplateFilePathsNotNull() {
		assertNotNull(CCAConstants.AMBIENTS_DELIMITER_FILE_PATH);
		assertNotNull(CCAConstants.SEND_RECEIVE_ONE_AMBIENT_FILE_PATH);
		assertNotNull(CCAConstants.RECEIVE_SEND_ONE_AMBIENT_FILE_PATH);
		assertNotNull(CCAConstants.EXCHANGE_MESSAGES_FILE_PATH);
		assertNotNull(CCAConstants.SEND_TO_SIBLING_FILE_PATH);
		assertNotNull(CCAConstants.RECEIVE_FROM_SIBLING_FILE_PATH);
		assertNotNull(CCAConstants.RECEIVE_FROM_SIBLING_2_FILE_PATH);
		assertNotNull(CCAConstants.SEND_TO_SIBLING_2_FILE_PATH);
	}

	@Test
	public void testTemplateFilePathsEndWithCcaExtension() {
		assertTrue(CCAConstants.AMBIENTS_DELIMITER_FILE_PATH.endsWith(".cca"));
		assertTrue(CCAConstants.SEND_RECEIVE_ONE_AMBIENT_FILE_PATH.endsWith(".cca"));
		assertTrue(CCAConstants.RECEIVE_SEND_ONE_AMBIENT_FILE_PATH.endsWith(".cca"));
		assertTrue(CCAConstants.EXCHANGE_MESSAGES_FILE_PATH.endsWith(".cca"));
	}

	private void assertTrue(boolean condition) {
		org.junit.jupiter.api.Assertions.assertTrue(condition);
	}
}

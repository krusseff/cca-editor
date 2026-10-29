package com.university.cca.files.csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class CsvMessageBeanTest {

	@Test
	public void testDefaultConstructor() {
		CsvMessageBean bean = new CsvMessageBean();

		assertNull(bean.getSenderAmbient());
		assertNull(bean.getRecipientAmbient());
		assertNull(bean.getPassMessageTo());
		assertNull(bean.getRespondToMessage());
		assertNull(bean.getAmbientMessage());
	}

	@Test
	public void testParameterizedConstructor() {
		CsvMessageBean bean = new CsvMessageBean(
			"SensorA", "Gateway", "Hub", "OK", "Temperature is 25C"
		);

		assertEquals("SensorA", bean.getSenderAmbient());
		assertEquals("Gateway", bean.getRecipientAmbient());
		assertEquals("Hub", bean.getPassMessageTo());
		assertEquals("OK", bean.getRespondToMessage());
		assertEquals("Temperature is 25C", bean.getAmbientMessage());
	}

	@Test
	public void testSettersAndGetters() {
		CsvMessageBean bean = new CsvMessageBean();

		bean.setSenderAmbient("Drone1");
		bean.setRecipientAmbient("OperationsCenter");
		bean.setPassMessageTo("HQ");
		bean.setRespondToMessage("Acknowledged");
		bean.setAmbientMessage("Field scan complete");

		assertEquals("Drone1", bean.getSenderAmbient());
		assertEquals("OperationsCenter", bean.getRecipientAmbient());
		assertEquals("HQ", bean.getPassMessageTo());
		assertEquals("Acknowledged", bean.getRespondToMessage());
		assertEquals("Field scan complete", bean.getAmbientMessage());
	}

	@Test
	public void testSetSenderOverwritesPreviousValue() {
		CsvMessageBean bean = new CsvMessageBean();
		bean.setSenderAmbient("First");
		bean.setSenderAmbient("Second");
		assertEquals("Second", bean.getSenderAmbient());
	}

	@Test
	public void testNullValues() {
		CsvMessageBean bean = new CsvMessageBean();
		bean.setSenderAmbient(null);
		bean.setRecipientAmbient(null);
		bean.setPassMessageTo(null);
		bean.setRespondToMessage(null);
		bean.setAmbientMessage(null);

		assertNull(bean.getSenderAmbient());
		assertNull(bean.getRecipientAmbient());
		assertNull(bean.getPassMessageTo());
		assertNull(bean.getRespondToMessage());
		assertNull(bean.getAmbientMessage());
	}

	@Test
	public void testEmptyStringValues() {
		CsvMessageBean bean = new CsvMessageBean();
		bean.setSenderAmbient("");
		bean.setRecipientAmbient("");
		bean.setPassMessageTo("");
		bean.setRespondToMessage("");
		bean.setAmbientMessage("");

		assertEquals("", bean.getSenderAmbient());
		assertEquals("", bean.getRecipientAmbient());
		assertEquals("", bean.getPassMessageTo());
		assertEquals("", bean.getRespondToMessage());
		assertEquals("", bean.getAmbientMessage());
	}

	@Test
	public void testSpecialCharactersInMessage() {
		CsvMessageBean bean = new CsvMessageBean();
		bean.setAmbientMessage("Alert: temp > 100°C & humidity < 20%!");
		assertEquals("Alert: temp > 100°C & humidity < 20%!", bean.getAmbientMessage());
	}
}

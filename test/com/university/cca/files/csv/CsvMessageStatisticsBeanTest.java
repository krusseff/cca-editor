package com.university.cca.files.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class CsvMessageStatisticsBeanTest {

	@Test
	public void testDefaultConstructor() {
		CsvMessageStatisticsBean bean = new CsvMessageStatisticsBean();

		assertNull(bean.getAmbientName());
		assertEquals(0, bean.getSentMessages());
		assertEquals(0, bean.getReceivedMessages());
	}

	@Test
	public void testParameterizedConstructor() {
		CsvMessageStatisticsBean bean = new CsvMessageStatisticsBean("Hospital", 15, 8);

		assertEquals("Hospital", bean.getAmbientName());
		assertEquals(15, bean.getSentMessages());
		assertEquals(8, bean.getReceivedMessages());
	}

	@Test
	public void testSettersAndGetters() {
		CsvMessageStatisticsBean bean = new CsvMessageStatisticsBean();

		bean.setAmbientName("Drone");
		bean.setSentMessages(42);
		bean.setReceivedMessages(37);

		assertEquals("Drone", bean.getAmbientName());
		assertEquals(42, bean.getSentMessages());
		assertEquals(37, bean.getReceivedMessages());
	}

	@Test
	public void testSetAmbientNameOverwritesPreviousValue() {
		CsvMessageStatisticsBean bean = new CsvMessageStatisticsBean();
		bean.setAmbientName("First");
		bean.setAmbientName("Second");
		assertEquals("Second", bean.getAmbientName());
	}

	@Test
	public void testZeroCounts() {
		CsvMessageStatisticsBean bean = new CsvMessageStatisticsBean("Empty", 0, 0);

		assertEquals("Empty", bean.getAmbientName());
		assertEquals(0, bean.getSentMessages());
		assertEquals(0, bean.getReceivedMessages());
	}

	@Test
	public void testNullAmbientName() {
		CsvMessageStatisticsBean bean = new CsvMessageStatisticsBean();
		bean.setAmbientName(null);
		assertNull(bean.getAmbientName());
	}

	@Test
	public void testLargeValues() {
		CsvMessageStatisticsBean bean = new CsvMessageStatisticsBean(
			"HighTraffic", Integer.MAX_VALUE, Integer.MAX_VALUE
		);

		assertEquals(Integer.MAX_VALUE, bean.getSentMessages());
		assertEquals(Integer.MAX_VALUE, bean.getReceivedMessages());
	}
}

package com.university.cca.files.csv;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CsvAmbientStatisticsBeanTest {

	@Test
	public void testDefaultConstructor() {
		CsvAmbientStatisticsBean bean = new CsvAmbientStatisticsBean();

		assertEquals(0, bean.getTotalCount());
		assertEquals(0, bean.getStaticCount());
		assertEquals(0, bean.getNonStaticCount());
		assertEquals(0, bean.getActiveCount());
		assertEquals(0, bean.getInactiveCount());
	}

	@Test
	public void testParameterizedConstructor() {
		CsvAmbientStatisticsBean bean = new CsvAmbientStatisticsBean(100, 60, 40, 80, 20);

		assertEquals(100, bean.getTotalCount());
		assertEquals(60, bean.getStaticCount());
		assertEquals(40, bean.getNonStaticCount());
		assertEquals(80, bean.getActiveCount());
		assertEquals(20, bean.getInactiveCount());
	}

	@Test
	public void testSettersAndGetters() {
		CsvAmbientStatisticsBean bean = new CsvAmbientStatisticsBean();

		bean.setTotalCount(50);
		bean.setStaticCount(30);
		bean.setNonStaticCount(20);
		bean.setActiveCount(45);
		bean.setInactiveCount(5);

		assertEquals(50, bean.getTotalCount());
		assertEquals(30, bean.getStaticCount());
		assertEquals(20, bean.getNonStaticCount());
		assertEquals(45, bean.getActiveCount());
		assertEquals(5, bean.getInactiveCount());
	}

	@Test
	public void testSetTotalCountOverwritesPreviousValue() {
		CsvAmbientStatisticsBean bean = new CsvAmbientStatisticsBean();
		bean.setTotalCount(10);
		bean.setTotalCount(99);
		assertEquals(99, bean.getTotalCount());
	}

	@Test
	public void testZeroValues() {
		CsvAmbientStatisticsBean bean = new CsvAmbientStatisticsBean(0, 0, 0, 0, 0);

		assertEquals(0, bean.getTotalCount());
		assertEquals(0, bean.getStaticCount());
		assertEquals(0, bean.getNonStaticCount());
		assertEquals(0, bean.getActiveCount());
		assertEquals(0, bean.getInactiveCount());
	}

	@Test
	public void testLargeValues() {
		CsvAmbientStatisticsBean bean = new CsvAmbientStatisticsBean(
			Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
			Integer.MAX_VALUE, Integer.MAX_VALUE
		);

		assertEquals(Integer.MAX_VALUE, bean.getTotalCount());
		assertEquals(Integer.MAX_VALUE, bean.getStaticCount());
		assertEquals(Integer.MAX_VALUE, bean.getNonStaticCount());
		assertEquals(Integer.MAX_VALUE, bean.getActiveCount());
		assertEquals(Integer.MAX_VALUE, bean.getInactiveCount());
	}
}

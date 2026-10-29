package com.university.cca.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.university.cca.constants.ApplicationModes;

public class AppModeUtilTest {

	// --- valid modes ---

	@Test
	public void testFindApplicationMode_smartCity() {
		assertEquals(ApplicationModes.SMART_CITY, AppModeUtil.findApplicationMode(new String[]{"0"}));
	}

	@Test
	public void testFindApplicationMode_smartAgriculture() {
		assertEquals(ApplicationModes.SMART_AGRICULTURE, AppModeUtil.findApplicationMode(new String[]{"1"}));
	}

	@Test
	public void testFindApplicationMode_smartEducation() {
		assertEquals(ApplicationModes.SMART_EDUCATION, AppModeUtil.findApplicationMode(new String[]{"2"}));
	}

	// --- empty args → default ---

	@Test
	public void testFindApplicationMode_emptyArgs() {
		assertEquals(ApplicationModes.SMART_CITY, AppModeUtil.findApplicationMode(new String[]{}));
	}

	// --- invalid numeric mode → default ---

	@Test
	public void testFindApplicationMode_negativeValue() {
		assertEquals(ApplicationModes.SMART_CITY, AppModeUtil.findApplicationMode(new String[]{"-1"}));
	}

	@Test
	public void testFindApplicationMode_tooHigh() {
		assertEquals(ApplicationModes.SMART_CITY, AppModeUtil.findApplicationMode(new String[]{"3"}));
	}

	@Test
	public void testFindApplicationMode_largeNumber() {
		assertEquals(ApplicationModes.SMART_CITY, AppModeUtil.findApplicationMode(new String[]{"999"}));
	}

	// --- non-numeric string → default ---

	@Test
	public void testFindApplicationMode_nonNumericString() {
		assertEquals(ApplicationModes.SMART_CITY, AppModeUtil.findApplicationMode(new String[]{"abc"}));
	}

	@Test
	public void testFindApplicationMode_emptyString() {
		assertEquals(ApplicationModes.SMART_CITY, AppModeUtil.findApplicationMode(new String[]{""}));
	}

	@Test
	public void testFindApplicationMode_specialChars() {
		assertEquals(ApplicationModes.SMART_CITY, AppModeUtil.findApplicationMode(new String[]{"!@#"}));
	}

	// --- extra args ignored (only first used) ---

	@Test
	public void testFindApplicationMode_extraArgsIgnored() {
		assertEquals(ApplicationModes.SMART_AGRICULTURE, AppModeUtil.findApplicationMode(new String[]{"1", "extra", "args"}));
	}

	@Test
	public void testFindApplicationMode_firstArgInvalidSecondValid() {
		assertEquals(ApplicationModes.SMART_CITY, AppModeUtil.findApplicationMode(new String[]{"bad", "1"}));
	}

	// --- boundary: decimal string ---

	@Test
	public void testFindApplicationMode_decimalString() {
		assertEquals(ApplicationModes.SMART_CITY, AppModeUtil.findApplicationMode(new String[]{"1.5"}));
	}

	// --- boundary: whitespace ---

	@Test
	public void testFindApplicationMode_whitespace() {
		assertEquals(ApplicationModes.SMART_CITY, AppModeUtil.findApplicationMode(new String[]{" "}));
	}
}

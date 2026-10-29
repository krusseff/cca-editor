package com.university.cca.exceptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ExceptionsTest {

	// --- AmbientMessageNotFoundException ---

	@Test
	public void testAmbientMessageNotFoundExceptionMessage() {
		AmbientMessageNotFoundException ex = new AmbientMessageNotFoundException("test error");
		
		assertEquals("test error", ex.getMessage());
	}

	@Test
	public void testAmbientMessageNotFoundExceptionIsCheckedException() {
		AmbientMessageNotFoundException ex = new AmbientMessageNotFoundException("test");
		
		assertTrue(ex instanceof Exception);
	}

	// --- FileNotFoundException ---

	@Test
	public void testFileNotFoundExceptionMessage() {
		FileNotFoundException ex = new FileNotFoundException("/path/to/file.csv");
		
		assertTrue(ex.getMessage().contains("/path/to/file.csv"));
		assertTrue(ex.getMessage().contains("File not found"));
	}

	@Test
	public void testFileNotFoundExceptionFilePath() {
		FileNotFoundException ex = new FileNotFoundException("/path/to/file.csv");
		
		assertEquals("/path/to/file.csv", ex.getFilePath());
	}

	@Test
	public void testFileNotFoundExceptionIsCheckedException() {
		FileNotFoundException ex = new FileNotFoundException("/path/to/file.csv");
		
		assertTrue(ex instanceof Exception);
	}

	// --- FileReadFailedException ---

	@Test
	public void testFileReadFailedExceptionMessage() {
		FileReadFailedException ex = new FileReadFailedException("read failed");
		
		assertEquals("read failed", ex.getMessage());
	}

	@Test
	public void testFileReadFailedExceptionIsCheckedException() {
		FileReadFailedException ex = new FileReadFailedException("read failed");
		
		assertTrue(ex instanceof Exception);
	}
}

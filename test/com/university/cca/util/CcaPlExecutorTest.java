package com.university.cca.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.university.cca.constants.Constants;
import com.university.cca.util.CcaPlExecutor.ExecutionResult;

/**
 * Tests for the CcaPlExecutor utility class that runs CCA scenarios
 * using the ccaPL interpreter JAR.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CcaPlExecutorTest {

	// --- JAR availability tests ---

	@Test
	public void testIsJarAvailable_returnsTrue() {
		assertTrue(CcaPlExecutor.isJarAvailable(), "ccaPL.jar should be available in the lib folder");
	}

	// --- Successful execution tests ---

	@Test
	public void testExecute_successfulWithDefaultCcaFile() {
		ExecutionResult result = CcaPlExecutor.execute(Constants.AMBIENTS_CCA_FILE_PATH);

		assertNotNull(result, "Execution result should not be null");
		assertTrue(result.isSuccess(), "Execution should be successful");
		assertTrue(result.hasOutput(), "Execution should produce output");
		assertTrue(result.getOutput().contains("CCA Interpreter version 2.0"),
				"Output should contain interpreter banner");
		assertTrue(result.getOutput().contains("CCA program parsed successfully"),
				"Output should indicate successful parsing");
	}

	@Test
	public void testExecute_outputContainsReductionSteps() {
		ExecutionResult result = CcaPlExecutor.execute(Constants.AMBIENTS_CCA_FILE_PATH);

		assertTrue(result.isSuccess(), "Execution should be successful");
		assertTrue(result.getOutput().contains("--->"),
				"Output should contain reduction step markers");
	}

	@Test
	public void testExecute_outputContainsSiblingCommunication() {
		ExecutionResult result = CcaPlExecutor.execute(Constants.AMBIENTS_CCA_FILE_PATH);

		assertTrue(result.isSuccess(), "Execution should be successful");
		assertTrue(result.getOutput().contains("Sibling to sibling:"),
				"Output should contain sibling-to-sibling communication");
	}

	@Test
	public void testExecute_outputContainsParentChildCommunication() {
		ExecutionResult result = CcaPlExecutor.execute(Constants.AMBIENTS_CCA_FILE_PATH);

		assertTrue(result.isSuccess(), "Execution should be successful");
		assertTrue(result.getOutput().contains("Parent to child:"),
				"Output should contain parent-to-child communication");
	}

	@Test
	public void testExecute_outputContainsChildParentCommunication() {
		ExecutionResult result = CcaPlExecutor.execute(Constants.AMBIENTS_CCA_FILE_PATH);

		assertTrue(result.isSuccess(), "Execution should be successful");
		assertTrue(result.getOutput().contains("Child to parent:"),
				"Output should contain child-to-parent communication");
	}

	@Test
	public void testExecute_emptyErrorMessageOnSuccess() {
		ExecutionResult result = CcaPlExecutor.execute(Constants.AMBIENTS_CCA_FILE_PATH);

		assertTrue(result.isSuccess(), "Execution should be successful");
		assertEquals("", result.getErrorMessage(), "Error message should be empty on success");
	}

	// --- Error handling tests ---

	@Test
	public void testExecute_nonExistentFile() {
		ExecutionResult result = CcaPlExecutor.execute("non_existent_file.cca");

		assertNotNull(result, "Execution result should not be null");
		assertFalse(result.isSuccess(), "Execution should fail for non-existent file");
		assertFalse(result.hasOutput(), "Should have no output for non-existent file");
		assertTrue(result.getErrorMessage().contains("CCA file not found"),
				"Error should mention file not found");
	}

	@Test
	public void testExecute_emptyFilePath() {
		ExecutionResult result = CcaPlExecutor.execute("");

		assertNotNull(result, "Execution result should not be null");
		assertFalse(result.isSuccess(), "Execution should fail for empty file path");
	}

	@Test
	public void testExecute_nullFilePath() {
		ExecutionResult result = CcaPlExecutor.execute(null);

		assertNotNull(result, "Execution result should not be null");
		assertFalse(result.isSuccess(), "Execution should fail for null file path");
	}

	// --- ExecutionResult tests ---

	@Test
	public void testExecutionResult_successfulResult() {
		ExecutionResult result = new ExecutionResult(true, "some output", "");

		assertTrue(result.isSuccess());
		assertEquals("some output", result.getOutput());
		assertEquals("", result.getErrorMessage());
		assertTrue(result.hasOutput());
	}

	@Test
	public void testExecutionResult_failedResult() {
		ExecutionResult result = new ExecutionResult(false, "", "error occurred");

		assertFalse(result.isSuccess());
		assertEquals("", result.getOutput());
		assertEquals("error occurred", result.getErrorMessage());
		assertFalse(result.hasOutput());
	}

	@Test
	public void testExecutionResult_hasOutputWithBlank() {
		ExecutionResult result = new ExecutionResult(true, "   ", "");
		assertFalse(result.hasOutput(), "Blank output should return false for hasOutput");
	}

	@Test
	public void testExecutionResult_hasOutputWithNull() {
		ExecutionResult result = new ExecutionResult(true, null, "");
		assertFalse(result.hasOutput(), "Null output should return false for hasOutput");
	}
}

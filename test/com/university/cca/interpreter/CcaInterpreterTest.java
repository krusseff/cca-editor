package com.university.cca.interpreter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.university.cca.constants.Constants;
import com.university.cca.interpreter.CcaInterpreter.ExecutionResult;

/**
 * Tests for the integrated ccaPL interpreter.
 * Verifies that the interpreter correctly parses and executes CCA programs,
 * including all communication types (sibling, parent-child, child-parent).
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CcaInterpreterTest {

	// --- Successful execution tests ---

	@Test
	public void testExecute_successfulWithDefaultCcaFile() {
		String output = CcaInterpreter.execute(Constants.AMBIENTS_CCA_FILE_PATH);

		assertNotNull(output, "Execution output should not be null");
		assertFalse(output.isEmpty(), "Execution should produce output");
		assertTrue(output.contains("CCA Interpreter version 2.0"),
				"Output should contain interpreter banner");
		assertTrue(output.contains("CCA program parsed successfully"),
				"Output should indicate successful parsing");
	}

	@Test
	public void testExecute_outputContainsEditorBanner() {
		String output = CcaInterpreter.execute(Constants.AMBIENTS_CCA_FILE_PATH);

		assertTrue(output.contains("Integrated into CCA Editor"),
				"Output should contain CCA Editor integration banner");
		assertTrue(output.contains("Konstantin Rusev"),
				"Output should contain author name");
	}

	@Test
	public void testExecute_outputContainsReductionSteps() {
		String output = CcaInterpreter.execute(Constants.AMBIENTS_CCA_FILE_PATH);

		assertTrue(output.contains("--->"),
				"Output should contain reduction step markers");
	}

	@Test
	public void testExecute_outputContainsSiblingCommunication() {
		String output = CcaInterpreter.execute(Constants.AMBIENTS_CCA_FILE_PATH);

		assertTrue(output.contains("Sibling to sibling:"),
				"Output should contain sibling-to-sibling communication");
	}

	@Test
	public void testExecute_outputContainsParentChildCommunication() {
		String output = CcaInterpreter.execute(Constants.AMBIENTS_CCA_FILE_PATH);

		assertTrue(output.contains("Parent to child:"),
				"Output should contain parent-to-child communication");
	}

	@Test
	public void testExecute_outputContainsChildParentCommunication() {
		String output = CcaInterpreter.execute(Constants.AMBIENTS_CCA_FILE_PATH);

		assertTrue(output.contains("Child to parent:"),
				"Output should contain child-to-parent communication");
	}

	@Test
	public void testExecute_outputContainsBusStationTaxiExchange() {
		String output = CcaInterpreter.execute(Constants.AMBIENTS_CCA_FILE_PATH);

		assertTrue(output.contains("BUS_STATION ===(are_you_free)===> TAXI"),
				"Output should contain BUS_STATION to TAXI message");
		assertTrue(output.contains("TAXI ===(are_you_free,yes_I_am)===> BUS_STATION"),
				"Output should contain TAXI response to BUS_STATION");
	}

	@Test
	public void testExecute_outputContainsUniversityFmiExchange() {
		String output = CcaInterpreter.execute(Constants.AMBIENTS_CCA_FILE_PATH);

		assertTrue(output.contains("PLOVDIV_UNIVERSITY ===(FMI_rooms_from_uni_to_fmi)===> FMI"),
				"Output should contain parent-to-child message");
		assertTrue(output.contains("FMI ===(FMI_rooms_from_uni_to_fmi,fmi_to_uni_543)===> PLOVDIV_UNIVERSITY"),
				"Output should contain child-to-parent response");
	}

	@Test
	public void testExecute_producesExactlySixReductionSteps() {
		String output = CcaInterpreter.execute(Constants.AMBIENTS_CCA_FILE_PATH);

		int count = 0;
		int index = 0;
		while ((index = output.indexOf("--->", index)) != -1) {
			count++;
			index += 4;
		}
		assertEquals(6, count, "Output should contain exactly 6 reduction steps");
	}

	// --- Reusability tests (parser state reset) ---

	@Test
	public void testExecute_canRunMultipleTimes() {
		String output1 = CcaInterpreter.execute(Constants.AMBIENTS_CCA_FILE_PATH);
		String output2 = CcaInterpreter.execute(Constants.AMBIENTS_CCA_FILE_PATH);

		assertNotNull(output1, "First execution should produce output");
		assertNotNull(output2, "Second execution should produce output");
		assertEquals(output1, output2,
				"Two consecutive executions should produce identical output");
	}

	@Test
	public void testExecute_stateResetBetweenRuns() {
		String output1 = CcaInterpreter.execute(Constants.AMBIENTS_CCA_FILE_PATH);
		String output2 = CcaInterpreter.execute(Constants.AMBIENTS_CCA_FILE_PATH);

		assertTrue(output2.contains("CCA Interpreter version 2.0"),
				"Second run should contain the interpreter banner (state reset)");
		assertTrue(output2.contains("CCA program parsed successfully"),
				"Second run should parse successfully (state reset)");
	}

	// --- Error handling tests ---

	@Test
	public void testExecute_nullFilePathThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> {
			CcaInterpreter.execute(null);
		}, "Null file path should throw IllegalArgumentException");
	}

	@Test
	public void testExecute_emptyFilePathThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> {
			CcaInterpreter.execute("");
		}, "Empty file path should throw IllegalArgumentException");
	}

	@Test
	public void testExecute_blankFilePathThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> {
			CcaInterpreter.execute("   ");
		}, "Blank file path should throw IllegalArgumentException");
	}

	@Test
	public void testExecute_nonExistentFileThrowsCcaException() {
		assertThrows(CcaException.class, () -> {
			CcaInterpreter.execute("non_existent_file.cca");
		}, "Non-existent file should throw CcaException");
	}

	// --- ExecuteWithResult tests ---

	@Test
	public void testExecuteWithResult_successfulExecution() {
		ExecutionResult result = CcaInterpreter.executeWithResult(Constants.AMBIENTS_CCA_FILE_PATH);

		assertNotNull(result, "Execution result should not be null");
		assertTrue(result.isSuccess(), "Execution should be successful");
		assertTrue(result.hasOutput(), "Execution should produce output");
		assertTrue(result.getOutput().contains("CCA program parsed successfully"),
				"Output should indicate successful parsing");
	}

	@Test
	public void testExecuteWithResult_nonExistentFile() {
		ExecutionResult result = CcaInterpreter.executeWithResult("non_existent_file.cca");

		assertNotNull(result, "Execution result should not be null");
		assertFalse(result.isSuccess(), "Execution should fail for non-existent file");
		assertNotNull(result.getErrorMessage(), "Error message should not be null");
	}

	@Test
	public void testExecuteWithResult_nullFilePath() {
		ExecutionResult result = CcaInterpreter.executeWithResult(null);

		assertNotNull(result, "Execution result should not be null");
		assertFalse(result.isSuccess(), "Execution should fail for null file path");
	}

	// --- ExecutionResult unit tests ---

	@Test
	public void testExecutionResult_successfulResult() {
		ExecutionResult result = new ExecutionResult(true, "some output", null);

		assertTrue(result.isSuccess());
		assertEquals("some output", result.getOutput());
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
	public void testExecutionResult_nullOutputHandled() {
		ExecutionResult result = new ExecutionResult(true, null, null);

		assertEquals("", result.getOutput());
		assertFalse(result.hasOutput());
	}
}

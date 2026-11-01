package com.university.cca.interpreter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Facade class for executing CCA programs using the integrated ccaPL interpreter.
 * Wraps the JavaCC-generated Cca_parser and its model classes, providing a clean API
 * for the CCA Editor GUI to parse and execute .cca files.
 *
 * <p>Handles static state reset between executions (the original interpreter uses
 * static fields throughout), output capture via {@link CcaOutput}, and error
 * translation from {@code System.exit(1)} calls into {@link CcaException}.</p>
 *
 * <p>Based on ccaPL Interpreter version 2.0 by François Siewe (De Montfort University),
 * modified for verification of Tourist Guide System (2017).
 * Integrated into CCA Editor by Konstantin Rusev (2026).</p>
 *
 * @author Konstantin Rusev
 * @version 1.0
 */
public final class CcaInterpreter {

	private static final Logger LOG = LoggerFactory.getLogger(CcaInterpreter.class);

	private static boolean parserInitialized = false;

	private CcaInterpreter() {
		// Prevent instantiation — all methods are static
	}

	/**
	 * Executes a CCA program from a .cca file and returns the execution trace.
	 *
	 * @param ccaFilePath absolute or relative path to the .cca file
	 * @return the execution trace output as a string
	 * @throws CcaException if the CCA program contains errors
	 * @throws IllegalArgumentException if the file path is null or empty
	 */
	public static String execute(String ccaFilePath) {
		if (ccaFilePath == null || ccaFilePath.trim().isEmpty()) {
			throw new IllegalArgumentException("CCA file path cannot be null or empty.");
		}

		LOG.info("Executing CCA program from file: {}", ccaFilePath);

		resetInterpreterState();

		try {
			initializeParser(ccaFilePath);

			Ambient root = Cca_parser.program();
			CcaOutput.println("CCA Parser Version 2.0:  CCA program parsed successfully.");

			if (Ambient.displayCode) {
				root.display(0);
			} else {
				CcaOutput.println("\n");
			}

			root.exec(root);

			String output = CcaOutput.getOutput();
			LOG.info("CCA program execution completed successfully.");

			return output;

		} catch (ParseException e) {
			CcaOutput.println("CCA Parser Version 2.0:  Encountered errors during parse.");
			CcaOutput.println(e.getMessage());
			String output = CcaOutput.getOutput();
			LOG.error("CCA parse error: {}", e.getMessage());
			throw new CcaException("Parse error: " + e.getMessage(), e);

		} catch (TokenMgrError e) {
			CcaOutput.println("CCA Parser Version 2.0:  Lexical error.");
			CcaOutput.println(e.getMessage());
			String output = CcaOutput.getOutput();
			LOG.error("CCA lexical error: {}", e.getMessage());
			throw new CcaException("Lexical error: " + e.getMessage(), e);

		} catch (CcaException e) {
			LOG.error("CCA execution error: {}", e.getMessage());
			throw e;

		} catch (Exception e) {
			LOG.error("Unexpected error during CCA execution: {}", e.getMessage(), e);
			throw new CcaException("Unexpected error: " + e.getMessage(), e);
		}
	}

	/**
	 * Executes a CCA program and returns both success status and output.
	 * Unlike {@link #execute(String)}, this method does not throw exceptions
	 * for CCA program errors — it wraps them in the result.
	 *
	 * @param ccaFilePath absolute or relative path to the .cca file
	 * @return an {@link ExecutionResult} with success status and output
	 */
	public static ExecutionResult executeWithResult(String ccaFilePath) {
		try {
			String output = execute(ccaFilePath);
			return new ExecutionResult(true, output, null);
		} catch (CcaException e) {
			String partialOutput = CcaOutput.getOutput();
			return new ExecutionResult(false, partialOutput, e.getMessage());
		} catch (Exception e) {
			String partialOutput = CcaOutput.getOutput();
			return new ExecutionResult(false, partialOutput, "Unexpected error: " + e.getMessage());
		}
	}

	/**
	 * Resets all static state in the interpreter classes so the parser
	 * can be reused for multiple executions within the same JVM session.
	 */
	private static void resetInterpreterState() {
		CcaOutput.clear();

		Ambient.ROOT = null;
		Ambient.definition = null;
		Ambient.displayCong = false;
		Ambient.displayCode = false;

		CcaEnum.mem = 0;
		CcaEnum.RULE = "";
	}

	/**
	 * Initializes or re-initializes the static JavaCC parser with a new input file.
	 */
	private static void initializeParser(String ccaFilePath) throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(ccaFilePath);

		CcaOutput.println(
			"     ******************************************\n" +
			"     **                                      **\n" +
			"     **                                      **\n" +
			"     **    CCA Interpreter version 2.0       **\n" +
			"     **                                      **\n" +
			"     **    Integrated into CCA Editor        **\n" +
			"     **    by Konstantin Rusev (2026)         **\n" +
			"     **                                      **\n" +
			"     ******************************************\n"
		);
		CcaOutput.println("CCA Parser Version 2.0:  Reading from file " + ccaFilePath + " . . .");

		if (parserInitialized) {
			Cca_parser.ReInit(fis);
		} else {
			new Cca_parser(fis);
			parserInitialized = true;
		}
	}

	/**
	 * Holds the result of a CCA program execution.
	 */
	public static class ExecutionResult {

		private final boolean success;
		private final String output;
		private final String errorMessage;

		public ExecutionResult(boolean success, String output, String errorMessage) {
			this.success = success;
			this.output = output != null ? output : "";
			this.errorMessage = errorMessage;
		}

		public boolean isSuccess() {
			return success;
		}

		public String getOutput() {
			return output;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public boolean hasOutput() {
			return output != null && !output.isEmpty();
		}
	}
}

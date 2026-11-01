package com.university.cca.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for executing CCA scenarios using the ccaPL interpreter JAR.
 * 
 * The ccaPL interpreter (version 2.0) was created by François Siewe at De Montfort University.
 * It parses and executes CCA (Calculus of Context-aware Ambients) programs.
 * 
 * Usage: java -jar ccaPL.jar NameOfFile.cca
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public final class CcaPlExecutor {

	private static final Logger logger = LoggerFactory.getLogger(CcaPlExecutor.class);

	private static final String CCAPL_JAR_PATH = "./lib/ccaPL.jar";
	private static final long TIMEOUT_SECONDS = 30;

	private CcaPlExecutor() {
		// Prevent creating an object of type CcaPlExecutor class
	}

	/**
	 * Executes a CCA scenario file using the ccaPL interpreter JAR.
	 * Runs the command: java -jar ccaPL.jar [ccaFilePath]
	 * 
	 * @param ccaFilePath the path to the .cca file to execute
	 * @return an ExecutionResult containing the output and status
	 */
	public static ExecutionResult execute(String ccaFilePath) {
		logger.info("Starting CCA scenario execution for file: {}", ccaFilePath);

		if (ccaFilePath == null || ccaFilePath.trim().isEmpty()) {
			String error = "CCA file path is null or empty. Please provide a valid .cca file path.";
			logger.error(error);
			return new ExecutionResult(false, "", error);
		}

		File jarFile = new File(CCAPL_JAR_PATH);
		if (!jarFile.exists()) {
			String error = "ccaPL interpreter JAR not found at: " + jarFile.getAbsolutePath();
			logger.error(error);
			return new ExecutionResult(false, "", error);
		}

		File ccaFile = new File(ccaFilePath);
		if (!ccaFile.exists()) {
			String error = "CCA file not found at: " + ccaFile.getAbsolutePath()
					+ "\nPlease generate the CCA file first using the CCA Generator.";
			logger.error("CCA file not found at: {}", ccaFile.getAbsolutePath());
			return new ExecutionResult(false, "", error);
		}

		try {
			ProcessBuilder processBuilder = new ProcessBuilder(
				"java", "-jar", jarFile.getAbsolutePath(), ccaFile.getAbsolutePath()
			);
			processBuilder.redirectErrorStream(true);

			logger.info("Executing command: java -jar {} {}", jarFile.getAbsolutePath(), ccaFile.getAbsolutePath());

			Process process = processBuilder.start();

			StringBuilder output = new StringBuilder();
			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
				String line;
				while ((line = reader.readLine()) != null) {
					output.append(line).append("\n");
				}
			}

			boolean finished = process.waitFor(TIMEOUT_SECONDS, TimeUnit.SECONDS);
			if (!finished) {
				process.destroyForcibly();
				String error = "CCA scenario execution timed out after " + TIMEOUT_SECONDS + " seconds.\n"
						+ "The scenario may contain infinite loops or too many reduction steps.";
				logger.error("CCA execution timed out for file: {}", ccaFilePath);
				return new ExecutionResult(false, output.toString(), error);
			}

			int exitCode = process.exitValue();
			String outputStr = output.toString();

			if (exitCode == 0) {
				logger.info("CCA scenario executed successfully. Output length: {} characters", outputStr.length());
				return new ExecutionResult(true, outputStr, "");
			} else {
				String error = "CCA interpreter exited with code: " + exitCode;
				logger.error("CCA execution failed with exit code: {} for file: {}", exitCode, ccaFilePath);
				return new ExecutionResult(false, outputStr, error);
			}

		} catch (Exception e) {
			String error = "Failed to execute CCA scenario: " + e.getMessage()
					+ "\nPlease ensure Java is installed and available in the system PATH.";
			logger.error("Exception during CCA execution for file: {}", ccaFilePath, e);
			return new ExecutionResult(false, "", error);
		}
	}

	/**
	 * Checks whether the ccaPL interpreter JAR file exists at the expected location.
	 * 
	 * @return true if the JAR file exists, false otherwise
	 */
	public static boolean isJarAvailable() {
		return new File(CCAPL_JAR_PATH).exists();
	}

	/**
	 * Holds the result of a CCA scenario execution.
	 */
	public static class ExecutionResult {
		private final boolean success;
		private final String output;
		private final String errorMessage;

		public ExecutionResult(boolean success, String output, String errorMessage) {
			this.success = success;
			this.output = output;
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
			return output != null && !output.trim().isEmpty();
		}
	}
}

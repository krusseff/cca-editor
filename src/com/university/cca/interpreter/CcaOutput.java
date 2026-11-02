package com.university.cca.interpreter;

/**
 * Captures the output of the ccaPL interpreter execution.
 * Replaces System.out.print/println calls in the interpreter classes
 * so output can be displayed in the CCA Editor GUI.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public final class CcaOutput {

	private static StringBuilder buffer = new StringBuilder();

	private CcaOutput() {
		// Prevent instantiation
	}

	public static void print(String s) {
		buffer.append(s);
	}

	public static void println(String s) {
		buffer.append(s).append("\n");
	}

	public static String getOutput() {
		return buffer.toString();
	}

	public static void clear() {
		buffer = new StringBuilder();
	}
}

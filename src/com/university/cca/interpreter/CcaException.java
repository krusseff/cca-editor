package com.university.cca.interpreter;

/**
 * Exception thrown by the ccaPL interpreter when a CCA program
 * contains errors (e.g., invalid process abstraction calls, cascading guards).
 * Replaces System.exit(1) calls in the original interpreter code.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CcaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CcaException(String message) {
		super(message);
	}

	public CcaException(String message, Throwable cause) {
		super(message, cause);
	}
}

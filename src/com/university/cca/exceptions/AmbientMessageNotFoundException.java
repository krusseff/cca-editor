package com.university.cca.exceptions;

/**
 * Custom exception class that handles the case when an ambient message is missed.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientMessageNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public AmbientMessageNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

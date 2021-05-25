package com.university.cca.exceptions;

/**
 * Custom exception class that handles the case when an ambient message is missed.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientMessageNotFound extends Exception {

	private static final long serialVersionUID = 1L;

	public AmbientMessageNotFound(String errorMessage) {
        super(errorMessage);
    }
}

package com.university.cca.exceptions;

/**
 * Custom exception class for missing ambient message
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

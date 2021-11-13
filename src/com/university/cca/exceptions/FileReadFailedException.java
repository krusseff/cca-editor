package com.university.cca.exceptions;

/**
 * Custom exception class that handles the case when file reading failed.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class FileReadFailedException extends Exception {

	private static final long serialVersionUID = 1L;

	public FileReadFailedException(String errorMessage) {
        super(errorMessage);
    }
}

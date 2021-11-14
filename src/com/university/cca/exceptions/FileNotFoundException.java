package com.university.cca.exceptions;

/**
 * Custom exception class that handles the case when a file is not found.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class FileNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private static final String ERROR_MESSAGE = "File not found on the specified path: ";
	
	private final String filePath;

	public FileNotFoundException(String filePath) {
        super(ERROR_MESSAGE + filePath);

        this.filePath = filePath;
    }

	public String getFilePath() {
		return this.filePath;
	}
}

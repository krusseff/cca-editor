package com.university.cca.constants;

/**
 * All constants related to the CCA Templates that should be used to generate CCA files.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CCATemplates {
	
	// CCA Templates files paths
	public static final String AMBIENTS_DELIMITER_FILE_PATH = "./files/cca_templates/ambients_delimiter.cca";
	public static final String SEND_RECEIVE_ONE_AMBIENT_FILE_PATH = "./files/cca_templates/send_receive_one_ambient.cca";
	public static final String RECEIVE_SEND_ONE_AMBIENT_FILE_PATH = "./files/cca_templates/receive_send_one_ambient.cca";
	
	// CCA Templates placeholders
	public static final String AMBIENT_SENDER_PLACEHOLDER = "AMBIENT_SENDER";
	public static final String AMBIENT_RECEIPIENT_PLACEHOLDER = "AMBIENT_RECEIPIENT";
	public static final String SEND_PARAMETERS_PLACEHOLDER = "SEND_PARAMETERS";
	public static final String RECEIVE_PARAMETERS_PLACEHOLDER = "RECEIVE_PARAMETERS";
	
	private CCATemplates() {
		// private constructor to hide the implicit public one
	}
}

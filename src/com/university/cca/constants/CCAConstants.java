package com.university.cca.constants;

/**
 * All constants related to the CCA generation that should be used to generate CCA files.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CCAConstants {
	
	// CCA Generation constants
	public static final String MSG_RESPOND_TO_NULL = "null";
	public static final int CCA_STATUS_SUCCESS = 0;
	public static final int CCA_STATUS_FAILURE = 1;
	
	// CCA Templates files paths version 1
	public static final String AMBIENTS_DELIMITER_FILE_PATH 	  = "./files/cca_templates/ambients_delimiter.cca";
	public static final String SEND_RECEIVE_ONE_AMBIENT_FILE_PATH = "./files/cca_templates/send_receive_one_ambient.cca";
	public static final String RECEIVE_SEND_ONE_AMBIENT_FILE_PATH = "./files/cca_templates/receive_send_one_ambient.cca";
	
	// CCA Templates files paths version 2
	public static final String SEND_RECEIVE_AMBIENT_FILE_PATH 	  = "./files/cca_templates/send_receive_ambient.cca";
	public static final String SEND_TO_SIBLING_AMBIENT_FILE_PATH  = "./files/cca_templates/send_to_sibling.cca";
	public static final String RECEIVE_FROM_SIBLING_FILE_PATH	  = "./files/cca_templates/receive_from_sibling.cca";
	
	// CCA Templates placeholders
	public static final String AMBIENT_SENDER_PLACEHOLDER 	  = "AMBIENT_SENDER";
	public static final String AMBIENT_RECEIPIENT_PLACEHOLDER = "AMBIENT_RECEIPIENT";
	public static final String SEND_PARAMETERS_PLACEHOLDER 	  = "SEND_PARAMETERS";
	public static final String RECEIVE_PARAMETERS_PLACEHOLDER = "RECEIVE_PARAMETERS";
	
	public static final String SEND_RECEIVE_MESSAGES_PLACEHOLDER = "SEND_RECEIVE_MESSAGES_PLACEHOLDER";
	
	private CCAConstants() {
		// private constructor to hide the implicit public one
	}
}

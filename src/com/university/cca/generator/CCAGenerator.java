package com.university.cca.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that has the responsibility to generate the CCA file with the valid specific syntax
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CCAGenerator {
	
	private static final Logger logger = LoggerFactory.getLogger(CCAGenerator.class);
	private static final int CCA_STATUS_SUCCESS = 0;
	private static final int CCA_STATUS_FAILURE = 1;
	
	private CCAGenerator() {
		// Prevent creating an object of type CCAGenerator
	}
	
	// TODO
	public static int generate() {
		
		return CCA_STATUS_SUCCESS;
	}
}

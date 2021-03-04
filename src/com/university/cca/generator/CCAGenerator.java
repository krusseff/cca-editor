package com.university.cca.generator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.entities.Ambient;
import com.university.cca.entities.Message;
import com.university.cca.files.csv.AmbientCSVReader;

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
	
	public static int generate() {
		List<Ambient> ambients = AmbientCSVReader.getAllAmbients();
		List<Message> messages = AmbientCSVReader.getAllMessages();
		
		return CCA_STATUS_FAILURE;
	}
}

package com.university.cca.files.csv;

import java.io.File;
import java.io.IOException;

import com.university.caa.entities.Ambient;

/**
 * Utility methods related to the functionality that reads and writes from/to CSV file.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientCsvUtil {
	
	private AmbientCsvUtil() {
		// Prevent creating an object of type CreateAmbientUtil
	}
	
	/**
	 * Method that creates a csv file if it does not exist.
	 */
	public static void createFileIfDoesNotExist(String filePath) {
		File file = new File(filePath);
		
		if (!file.exists()) {
			try {
				boolean result = file.createNewFile();
				System.out.println("Ambients CSV file: " + filePath + " is created with status: " + result);
			} catch (IOException e) {
				System.err.println("Unable to create CSV file: " + e.getMessage());
				System.err.println("Exiting the program...");
				System.exit(2);
			}
		} else {
			System.out.println("File: " + filePath + " already exists.");
		}	
	}
	
	/**
	 * Method that converts from CsvAmbientBean object to Ambient object.
	 */
	public static Ambient convertToAmbient(CsvAmbientBean ambientBean) {
		Ambient ambient = new Ambient();
		ambient.setName(ambientBean.getName());
		ambient.setLocation(ambientBean.getLocation());
		ambient.setStaticAmbient(ambientBean.isStaticAmbient());
		ambient.setParentAmbient(ambientBean.getParentAmbient());
		
		return ambient;
	}
	
	/**
	 * Method that converts from Ambient object to CsvAmbientBean object.
	 */
	public static CsvAmbientBean convertToCsvAmbientBean(Ambient ambient) {
		CsvAmbientBean ambientBean = new CsvAmbientBean();
		ambientBean.setName(ambient.getName());
		ambientBean.setLocation(ambient.getLocation());
		ambientBean.setStaticAmbient(ambient.isStaticAmbient());
		ambientBean.setParentAmbient(ambient.getParentAmbient());
		
		return ambientBean;
	}
}

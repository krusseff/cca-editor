package com.university.cca.generator;

public class GeneratorUtil {
	
	private static final String CSV_DELIMITER = ",";

	private GeneratorUtil() {
		// Prevent creating an object of type GeneratorUtil
	}

	/**
	 * Replaces all occurrences of the old value with the new value.
	 */
	protected static String replace(String originalString, 
									String stringOldValue, 
									String stringNewValue) {
		
		return originalString.replace(stringOldValue, stringNewValue);
	}
	
	/**
	 * Converts two string values in one in CSV format.
	 */
	protected static String toCSVFormat(String firstValue,
										String secondValue) {
		
		StringBuilder stringBuilder = new StringBuilder();  
		stringBuilder.append(firstValue);
		stringBuilder.append(CSV_DELIMITER); 
		stringBuilder.append(secondValue); 
		
		return stringBuilder.toString();
	}
}

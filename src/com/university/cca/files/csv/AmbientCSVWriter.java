package com.university.cca.files.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.university.caa.entities.Ambient;
import com.university.cca.constants.Constants;

/**
 * Utility methods related to the writting an ambient information to the CSV file.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientCSVWriter {
	
	private AmbientCSVWriter() {
		// Prevent creating an object of type AmbientCSVWriter
	}
	
	/**
	 * Method, which main responsibility is to write ambients to the csv file.
	 */
	public static void writeAmbientToCsv(Ambient ambient) {
		String filePath = Constants.AMBIENTS_CSV_FILE_PATH;
		AmbientCsvUtil.createFileIfDoesNotExist(filePath);
		Path path = Paths.get(filePath);

		try {
			writeCsvFromBean(path, AmbientCsvUtil.convertToCsvAmbientBean(ambient));
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
			System.err.println("Unable to write the data to the CSV file: " + e.getMessage());
			System.err.println("Exiting the program...");
			System.exit(2);
		}
	}

	/**
	 * Method, which main responsibility is to write to the csv file.
	 */
	private static <T> void writeCsvFromBean(Path path, T item) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
	    boolean isAppendingAllowed = true;
		Writer writer = new FileWriter(path.toString(), isAppendingAllowed);

	    StatefulBeanToCsv<T> sbc = new StatefulBeanToCsvBuilder<T>(writer)
	       .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
	       .build();

	    sbc.write(item);
	    writer.close();
	}
}

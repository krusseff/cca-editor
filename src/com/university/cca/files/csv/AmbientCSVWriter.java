package com.university.cca.files.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.university.cca.constants.Constants;
import com.university.cca.entities.Ambient;
import com.university.cca.entities.Message;

/**
 * Utility methods related to the writting an ambient information to the CSV file.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientCSVWriter {
	
	private static final Logger logger = LoggerFactory.getLogger(AmbientCSVWriter.class);
	
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
			logger.error("Unable to write the data to the CSV file: {}", e.getMessage());
			logger.error("Exiting the program... :(");
			System.exit(2);
		}
	}
	
	/**
	 * Method, which main responsibility is to write ambient messages to the csv file.
	 */
	public static void writeMessageToCsv(Message ambientMessage) {
		String filePath = Constants.MESSAGES_CSV_FILE_PATH;
		AmbientCsvUtil.createFileIfDoesNotExist(filePath);
		Path path = Paths.get(filePath);

		try {
			writeCsvFromBean(path, AmbientCsvUtil.convertToCsvMessageBean(ambientMessage));
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
			logger.error("Unable to write the data to the CSV file: {}", e.getMessage());
			logger.error("Exiting the program... :(");
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

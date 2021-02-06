package com.university.cca.files.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
		// Prevent creating an object of type CreateAmbientUtil
	}
	
	/**
	 * Method, which main responsibility is to write ambients to the csv file.
	 */
	public static void writeAmbientToCsv(Ambient ambient) {
		CsvAmbientBean ambientBean = AmbientCsvUtil.convertToCsvAmbientBean(ambient);
		List<CsvAmbientBean> ambientBeans = new ArrayList<>();
		ambientBeans.add(ambientBean);
		
		String filePath = Constants.AMBIENTS_CSV_FILE_PATH;
		
		AmbientCsvUtil.createFileIfDoesNotExist(filePath);
		
		Path path = Paths.get(filePath);

		try {
			writeCsvFromBean(path, ambientBeans);
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
			System.err.println("Unable to write the data to the CSV file: " + e.getMessage());
			System.err.println("Exiting the program...");
			System.exit(2);
		}
	}

	/**
	 * Method, which main responsibility is to write to the csv file.
	 */
	private static <T> void writeCsvFromBean(Path path, List<T> items) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
	    Writer writer  = new FileWriter(path.toString());

	    StatefulBeanToCsv<T> sbc = new StatefulBeanToCsvBuilder<T>(writer)
	       .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
	       .build();

	    sbc.write(items);
	    writer.close();
	}
}

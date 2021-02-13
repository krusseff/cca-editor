package com.university.cca.files.csv;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.university.cca.constants.Constants;

/**
 * Utility methods related to the reading an ambient information from the CSV file.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientCSVReader {

	private AmbientCSVReader() {
		// Prevent creating an object of type AmbientCSVReader
	}
	
	/**
	 * Method, which main responsibility is to read ambient names from the csv file as an array.
	 */
	public static String[] getAmbientNamesSorted() {
		List<String> ambients = readAmbientNamesFromCsv();
		List<String> sortedAmbients = ambients.stream().sorted().collect(Collectors.toList());
		
		String[] ambientNames = new String[sortedAmbients.size()];
		
		return sortedAmbients.toArray(ambientNames);
	}
	
	/**
	 * Method, which main responsibility is to read ambient names from the csv file.
	 */
	public static List<String> readAmbientNamesFromCsv() {
		List<CsvAmbientBean> ambients = readAmbientsFromCsv();
		List<String> parentAmbientNames = new ArrayList<>();
		
		for (CsvAmbientBean ambient : ambients) {
			parentAmbientNames.add(ambient.getName());
		}
		
		return parentAmbientNames;
	}
	
	/**
	 * Method, which main responsibility is to read ambients from the csv file.
	 */
	public static List<CsvAmbientBean> readAmbientsFromCsv() {
		List<CsvAmbientBean> ambientBeans;
		
		String filePath = Constants.AMBIENTS_CSV_FILE_PATH;
		Path path = Paths.get(filePath);

		try {
			ambientBeans = readCsvToBean(path, CsvAmbientBean.class);
		} catch (IOException e) {
			System.err.println("Unable to read the data from the CSV file: " + e.getMessage());
			ambientBeans = new ArrayList<>();
		}
		
		return ambientBeans;
	}
	
	/**
	 * Method, which main responsibility is to read ambient messages from the csv file.
	 */
	public static List<CsvMessageBean> readAmbientMessagesFromCsv() {
		List<CsvMessageBean> messageBeans;
		
		String filePath = Constants.MESSAGES_CSV_FILE_PATH;
		Path path = Paths.get(filePath);

		try {
			messageBeans = readCsvToBean(path, CsvMessageBean.class);
		} catch (IOException e) {
			System.err.println("Unable to read the data from the CSV file: " + e.getMessage());
			messageBeans = new ArrayList<>();
		}
		
		return messageBeans;
	}

	/**
	 * Method, which main responsibility is to read from the csv file.
	 */
	private static <T> List<T> readCsvToBean(Path path, Class<T> clazz) throws IOException {
		ColumnPositionMappingStrategy<T> ms = new ColumnPositionMappingStrategy<>();
		ms.setType(clazz);

		Reader reader = Files.newBufferedReader(path);
		CsvToBean<T> cb = new CsvToBeanBuilder<T>(reader)
			.withType(clazz)
			.withMappingStrategy(ms)
			.build();

		List<T> items = cb.parse();
		reader.close();
		return items;
	}
}

package com.university.cca.files.csv;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.university.cca.constants.Constants;
import com.university.cca.entities.Ambient;
import com.university.cca.entities.Message;

/**
 * Utility methods related to the reading an ambient information from the CSV file.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientCSVReader {
	
	private static final Logger logger = LoggerFactory.getLogger(AmbientCSVReader.class);

	private AmbientCSVReader() {
		// Prevent creating an object of type AmbientCSVReader
	}
	
	/**
	 * Method, which main responsibility is to fetch all ambients from the csv file.
	 */
	public static List<Ambient> getAllAmbients() {
		List<CsvAmbientBean> ambientBeans = readAmbientsFromCsv();
		List<Ambient> ambients = new ArrayList<>();
		
		for (CsvAmbientBean ambientBean : ambientBeans) {
			Ambient ambient = AmbientCsvUtil.convertToAmbient(ambientBean);
			ambients.add(ambient);
		}
		
		return ambients;
	}
	
	/**
	 * Method, which main responsibility is to fetch all ambient messages from the csv file.
	 */
	public static List<Message> getAllMessages() {
		List<CsvMessageBean> messageBeans = readAmbientMessagesFromCsv();
		List<Message> messages = new ArrayList<>();
		
		for (CsvMessageBean messageBean : messageBeans) {
			Message message = AmbientCsvUtil.convertToMessage(messageBean);
			messages.add(message);
		}
		
		return messages;
	}
	
	/**
	 * Method, which main responsibility is to read ambients from the csv file
	 */
	private static List<CsvAmbientBean> readAmbientsFromCsv() {
		List<CsvAmbientBean> ambientBeans;
		
		String filePath = Constants.AMBIENTS_CSV_FILE_PATH;
		Path path = Paths.get(filePath);

		try {
			ambientBeans = readCsvToBean(path, CsvAmbientBean.class);
		} catch (IOException ex) {
			logger.error("Unable to read the data from the CSV file: {}", ex.getMessage());
			ambientBeans = new ArrayList<>();
		}
		
		return ambientBeans;
	}
	
	/**
	 * Method, which main responsibility is to read ambient messages from the csv file
	 */
	private static List<CsvMessageBean> readAmbientMessagesFromCsv() {
		List<CsvMessageBean> messageBeans;
		
		String filePath = Constants.MESSAGES_CSV_FILE_PATH;
		Path path = Paths.get(filePath);

		try {
			messageBeans = readCsvToBean(path, CsvMessageBean.class);
		} catch (IOException ex) {
			logger.error("Unable to read the data from the CSV file: {}", ex.getMessage());
			messageBeans = new ArrayList<>();
		}
		
		return messageBeans;
	}

	/**
	 * Method, which main responsibility is to read from the csv file
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

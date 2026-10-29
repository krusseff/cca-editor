package com.university.cca.files.csv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.university.cca.constants.Constants;
import com.university.cca.entities.Ambient;
import com.university.cca.entities.AmbientStatistics;
import com.university.cca.entities.Message;
import com.university.cca.entities.MessageStatistics;

/**
 * Utility methods related to the writting an ambient information to the CSV file.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientCSVWriter {
	
	private static final Logger logger = LoggerFactory.getLogger(AmbientCSVWriter.class);
	
	private static final String NEW_LINE   = "\n";
	
	private AmbientCSVWriter() {
		// Prevent creating an object of type AmbientCSVWriter
	}
	
	/**
	 * Method, which main responsibility is to rewrite the whole CSV file with the newly provided data.
	 * 
	 * @param data		the data that will be stored into the CSV file.
	 * @param filePath	the file that will be populated with the <code>data</code>.
	 */
	public static void writeToCsv(String data, String filePath) {
		boolean shouldAppendToFile = false;
		CsvUtil.createFileIfDoesNotExist(filePath);
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, shouldAppendToFile))) {
			writer.write(data);
			writer.write(NEW_LINE);
			logger.info("The data: \n{} \nis written successfully to the CSV file: {}", data, filePath);
		} catch (IOException ex) {
			logger.error("Unable to write the data to the CSV file: {}", ex.getMessage());
			throw new RuntimeException("Unable to write data to CSV file: " + filePath, ex);
		}
	}
	
	/**
	 * Method, which main responsibility is to write ambients to a specified csv file.
	 */
	public static void writeAmbientsToCsv(String filePath, List<Ambient> ambients) {
		CsvUtil.createFileIfDoesNotExist(filePath);
		Path path = Paths.get(filePath);
		
		for (Ambient ambient : ambients) {
			try {
				writeCsvFromBean(path, CsvUtil.convertToCsvAmbientBean(ambient));
			} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException ex) {
				logger.error("Unable to write the ambient: {} to the CSV file: {}, Exception: {}", ambient, filePath, ex.getMessage());
				logger.error("Skip the record: {} and continue with the next one.", ambient);
			}
		}
	}

	/**
	 * Method, which main responsibility is to write ambient to the csv file.
	 */
	public static void writeAmbientToCsv(Ambient ambient) {
		writeAmbientToCsv(ambient, Constants.AMBIENTS_CSV_FILE_PATH);
	}

	/**
	 * Method, which main responsibility is to write ambient to the specified csv file.
	 */
	public static void writeAmbientToCsv(Ambient ambient, String filePath) {
		CsvUtil.createFileIfDoesNotExist(filePath);
		Path path = Paths.get(filePath);

		try {
			writeCsvFromBean(path, CsvUtil.convertToCsvAmbientBean(ambient));
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException ex) {
			logger.error("Unable to write the data to the CSV file: {}", ex.getMessage());
			throw new RuntimeException("Unable to write ambient to CSV file: " + filePath, ex);
		}
	}
	
	/**
	 * Method, which main responsibility is to write ambient messages to the specified csv file.
	 */
	public static void writeMessagesToCsv(String filePath, List<Message> messages) {
		CsvUtil.createFileIfDoesNotExist(filePath);
		Path path = Paths.get(filePath);

		for (Message message : messages) {
			try {
				writeCsvFromBean(path, CsvUtil.convertToCsvMessageBean(message));
			} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException ex) {
				logger.error("Unable to write the message: {} to the CSV file: {}", message, ex.getMessage());
				logger.error("Skip the record: {} and continue with the next one.", message);
			}
		}
	}
	
	/**
	 * Method, which main responsibility is to write ambient message to the csv file.
	 */
	public static void writeMessageToCsv(Message ambientMessage) {
		writeMessageToCsv(ambientMessage, Constants.MESSAGES_CSV_FILE_PATH);
	}

	/**
	 * Method, which main responsibility is to write ambient message to the specified csv file.
	 */
	public static void writeMessageToCsv(Message ambientMessage, String filePath) {
		CsvUtil.createFileIfDoesNotExist(filePath);
		Path path = Paths.get(filePath);

		try {
			writeCsvFromBean(path, CsvUtil.convertToCsvMessageBean(ambientMessage));
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException ex) {
			logger.error("Unable to write the message: {} to the CSV file: {}", ambientMessage, ex.getMessage());
			throw new RuntimeException("Unable to write message to CSV file: " + filePath, ex);
		}
	}
	
	/**
	 * Method, which main responsibility is to write ambient statistics to the csv file.
	 */
	public static void writeAmbientStatisticsToCsv(AmbientStatistics ambientStats, 
												   String filePath) {
		CsvUtil.createFileIfDoesNotExist(filePath);
		Path path = Paths.get(filePath);

		try {
			writeCsvFromBean(path, CsvUtil.convertToCsvAmbientStatisticsBean(ambientStats));
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException ex) {
			logger.error("Unable to write the data: {} to the CSV file: {}", ambientStats, ex.getMessage());
			throw new RuntimeException("Unable to write ambient statistics to CSV file: " + filePath, ex);
		}
	}
	
	/**
	 * Method, which main responsibility is to write message statistics to the csv file.
	 */
	public static void writeMessageStatisticsToCsv(List<MessageStatistics> statistics, 
												   String filePath) {
		CsvUtil.createFileIfDoesNotExist(filePath);
		Path path = Paths.get(filePath);

		try {
			for (MessageStatistics messageStats : statistics) {
				CsvMessageStatisticsBean bean = CsvUtil.convertToCsvMessageStatisticsBean(messageStats);
				writeCsvFromBean(path, bean);
			}
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException ex) {
			logger.error("Unable to write the data to the CSV file: {}", ex.getMessage());
			throw new RuntimeException("Unable to write message statistics to CSV file: " + filePath, ex);
		}
	}

	/**
	 * Method, which main responsibility is to write to the csv file.
	 */
	private static <T> void writeCsvFromBean(Path path, T item) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
	    boolean isAppendingAllowed = true;

	    try (Writer writer = new FileWriter(path.toString(), isAppendingAllowed)) {
		    StatefulBeanToCsv<T> sbc = new StatefulBeanToCsvBuilder<T>(writer)
		       .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
		       .build();

		    sbc.write(item);
	    }
	}
}

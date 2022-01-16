package com.university.cca.generator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.CCAConstants;
import com.university.cca.entities.Message;
import com.university.cca.exceptions.AmbientMessageNotFoundException;
import com.university.cca.files.cca.TemplateCCAReader;
import com.university.cca.files.csv.AmbientCSVReader;
import com.university.cca.repositories.AmbientRepository;

/**
 * Version 2 of the class that has the responsibility to generate the CCA file
 * 
 * @author Konstantin Rusev
 * @version 2.0
 */
public class CCAGeneratorV2 {

	private static final Logger logger = LoggerFactory.getLogger(CCAGeneratorV2.class);

	private CCAGeneratorV2() {
		// Prevent creating an object of type CCAGeneratorV2 class
	}

	// TODO
	// разместване на амбиентите
	// някъде се разместват и се губи оригиналната подредба
	// може да ми трябва sorted map или tree map за да са сортирани елементите в мап-а?
	public static int generate() {
		StringBuilder ccaBuilder = new StringBuilder();
		String ambientDelimiter = TemplateCCAReader.readAmbientDelimiterTemplate();
		List<Message> activeMessages = GeneratorUtil.getMessagesActiveAmbients(AmbientCSVReader.getAllMessages());
		
		// TODO
		// Може би в мап-а има нужда само от съобщенията, които са изпратени от
		// амбиента, който е key в мап-а
		Map<String, List<Message>> messagesByAmbient = getMessagesByAmbient(activeMessages);
		
		for(Map.Entry<String, List<Message>> entry : messagesByAmbient.entrySet()) {
			String ambientKey = entry.getKey();
			List<Message> messages =  entry.getValue();
		    
			StringBuilder builderSend = new StringBuilder();
			StringBuilder builderReceive = new StringBuilder();
			
			StringBuilder builderReceiveSend = new StringBuilder();
			
			// на всяка итерация ще апендвам към двата билдъра
			for (Message message : messages) {
				
				if (GeneratorUtil.isSender(message.getSenderAmbient(), ambientKey, message.getRespondToMessage(), message.getPassMessageTo())) {
			
					try {
						// TODO: Може би active messages да се замени само с messages
						Message recipientMsg = GeneratorUtil.findMessageRecipient(activeMessages, message);
					
						Message convertedMsg = GeneratorUtil.replaceUnsupportedChars(message);
						
						String receiveParameters = GeneratorUtil.toCSVFormat(
							GeneratorUtil.replaceUnsupportedChars(recipientMsg.getRespondToMessage()),
							GeneratorUtil.replaceUnsupportedChars(recipientMsg.getMessage())
						);

						String sendSiblingTemplate = TemplateCCAReader.readSendSiblingAmbientTemplate();
						String receiveSiblingTemplate = TemplateCCAReader.readReceiveSiblingAmbientTemplate();
						
						String sendSibling = sendSiblingTemplate
							.replace(CCAConstants.AMBIENT_RECEIPIENT_PLACEHOLDER, convertedMsg.getRecipientAmbient())
							.replace(CCAConstants.SEND_PARAMETERS_PLACEHOLDER, convertedMsg.getMessage());
						
						// TODO: тук може да трябва да апендвам само към сендър билдъра
						// а ресийв апендъра да остане за else if() частта
						// т.е. всичко да се апендва към сендър билдъра
						builderSend.append(sendSibling).append(ambientDelimiter);
						
						String receiveSibling = receiveSiblingTemplate
							.replace(CCAConstants.AMBIENT_RECEIPIENT_PLACEHOLDER, convertedMsg.getRecipientAmbient())
							.replace(CCAConstants.RECEIVE_PARAMETERS_PLACEHOLDER, receiveParameters);
						 
						builderReceive.append(receiveSibling).append(ambientDelimiter);

					} catch (AmbientMessageNotFoundException ex) {
						logger.error("CCA Generator V2: An error occurred during the CCA generation. Ambient: {}, Message: {}, Exception: {}", ambientKey, message, ex.getMessage());
						return CCAConstants.CCA_STATUS_FAILURE;
					}

				} else if (GeneratorUtil.isRecipient(message.getSenderAmbient(), ambientKey, message.getRespondToMessage(), message.getPassMessageTo())) {
					
					// TODO: помисли да изкараш четенията най-горе
					// за да не се чете всеки път
					String receiveSiblingTemplate = TemplateCCAReader.readReceiveSiblingTemplate();
					String sendSiblingTemplate = TemplateCCAReader.readSendSiblingTemplate();
					
					Message convertedMsg = GeneratorUtil.replaceUnsupportedChars(message);

					String receiveParameters = GeneratorUtil.toCSVFormat(
						convertedMsg.getRespondToMessage(), 
						convertedMsg.getMessage()
					);
					
					String receiveSibling = receiveSiblingTemplate
						.replace(CCAConstants.AMBIENT_RECEIPIENT_PLACEHOLDER, convertedMsg.getRecipientAmbient())
						.replace(CCAConstants.SEND_PARAMETERS_PLACEHOLDER, convertedMsg.getRespondToMessage());
					
					String sendSibling = sendSiblingTemplate
						.replace(CCAConstants.AMBIENT_RECEIPIENT_PLACEHOLDER, convertedMsg.getRecipientAmbient())
						.replace(CCAConstants.RECEIVE_PARAMETERS_PLACEHOLDER, receiveParameters);
					
					builderReceiveSend.append(receiveSibling).append(sendSibling);
				
				} else if (GeneratorUtil.hasPassMsgTo(message.getSenderAmbient(), ambientKey, message.getPassMessageTo())) {
					
					// TODO
					logger.info("PASS MESSAGE TO IS NOT NULL FOR MESSAGE: {}", message);
				
				} else {
					logger.error("CCA Generator V2: Unsupported ambient operation! Ambient: {}, Message: {}", ambientKey, message);
				}
			} // end for loop
			
			StringBuilder builderCombine = new StringBuilder();
			builderCombine.append(builderSend).append(builderReceive)
				.append(builderReceiveSend);
			
			// TODO: помисли къде да преместиш: remove last char functionality
			// защото в началото премахваше от последния "builderReceive"
			// но след това добавих ".append(builderReceiveSend)"
			String messagesExchange = GeneratorUtil.removeLastChar(builderCombine.toString(), ambientDelimiter);
			
			String exchangeMessagesTemplate = TemplateCCAReader.readExchangeMessagesTemplate();
			String ambientSender = GeneratorUtil.replaceUnsupportedChars(ambientKey);
			String ambientStructure = exchangeMessagesTemplate
				.replace(CCAConstants.AMBIENT_SENDER_PLACEHOLDER, ambientSender)
				.replace(CCAConstants.MESSAGES_PLACEHOLDER, messagesExchange);

			ccaBuilder.append(ambientStructure).append(ambientDelimiter);
		} // end map for loop
		
		// remove last character if it's ambient delimeter, i.e. "|"
		String ccaScenario = GeneratorUtil.removeLastChar(ccaBuilder.toString(), ambientDelimiter);
		
		logger.info("CCA file generation result: {}", ccaScenario);
		
		// TODO: write to file
		// AmbientCCAWriter.write(ccaScenario);	
		
		return CCAConstants.CCA_STATUS_SUCCESS;
	}
	
	/**
	 * Returns a map with all active ambients that have messages.
	 * Ambients that are not active or do not have messages will not be included.
	 * Key 	 - active ambient name that has messages.
	 * Value - list of messages for this key (ambient name).
	 */
	private static Map<String, List<Message>> getMessagesByAmbient(List<Message> messages) {
		Map<String, List<Message>> messagesByAmbient = new HashMap<>();
		List<String> activeAmbients = AmbientRepository.getActiveAmbientNames();
		List<String> activeAmbientsWithMessages = activeAmbients.stream()
			.filter(ambient -> GeneratorUtil.hasMessages(messages, ambient))
			.collect(Collectors.toList());
		
		for (String ambient : activeAmbientsWithMessages) {
			List<Message> ambientMessages = GeneratorUtil.getMessagesByAmbient(ambient, messages);
			
			messagesByAmbient.putIfAbsent(ambient, ambientMessages);
		}
		
		return messagesByAmbient;
	}
}

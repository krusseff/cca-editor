package com.university.cca.generator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.university.cca.constants.CCAConstants;
import com.university.cca.entities.Message;
import com.university.cca.exceptions.AmbientMessageNotFoundException;
import com.university.cca.files.cca.AmbientCCAWriter;
import com.university.cca.files.cca.TemplateCCAReader;
import com.university.cca.files.csv.AmbientCSVReader;

/**
 * Class that has the responsibility to generate the CCA file (Version 1) with the valid specific syntax
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CCAGeneratorV1 {

	private static final Logger logger = LoggerFactory.getLogger(CCAGeneratorV1.class);
	
	private CCAGeneratorV1() {
		// Prevent creating an object of type CCAGeneratorV1 class
	}
	
	public static int generate() {
		StringBuilder builder = new StringBuilder();
		List<Message> allMessages = AmbientCSVReader.getAllMessages();
		List<Message> messages = GeneratorUtil.getMessagesActiveAmbients(allMessages);

		for (Message message : messages) {
			logger.info("Ambient Message retrieved: {}", message);
			
			if (message.getRespondToMessage().equalsIgnoreCase(CCAConstants.MSG_RESPOND_TO_NULL)) {
				
				try {
					
					// process send-receive one ambient template	
					String sendReceiveOneAmbient = generateSendReceiveOneAmbient(messages, message);
					logger.info("Send-Receive One Ambient CCA Item generated: {}", sendReceiveOneAmbient);
					builder.append(sendReceiveOneAmbient);
				} catch (AmbientMessageNotFoundException ex) {
					logger.error("An error occurred during the generation of the CCA file: {}", ex);
					return CCAConstants.CCA_STATUS_FAILURE;
				}
				
			} else if (!message.getRespondToMessage().equalsIgnoreCase(CCAConstants.MSG_RESPOND_TO_NULL)) {

				// process receive-send one ambient template
				String receiveSendOneAmbient = generateReceiveSendOneAmbient(message);
				logger.info("Receive-Send One Ambient CCA Item generated: {}", receiveSendOneAmbient);
				builder.append(receiveSendOneAmbient);
			} else {
				// Do nothing here, at least for now
				logger.error("CCA Generator V1: Unsupported ambient operation has found!");
			}
			
			// Do not add an ambient delimiter after the last element
			if (GeneratorUtil.isLastAmbientMessage(messages, message)) {
				builder.append(TemplateCCAReader.readAmbientDelimiterTemplate());
			}
		}
		
		AmbientCCAWriter.write(builder.toString());		
		return CCAConstants.CCA_STATUS_SUCCESS;
	}
	
	/**
	 * Generate send-receive one ambient based on the send_receive_one_ambient.cca template
	 */
	private static String generateSendReceiveOneAmbient(List<Message> messages, 
														Message ambientMessage) throws AmbientMessageNotFoundException {
		String sendReceiveOneAmbientTemplate = TemplateCCAReader.readSendReceiveOneAmbientTemplate();
		
		Message recipientMsg = GeneratorUtil.findMessageRecipient(messages, ambientMessage);

		Message convertedMsg = GeneratorUtil.replaceUnsupportedChars(ambientMessage);
	
		String receiveParameters = GeneratorUtil.toCSVFormat(
			GeneratorUtil.replaceUnsupportedChars(recipientMsg.getRespondToMessage()),
			GeneratorUtil.replaceUnsupportedChars(recipientMsg.getMessage())
		);
		
		return sendReceiveOneAmbientTemplate
			.replace(CCAConstants.AMBIENT_SENDER_PLACEHOLDER, convertedMsg.getSenderAmbient())
			.replace(CCAConstants.AMBIENT_RECEIPIENT_PLACEHOLDER, convertedMsg.getRecipientAmbient())
			.replace(CCAConstants.SEND_PARAMETERS_PLACEHOLDER, convertedMsg.getMessage())
			.replace(CCAConstants.RECEIVE_PARAMETERS_PLACEHOLDER, receiveParameters);
	}

	/**
	 * Generate receive-send one ambient based on the receive_send_one_ambient.cca template
	 */
	private static String generateReceiveSendOneAmbient(Message ambientMessage) {
		String receiveSendOneAmbientTemplate = TemplateCCAReader.readReceiveSendOneAmbientTemplate();
		
		Message message = GeneratorUtil.replaceUnsupportedChars(ambientMessage);
		
		String receiveParameters = GeneratorUtil.toCSVFormat(
			message.getRespondToMessage(), 
			message.getMessage()
		);
		
		return receiveSendOneAmbientTemplate
			.replace(CCAConstants.AMBIENT_SENDER_PLACEHOLDER, message.getSenderAmbient())
			.replace(CCAConstants.AMBIENT_RECEIPIENT_PLACEHOLDER, message.getRecipientAmbient())
			.replace(CCAConstants.SEND_PARAMETERS_PLACEHOLDER, message.getRespondToMessage())
			.replace(CCAConstants.RECEIVE_PARAMETERS_PLACEHOLDER, receiveParameters);
	}
}

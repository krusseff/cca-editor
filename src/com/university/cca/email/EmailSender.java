package com.university.cca.email;

import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailSender {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);

	private static final int EMAIL_SUCCESS_STATUS = 0;
	private static final int EMAIL_VALIDATION_FAILED_STATUS = 1;
	private static final int EMAIL_FAILED_STATUS = 2;
	
	private static final String CONTACT_EMAIL_SUBJECT = "CCA Editor Contact Us Form Reply";
	private static final String EMAIL_CONTENT_TYPE = "text/html; charset=utf-8";
	
	private static final EmailConfiguration emailConfig = EmailConfigReader.getEmailConfigurations();
	
	private EmailSender() {
		// prevent creating an object of type EmailSender
	}
	
	public static int send(String name, String email, String message) {
		int emailResultStatus;
		
		if (EmailValidator.isValidValues(name, email, message)) {
			logger.info("Valid values are passed to the contact us form");
			emailResultStatus = sendEmail(name, email, message);
		} else {
			logger.info("Invalid values are passed to the contact us form");
			emailResultStatus = EMAIL_VALIDATION_FAILED_STATUS;
		}
		
		logger.info("Tried to send an email. Exit status: {}", emailResultStatus);
		return emailResultStatus;
	}
	
    private static int sendEmail(String name, String email, String message) {
    	
    	if (emailConfig == null) {
    		logger.error("The email configuration class has a value of NULL. Not able to send an email.");
    		return EMAIL_FAILED_STATUS;
    	}
    	
    	Session session = EmailConfigUtil.getSession(emailConfig);
    	Message mimeMessage = new MimeMessage(session);
    	
    	try {
    		mimeMessage.setFrom(new InternetAddress(emailConfig.getSenderEmail()));
	    	
    		mimeMessage.addRecipient(
        		Message.RecipientType.TO, 
        		new InternetAddress(emailConfig.getRecipientEmail())
    		);
            
    		mimeMessage.addRecipient(
        		Message.RecipientType.BCC,
        		new InternetAddress(emailConfig.getRecipientBccEmail())
            );

    		mimeMessage.setSentDate(new Date());
    		mimeMessage.setSubject(CONTACT_EMAIL_SUBJECT);
	    	mimeMessage.setContent(constructEmailContent(name, email, message));
	
	    	Transport.send(mimeMessage);
	    	
	    	logger.info("Contact us form email is sent successfully");
	    	return EMAIL_SUCCESS_STATUS;
    	} catch (MessagingException ex) {
        	logger.error("Unexpected error occurred during the email sending with an error: {}", ex.getMessage());
        	return EMAIL_FAILED_STATUS;
        }
    }
    
    private static Multipart constructEmailContent(String name, 
    											   String email, 
    											   String message) throws MessagingException {
    	String htmlContent = constructHtmlContent(name, email, message);
    	
    	MimeBodyPart mimeBodyPart = new MimeBodyPart();
    	mimeBodyPart.setContent(htmlContent, EMAIL_CONTENT_TYPE);

    	Multipart multipart = new MimeMultipart();
    	multipart.addBodyPart(mimeBodyPart);
    	
    	return multipart;
    }
    
    private static String constructHtmlContent(String name, String email, String message) {
    	String headerEmail = 
			"<div>" + 
				"<p> Dear, <b>CCA Editor Administrator</b>, </p>" +
			"</div>" +
				
			"<div>" + 
				"<p> A CCA Editor user sent an email to you via the <b>Contact Us Form</b> from the application with the following details. </p>" +
			"</div>";
    	
		String contentEmail = 
			"<div style=\"border-style: ridge; padding-left: 5px; padding-right: 5px;\">" +
				"<div>" + 
					"<p> Name: <b>" + name + "</b> </p>" +
				"</div>" +
					
				"<div>" + 
					"<p> Email: <u><i>" + email + "</i></u> </p>" +
				"</div>" +
					
				"<div style=\"word-wrap: break-word;\">" + 
					"<p> Message: <i>" + message + "</i> </p>" +
				"</div>" +
			"</div>";
		
		String footerEmail =
			"<div>" + 
				"<p> Please <b>do not reply</b> to this email. Replies to this email are routed to an unmonitored mailbox. If you want to contact the user, please use they email address provided above. </p>" +
			"</div>" +
				
			"<div>" + 
				"<p> Best Regards, <br/> <b>CCA Editor</b> </p>" +
			"</div>";
		
		StringBuilder emailStringBuilder = new StringBuilder();
		emailStringBuilder.append(headerEmail);
		emailStringBuilder.append(contentEmail);
		emailStringBuilder.append(footerEmail);

    	return emailStringBuilder.toString();
    }
}

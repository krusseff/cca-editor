package com.university.cca.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class EmailConfigUtil {

	private EmailConfigUtil() {
		// Private constructor to prevent creation of objects with type EmailConfigUtil
	}
	
	public static Session getSession(EmailConfiguration emailConfig) {
		Properties properties = getProperties(emailConfig);
		String senderEmail = properties.getProperty("mail.smtp.user");
		String senderPass = properties.getProperty("mail.smtp.password");
		
		return Session.getInstance(properties, new Authenticator() {
			
    	    @Override
    	    protected PasswordAuthentication getPasswordAuthentication() {
    	        return new PasswordAuthentication(senderEmail, senderPass);
    	    }
    	});
	}
	
	private static Properties getProperties(EmailConfiguration emailConfig) {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", emailConfig.getSmtpServerTls());
        properties.put("mail.smtp.host", emailConfig.getSmtpServerHost());
        properties.put("mail.smtp.user", emailConfig.getSenderEmail());
        properties.put("mail.smtp.password", emailConfig.getSenderEmailPass());
        properties.put("mail.smtp.port", emailConfig.getSmtpServerPort());
        properties.put("mail.smtp.auth", emailConfig.getSmtpServerAuth());
        
        return properties;
	}
}

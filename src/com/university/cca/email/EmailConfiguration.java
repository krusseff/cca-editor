package com.university.cca.email;

/**
 * Class that has the responsibility to hold all email configurations
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class EmailConfiguration {
	
	private String senderEmail;
	private String senderEmailPass;
	private String recipientEmail;
	private String recipientBccEmail;
	private String smtpServerHost;
	private String smtpServerTls;
	private String smtpServerAuth;
	private String smtpServerPort;
	
	public EmailConfiguration() {
		// Default constructor of class EmailConfiguration
	}
	
	@Override
	public String toString() {
		return "EmailConfiguration [senderEmail=" + senderEmail + ", senderEmailPass=" + senderEmailPass
			+ ", recipientEmail=" + recipientEmail + ", recipientBccEmail=" + recipientBccEmail
			+ ", smtpServerHost=" + smtpServerHost + ", smtpServerTls=" + smtpServerTls + ", smtpServerAuth="
			+ smtpServerAuth + ", smtpServerPort=" + smtpServerPort + "]";
	}

	// Getters and Setters
	public String getSenderEmail() {
		return senderEmail;
	}
	
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	
	public String getSenderEmailPass() {
		return senderEmailPass;
	}
	
	public void setSenderEmailPass(String senderEmailPass) {
		this.senderEmailPass = senderEmailPass;
	}
	
	public String getRecipientEmail() {
		return recipientEmail;
	}
	
	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}
	
	public String getRecipientBccEmail() {
		return recipientBccEmail;
	}
	
	public void setRecipientBccEmail(String recipientBccEmail) {
		this.recipientBccEmail = recipientBccEmail;
	}
	
	public String getSmtpServerHost() {
		return smtpServerHost;
	}
	
	public void setSmtpServerHost(String smtpServerHost) {
		this.smtpServerHost = smtpServerHost;
	}
	
	public String getSmtpServerTls() {
		return smtpServerTls;
	}
	
	public void setSmtpServerTls(String smtpServerTls) {
		this.smtpServerTls = smtpServerTls;
	}
	
	public String getSmtpServerAuth() {
		return smtpServerAuth;
	}
	
	public void setSmtpServerAuth(String smtpServerAuth) {
		this.smtpServerAuth = smtpServerAuth;
	}
	
	public String getSmtpServerPort() {
		return smtpServerPort;
	}

	public void setSmtpServerPort(String smtpServerPort) {
		this.smtpServerPort = smtpServerPort;
	}
}

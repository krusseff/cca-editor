package com.university.cca.email;

import java.util.regex.Pattern;

public class EmailValidator {
	
	private static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	private static final int MIN_NAME_SIZE = 0;
	private static final int MAX_NAME_SIZE = 100;
	private static final int MIN_MESSAGE_SIZE = 0;
	private static final int MAX_MESSAGE_SIZE = 1000;
	
	private EmailValidator() {
		// prevent creating an object of type EmailValidator
	}
	
	public static boolean isValidValues(String name, String email, String message) {
		boolean isValidName = isValidName(name);
		boolean isValidEmail = isValidEmail(email);
		boolean isValidMessage = isValidMessage(message);
		
		return isValidName && isValidEmail && isValidMessage;
	}
	
	private static boolean isValidName(String name) {
		return name != null && 
				!name.isEmpty() &&
				(name.length() > MIN_NAME_SIZE && name.length() <= MAX_NAME_SIZE);
	}
	
	private static boolean isValidEmail(String email) {
		Pattern emailPattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
		
		return email != null && 
				!email.isEmpty() &&
				emailPattern.matcher(email).matches();
	}	

	private static boolean isValidMessage(String message) {
		return message != null && 
				!message.isEmpty() && 
				(message.length() > MIN_MESSAGE_SIZE && message.length() <= MAX_MESSAGE_SIZE);
	}
}

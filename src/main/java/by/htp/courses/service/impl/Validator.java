package by.htp.courses.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Validator {	
	
	private static Pattern pattern;
	private static Matcher matcher;

	private static final Logger logger = LoggerFactory.getLogger(Validator.class);

	private List<String> errors = new ArrayList<>();

	public List<String> getErrors() {
		return errors;
	}

	private static final String PATTERN_PASSWORD = "^[1-9a-zA-Z]{5,15}$";
	private static final String PATTERN_EMAIL = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
	private static final String PATTERN_LOGIN = "^[A-Za-z0-9_]{3,15}$";
	private static final String PATTERN_NAME = "^[a-zA-Zа-яА-ЯёЁ ]{2,25}$";

	public boolean hasValidationErrors() {
		return !errors.isEmpty();
	}

	public void addError(String error) {
		errors.add(error);
	}

	public static boolean validateLengthPassword(String password) {
		return password.length() > 5;
	}

	public static boolean validateInt(int value) {
		return value > 0;
	}

	public static boolean validateString(String value) {
		logger.debug("Validate String isEmpty");
		return !(value == null || value.isEmpty());
	}

	public static boolean validateLogin(String login) {
		logger.info("Validate Login: " + login);
		if (!validateString(login)) {
			return false;
		}
		pattern = Pattern.compile(PATTERN_LOGIN);
		matcher = pattern.matcher(login);
		logger.info("Validate Login:  {} " + matcher.matches());
		return matcher.matches();
	}

	public static boolean validateName(String name) {
		logger.debug("Validate Name: " + name);
		if (!validateString(name)) {
			return false;
		}
		pattern = Pattern.compile(PATTERN_NAME);
		matcher = pattern.matcher(name);
		return matcher.matches();
	}

	public static boolean validatePassword(String password) {
		if (!validateString(password)) {
			logger.debug("Password is empty");
			return false;
		}
		pattern = Pattern.compile(PATTERN_PASSWORD);
		matcher = pattern.matcher(password);
		return matcher.matches();
	}

	public static boolean validateEmail(String email) {
		if (!validateString(email)) {
			logger.debug("Email is empty");
			return false;
		}
		pattern = Pattern.compile(PATTERN_EMAIL);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean validateLessonDate(Date lessonDate) {
		Date nowDate = new Date();
		logger.debug("Validate lesson date: " + (nowDate.before(lessonDate)));
		return nowDate.before(lessonDate);
	}

}

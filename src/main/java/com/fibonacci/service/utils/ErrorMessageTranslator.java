package com.fibonacci.service.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.validation.constraints.NotNull;

import org.springframework.util.StringUtils;

/**
 * Translates error codes into actual messages
 *
 * @author igorl
 */
public class ErrorMessageTranslator {

	private static final String NO_MESSAGE_DEFINED = "No message was defined in error properties file for ";

	private static final Locale DEFAULT_LOCALE = Locale.US;

	private static final String DEFAULT_PROPS = "en_error_messages.properties";

	private Map<Locale, Properties> errorMessagesMap  = new HashMap<>();

	private static ErrorMessageTranslator theInst = new ErrorMessageTranslator();

	public static ErrorMessageTranslator getInstance() {
		return theInst;
	}

	private ErrorMessageTranslator() {
		try {
			Properties defaultEnv = new Properties();
			InputStream in = MessageCodes.class.getClassLoader().getResourceAsStream(DEFAULT_PROPS);
			Reader reader = new InputStreamReader(in);
			defaultEnv.load(reader);
			errorMessagesMap.put(DEFAULT_LOCALE, defaultEnv);
		} catch (IOException e) {
			throw new RuntimeException("Cannot load default error messages");
		} catch (Exception e  ) {
			e.fillInStackTrace();
			e.printStackTrace();
		}
	}

	public String translateMessage(@NotNull MessageCodes code, Object... args) {
		return translateMessage(code, DEFAULT_LOCALE, args);
	}

	public String translateMessage(@NotNull MessageCodes code, Locale locale, Object... args) {
		Properties errorMessages = errorMessagesMap.get(locale);
		String message = errorMessages.getProperty(code.name());
		if (StringUtils.isEmpty(message)) {
			return NO_MESSAGE_DEFINED + code;
		}

		return MessageFormat.format(message, args);
	}
}
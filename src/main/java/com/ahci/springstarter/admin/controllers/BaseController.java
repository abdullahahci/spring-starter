package com.ahci.springstarter.admin.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;

public class BaseController {

	@Autowired
    private MessageSource messageSource;
	
	@Value("${locale}")
	private String defaultLocale;
	
	public String getMessage(String message) {
		return messageSource.getMessage(message,  null, new Locale(defaultLocale));
	}
	
	public String getMessage(String message, Locale locale) {
		return messageSource.getMessage(message,  null, locale);
	}
}

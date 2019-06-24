package com.ahci.springstarter;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ahci.springstarter.admin.exceptions.ContentNotFoundException;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {
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
	
	@ExceptionHandler(MultipartException.class)
	@ResponseBody
	ResponseEntity<String> handleFileException(HttpServletRequest request, Throwable ex) {
		String message = "";
		// return your json insted this string.
		if(ex instanceof MaxUploadSizeExceededException)
			message = getMessage("errors.fileUploadExceedsMaxSize");
		else
			message = getMessage("errors.fileUploadError");
		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}
	
	@ExceptionHandler(ContentNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404Exceptions(Exception e) {
		return "404"; 
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleAllExceptions(Exception e) {
		return "500"; 
	}
}
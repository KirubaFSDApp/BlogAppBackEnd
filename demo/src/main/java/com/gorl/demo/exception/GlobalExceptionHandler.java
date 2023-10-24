package com.gorl.demo.exception;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// 1. handle specfic exception, 2. handle global exception

	@ExceptionHandler(ResourcenotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourcenotFoundException exception,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(true));

		ResponseEntity<ErrorDetails> response = new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

		return response;
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorDetails> handleBadRequestException(BadRequestException exception, WebRequest request) {
		ErrorDetails details = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));

		ResponseEntity<ErrorDetails> response = new ResponseEntity<ErrorDetails>(details, HttpStatus.NOT_FOUND);

		return response;
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest request) {
		ErrorDetails details = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));

		ResponseEntity<ErrorDetails> response = new ResponseEntity<ErrorDetails>(details,
				HttpStatus.INTERNAL_SERVER_ERROR);

		return response;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		Map<String, Object> body = new LinkedHashMap<>();

		body.put("Timestamp", System.currentTimeMillis());
		body.put("Status", status.value());

		List<String> error = ex.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("Errors", error);

		return new ResponseEntity<Object>(body, status);

	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorDetails> handleAccessDeniedException(AccessDeniedException exception, WebRequest request) {
		ErrorDetails details = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));

		ResponseEntity<ErrorDetails> response = new ResponseEntity<ErrorDetails>(details,
				HttpStatus.UNAUTHORIZED);

		return response;
	}
}
 
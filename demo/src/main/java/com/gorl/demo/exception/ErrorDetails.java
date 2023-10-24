package com.gorl.demo.exception;

import java.util.Date;

public class ErrorDetails {
	
	private Date timetamp;
	private String message;
	private String details;
	
	public ErrorDetails(Date timetamp, String message, String details) {
		super();
		this.timetamp = timetamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimetamp() {
		return timetamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
	
}

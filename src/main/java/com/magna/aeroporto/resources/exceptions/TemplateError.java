package com.magna.aeroporto.resources.exceptions;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TemplateError {
	private String cause;
	private String message;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT")
	private Instant timestamp;
	private Integer status;
	
	public TemplateError(String cause, String message, Instant timestamp, Integer status) {
		super();
		this.cause = cause;
		this.message = message;
		this.timestamp = timestamp;
		this.status = status;
	}

	public String getCause() {
		return cause;
	}

	public String getMessage() {
		return message;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public Integer getStatus() {
		return status;
	}		
}

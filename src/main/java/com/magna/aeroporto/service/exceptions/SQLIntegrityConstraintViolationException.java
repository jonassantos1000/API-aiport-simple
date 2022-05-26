package com.magna.aeroporto.service.exceptions;

public class SQLIntegrityConstraintViolationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public SQLIntegrityConstraintViolationException (Throwable causa, String message) {
		super(message, causa);
	}
}

package com.magna.aeroporto.exceptions;

public class SQLIntegrityConstraintViolationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public SQLIntegrityConstraintViolationException (Throwable causa, String message) {
		super(message, causa);
	}
}

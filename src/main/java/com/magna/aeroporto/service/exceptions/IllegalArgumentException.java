package com.magna.aeroporto.service.exceptions;

public class IllegalArgumentException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IllegalArgumentException(Throwable causa, String message) {
		super(message, causa);
	}
}

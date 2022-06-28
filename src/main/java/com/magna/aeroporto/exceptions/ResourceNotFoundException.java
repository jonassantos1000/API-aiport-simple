package com.magna.aeroporto.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException (Throwable causa, String message) {
		super(message, causa);
	}
	
}

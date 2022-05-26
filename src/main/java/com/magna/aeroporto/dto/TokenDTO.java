package com.magna.aeroporto.dto;

public class TokenDTO {
	private String token;
	private String tipo;
	
	public TokenDTO() {
	}

	public TokenDTO(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}
	
}
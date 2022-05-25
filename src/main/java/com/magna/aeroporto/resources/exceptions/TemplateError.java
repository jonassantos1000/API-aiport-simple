package com.magna.aeroporto.resources.exceptions;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TemplateError {
	private String causador;
	private String mensagem;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT")
	private Instant timestamp;
	private Integer status;
	
	public TemplateError(String causador, String mensagem, Instant timestamp, Integer status) {
		super();
		this.causador = causador;
		this.mensagem = mensagem;
		this.timestamp = timestamp;
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getCausador() {
		return causador;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public Integer getStatus() {
		return status;
	}
		
}

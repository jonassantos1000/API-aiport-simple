package com.magna.aeroporto.exceptions.handler;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TemplateError {
	private String causa;
	private String mensagem;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT")
	private Instant timestamp;
	private Integer status;
	
	public TemplateError(String cause, String message, Instant timestamp, Integer status) {
		super();
		this.causa = cause;
		this.mensagem = message;
		this.timestamp = timestamp;
		this.status = status;
	}

	public String getCausa() {
		return causa;
	}

	public String getMensagem() {
		return mensagem;
	}

	public Integer getStatus() {
		return status;
	}	
}

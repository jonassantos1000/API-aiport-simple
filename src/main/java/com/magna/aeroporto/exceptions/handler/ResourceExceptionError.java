package com.magna.aeroporto.exceptions.handler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.magna.aeroporto.exceptions.IllegalArgumentException;
import com.magna.aeroporto.exceptions.ResourceNotFoundException;
import com.magna.aeroporto.exceptions.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ResourceExceptionError {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<TemplateError> handle(MethodArgumentNotValidException exception) {
		List<TemplateError> dto = new ArrayList<>();
		List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		fieldError.forEach(error -> {
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			TemplateError template = new TemplateError(error.getField(), mensagem, Instant.now(), status.value());
			dto.add(template);
		});
		return dto;
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<TemplateError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		HttpStatus status= HttpStatus.NOT_FOUND;
		TemplateError err = new TemplateError(e.getCause().getMessage(), e.getMessage(), Instant.now(), status.value());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<TemplateError> sqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e, HttpServletRequest request){
		HttpStatus status= HttpStatus.BAD_REQUEST;
		TemplateError err = new TemplateError(e.getCause().getMessage(), e.getMessage(), Instant.now(), status.value());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<TemplateError> illegalArgumentException(IllegalArgumentException e, HttpServletRequest request){
		HttpStatus status= HttpStatus.BAD_REQUEST;
		TemplateError err = new TemplateError(e.getCause().getMessage(), e.getMessage(), Instant.now(), status.value());
		return ResponseEntity.status(status).body(err);
	}
}

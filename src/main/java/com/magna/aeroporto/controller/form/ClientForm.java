package com.magna.aeroporto.controller.form;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

public class ClientForm implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotNull @NotEmpty
	private String nome;

	@NotNull @NotEmpty @CPF
	private String cpf; 
	
	@NotNull @NotEmpty
	private String logradouro;
	
	@NotNull @NotEmpty
	private String telefone;
	
	@NotNull @NotEmpty @Email
	private String email;
	
	public ClientForm() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
				
}

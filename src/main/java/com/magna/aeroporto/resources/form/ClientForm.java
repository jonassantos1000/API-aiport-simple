package com.magna.aeroporto.resources.form;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	public ClientForm(Long id, @NotNull @NotEmpty String nome, @NotNull @NotEmpty @CPF String cpf,
			@NotNull @NotEmpty String logradouro, @NotNull @NotEmpty String telefone,
			@NotNull @NotEmpty @Email String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.logradouro = logradouro;
		this.telefone = telefone;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	@JsonIgnore
	public void setId(Long id) {
		this.id = id;
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

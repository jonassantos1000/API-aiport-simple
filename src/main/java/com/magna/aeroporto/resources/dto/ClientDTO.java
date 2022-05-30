package com.magna.aeroporto.resources.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.magna.aeroporto.entities.Client;
import com.magna.aeroporto.entities.Ticket;

public class ClientDTO implements Serializable{
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
	
	private Set<Ticket> ticket;
	
	public ClientDTO() {
		
	}
	
	public ClientDTO(Long id,String nome, String cpf, String logradouro, String telefone, String email) {
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
	
	public Set<Ticket> getTicket() {
		return ticket;
	}

	public void setTicket(Set<Ticket> ticket) {
		this.ticket = ticket;
	}

	public static ClientDTO converteEntity(Client client) {
		ClientDTO dto = new ClientDTO();
		dto.setId(client.getId());
		dto.setNome(client.getNome());
		dto.setCpf(client.getCpf());
		dto.setEmail(client.getEmail());
		dto.setLogradouro(client.getLogradouro());
		dto.setTelefone(client.getTelefone());
		dto.setTicket(client.getTickets());
		return dto;
	}
			
}

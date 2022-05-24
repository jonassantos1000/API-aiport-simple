package com.magna.aeroporto.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.magna.aeroporto.dto.ClientDTO;

@Entity
public class Client implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nome;
	
	private String cpf;
	private String logradouro;
	private String telefone;
	private String email;
	
	@OneToMany(mappedBy = "client")
	private Set<Ticket> ticket = new HashSet<>();
	
	public Client() {
		
	}
	
	public Client(Long id, String nome, String cpf, String logradouro, String telefone, String email) {
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
	
	@JsonIgnore
	public Set<Ticket> getTickets(){
		return ticket;
	}
	
	@JsonIgnore
	public static Client converterDTO(ClientDTO dto) {
		Client client = new Client();
		client.setId(dto.getId());
		client.setCpf(dto.getCpf());
		client.setEmail(dto.getEmail());
		client.setLogradouro(dto.getLogradouro());
		client.setNome(dto.getNome());
		client.setTelefone(dto.getTelefone());
		return client;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
	}

}

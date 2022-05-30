package com.magna.aeroporto.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.magna.aeroporto.resources.dto.TicketDTO;

@Entity
public class Ticket implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="flight_id")
	private Flight flight;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	private LocalDateTime dataCompra;
	
	public Ticket() {
		
	}

	public Ticket(Long id, Flight flight, Client client, LocalDateTime dataCompra) {
		super();
		this.id = id;
		this.flight = flight;
		this.client = client;
		this.dataCompra = dataCompra;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	@JsonIgnore
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public LocalDateTime getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(LocalDateTime dataCompra) {
		this.dataCompra = dataCompra;
	}
	
	@JsonIgnore
	public static Ticket converteDTO(TicketDTO dto) {
		Ticket ticket = new Ticket();
		ticket.setClient(dto.getClient());
		ticket.getClient().setId(dto.getClient().getId());
		ticket.setId(dto.getId());
		ticket.setFlight(dto.getFlight());
		ticket.setDataCompra(dto.getDataCompra());
		return ticket;
	}

	@Override
	public int hashCode() {
		return Objects.hash(flight, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(flight, other.flight) && Objects.equals(id, other.id);
	}
	
	
}

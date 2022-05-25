package com.magna.aeroporto.dto;

import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.magna.aeroporto.entities.Client;
import com.magna.aeroporto.entities.Flight;
import com.magna.aeroporto.entities.Ticket;

public class TicketDTO {
	private Long id;

	@NotNull @NotEmpty @NotBlank
	private Flight flight;
	
	@NotNull @NotEmpty @NotBlank
	private Client client;
	
	@NotNull @NotEmpty @NotBlank
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	private Instant dataCompra;
	
	public TicketDTO() {
		
	}

	public TicketDTO(Long id, Flight flight, Client client, Instant dataCompra) {
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Instant getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Instant dataCompra) {
		this.dataCompra = dataCompra;
	}
	
	public static TicketDTO converteEntity(Ticket ticket) {
		TicketDTO dto = new TicketDTO();
		dto.setClient(ticket.getClient());
		dto.setDataCompra(ticket.getDataCompra());
		dto.setFlight(ticket.getFlight());
		dto.setId(ticket.getId());
		return dto;
	}
}

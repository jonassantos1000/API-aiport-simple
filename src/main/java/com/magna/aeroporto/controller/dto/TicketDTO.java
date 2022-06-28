package com.magna.aeroporto.controller.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.magna.aeroporto.entities.Client;
import com.magna.aeroporto.entities.Flight;
import com.magna.aeroporto.entities.Ticket;

public class TicketDTO {
	private Long id;

	@NotNull
	private Flight flight;
	
	@NotNull
	private Client client;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	private LocalDateTime dataCompra;
	
	public TicketDTO() {
		super();
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

	public LocalDateTime getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(LocalDateTime dataCompra) {
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

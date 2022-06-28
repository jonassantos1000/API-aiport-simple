package com.magna.aeroporto.controller.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.magna.aeroporto.entities.Flight;
import com.magna.aeroporto.entities.Ticket;

public class FlightDTO {
	
	private Long id;
	
	@NotNull @NotEmpty
	private String destiny;
	
	@NotNull @NotEmpty
	private String origin;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	private LocalDateTime departureTime;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	private LocalDateTime arrivalTime;
	
	@Digits(integer = 7, fraction = 2)
	private Double price;
	
	private Set<Ticket> ticket;

	public FlightDTO () {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDestiny() {
		return destiny;
	}

	public void setDestiny(String destiny) {
		this.destiny = destiny;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public void setTickets(Set<Ticket> ticket) {
		this.ticket= ticket;
	}
	
	@JsonIgnore
	public Set<Ticket> getTickets (){
		return ticket;
	}
	
	@JsonGetter(value = "Tickets")
	public Set<TicketFiltradoDTO> getTicketFiltrado(){
		Set<TicketFiltradoDTO> ticketsFiltrados = new HashSet<>();
		this.ticket.forEach(item ->
			ticketsFiltrados.add(new TicketFiltradoDTO(item)));
		return ticketsFiltrados;
	}
	
	public static FlightDTO converteEntity(Flight flight) {
		FlightDTO dto = new FlightDTO();
		dto.setArrivalTime(flight.getArrivalTime());
		dto.setDepartureTime(flight.getDepartureTime());
		dto.setDestiny(flight.getDestiny());
		dto.setId(flight.getId());
		dto.setOrigin(flight.getOrigin());
		dto.setPrice(flight.getPrice());
		dto.setTickets(flight.getTickets());
		return dto;
	}
}

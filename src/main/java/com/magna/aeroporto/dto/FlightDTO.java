package com.magna.aeroporto.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.magna.aeroporto.entities.Flight;
import com.magna.aeroporto.entities.Ticket;

public class FlightDTO {
	
	private Long id;
	private String destiny;
	private String origin;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	private Instant departureTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	private Instant arrivalTime;
	private Double price;
	private Set<Ticket> ticket;

	public FlightDTO () {
		
	}

	public FlightDTO(Long id, String destiny, String origin, Instant departureTime, Instant arrivalTime, Double price,
			Set<Ticket> ticket) {
		super();
		this.id = id;
		this.destiny = destiny;
		this.origin = origin;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.price = price;
		this.ticket = ticket;
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

	public Instant getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Instant departureTime) {
		this.departureTime = departureTime;
	}

	public Instant getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Instant arrivalTime) {
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

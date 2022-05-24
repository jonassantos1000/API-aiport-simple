package com.magna.aeroporto.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.magna.aeroporto.dto.FlightDTO;
import com.magna.aeroporto.dto.FlightTicketsDTO;

@Entity
public class Flight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String destiny;
	
	@NotNull
	private String origin;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	private Instant departureTime;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	private Instant arrivalTime;
	
	@NotNull
	private Double price;
	
	@OneToMany(mappedBy = "flight")
	private Set<Ticket> ticket;

	public Flight () {
		
	}

	public Flight(Long id, String destiny, String origin, Instant departureTime, Instant arrivalTime,
			Double price) {
		super();
		this.id = id;
		this.destiny = destiny;
		this.origin = origin;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.price = price;
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
	
	public void setTicket(Set<Ticket> ticket) {
		this.ticket = ticket;
	}

	@JsonIgnore
	public Set<Ticket> getTickets (){
		return ticket;
	}
	
	private static Set<Ticket> converterTicketsDTO(Set<FlightTicketsDTO> dto){
		Set<Ticket> ticketsConvertidos = new HashSet<>();
		if(dto != null && !dto.isEmpty()) {
			dto.forEach(item -> {
				Ticket ticket = new Ticket();
				ticket.setClient(item.getClient());
				ticket.setDataCompra(item.getDataCompra());
				ticket.setId(item.getId());
				ticketsConvertidos.add(ticket);
			});
			return ticketsConvertidos;
		}
		
		return ticketsConvertidos;
	}
	
	public static Flight converteDTO(FlightDTO flight) {
		Flight dto = new Flight();
		dto.setArrivalTime(flight.getArrivalTime());
		dto.setDepartureTime(flight.getDepartureTime());
		dto.setDestiny(flight.getDestiny());
		dto.setId(flight.getId());
		dto.setOrigin(flight.getOrigin());
		dto.setPrice(flight.getPrice());
		dto.setTicket(converterTicketsDTO(flight.getTickets()));
		return dto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		return Objects.equals(id, other.id);
	}
}

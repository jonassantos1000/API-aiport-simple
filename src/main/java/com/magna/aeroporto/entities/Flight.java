package com.magna.aeroporto.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import com.magna.aeroporto.resources.dto.FlightDTO;
import com.magna.aeroporto.resources.form.FlightForm;

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
	private LocalDateTime departureTime;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	private LocalDateTime arrivalTime;
	
	@NotNull
	private Double price= 0.0;
	
	@OneToMany(mappedBy = "flight")
	private Set<Ticket> ticket;

	public Flight () {
		
	}

	public Flight(Long id, String destiny, String origin, LocalDateTime departureTime, LocalDateTime arrivalTime,
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
	
	public void setTicket(Set<Ticket> ticket) {
		this.ticket = ticket;
	}

	@JsonIgnore
	public Set<Ticket> getTickets (){
		return ticket;
	}
		
	public static Flight converteDTO(FlightDTO flight) {
		Flight dto = new Flight();
		dto.setArrivalTime(flight.getArrivalTime());
		dto.setDepartureTime(flight.getDepartureTime());
		dto.setDestiny(flight.getDestiny());
		dto.setId(flight.getId());
		dto.setOrigin(flight.getOrigin());
		dto.setPrice(flight.getPrice());
		dto.setTicket(flight.getTickets());
		return dto;
	}
	
	public static Flight converteForm(FlightForm flight) {
		Flight dto = new Flight();
		dto.setArrivalTime(flight.getArrivalTime());
		dto.setDepartureTime(flight.getDepartureTime());
		dto.setDestiny(flight.getDestiny());
		dto.setId(flight.getId());
		dto.setOrigin(flight.getOrigin());
		dto.setPrice(flight.getPrice());
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

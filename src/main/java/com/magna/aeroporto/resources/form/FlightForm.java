package com.magna.aeroporto.resources.form;

import java.time.LocalDateTime;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class FlightForm {
	
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

	public FlightForm() {
		
	}
	
	public FlightForm(Long id, @NotNull @NotEmpty String destiny, @NotNull @NotEmpty String origin,
			@NotNull LocalDateTime departureTime, @NotNull LocalDateTime arrivalTime,
			@Digits(integer = 7, fraction = 2) Double price) {
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
 
	@JsonIgnore
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
			
}

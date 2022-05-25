package com.magna.aeroporto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magna.aeroporto.entities.Flight;
import com.magna.aeroporto.repositories.FlightRepository;

@Service
public class FlightService {
	
	@Autowired
	FlightRepository repository;
	
	public Flight insert(Flight flight) {
		return repository.save(flight);
	}
	
	public List<Flight> findAll(){
		return repository.findAll();
	}
	
	public Flight findById(Long id) {
		Optional<Flight> flight = repository.findById(id);
		return flight.orElseThrow();
	}
	
	public Flight update(Flight flight, Long id) {
		Flight atual = repository.getReferenceById(id);
		updateData(atual,flight);
		return repository.save(atual);
	}
	
	private void updateData(Flight atual, Flight novo) {
		atual.setArrivalTime(novo.getArrivalTime());
		atual.setDepartureTime(novo.getDepartureTime());
		atual.setDestiny(novo.getDestiny());
		atual.setOrigin(novo.getOrigin());
		atual.setPrice(novo.getPrice());
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}

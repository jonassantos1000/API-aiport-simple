package com.magna.aeroporto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magna.aeroporto.entities.Flight;
import com.magna.aeroporto.repositories.FlightRepository;
import com.magna.aeroporto.service.exceptions.ResourceNotFoundException;
import com.magna.aeroporto.service.exceptions.SQLIntegrityConstraintViolationException;

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
		return flight.orElseThrow(() -> new ResourceNotFoundException(new Throwable("ID"),"Não foi possivel encontrar um recurso flight válido com o id: "+id));
	}
	
	public Flight update(Flight flight, Long id) {
		try {
			Flight atual = repository.getReferenceById(id);
			updateData(atual,flight);
			return repository.save(atual);
		}catch(javax.persistence.EntityNotFoundException e) {
			throw new ResourceNotFoundException(new Throwable("Recurso Inexistente"),"Recurso flight que deseja atualizar não existe, verifique as informações passadas e tente novamente !");
		}
	}
	
	private void updateData(Flight atual, Flight novo) {
		atual.setArrivalTime(novo.getArrivalTime());
		atual.setDepartureTime(novo.getDepartureTime());
		atual.setDestiny(novo.getDestiny());
		atual.setOrigin(novo.getOrigin());
		atual.setPrice(novo.getPrice());
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(org.springframework.dao.EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(new Throwable("ID"),"Não foi possivel encontrar um recurso flight válido com o id: "+id);
		}catch(SQLIntegrityConstraintViolationException ex) {
			throw new SQLIntegrityConstraintViolationException(new Throwable("Violação de constraint"),"Não foi possível finalizar a operação, pois este recurso já possui movimentações");
		}
	}
}

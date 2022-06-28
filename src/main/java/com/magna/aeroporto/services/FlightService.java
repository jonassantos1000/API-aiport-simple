package com.magna.aeroporto.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.magna.aeroporto.entities.Flight;
import com.magna.aeroporto.exceptions.ResourceNotFoundException;
import com.magna.aeroporto.exceptions.SQLIntegrityConstraintViolationException;
import com.magna.aeroporto.repositories.FlightRepository;

@Service
public class FlightService {
	
	@Autowired
	FlightRepository repository;
	
	private static Logger logger = LoggerFactory.getLogger(FlightService.class);
	
	public Flight insert(Flight flight) {
		logger.info("INICIANDO METODO INSERT DE FLIGHT");
		return repository.save(flight);
	}
	
	public List<Flight> findAll(){
		logger.info("INICIANDO METODO findAll DE FLIGHT");
		return repository.findAll();
	}
	
	public Flight findById(Long id) {
		logger.info("INICIANDO METODO findById DE FLIGHT");
		Optional<Flight> flight = repository.findById(id);
		return flight.orElseThrow(() -> new ResourceNotFoundException(new Throwable("ID"),"Não foi possivel encontrar um recurso flight válido com o id: "+id));
	}
	
	public Flight update(Flight flight, Long id) {
		try {
			logger.info("INICIANDO METODO update DE FLIGHT");
			Flight atual = repository.getReferenceById(id);
			updateData(atual,flight);
			return repository.save(atual);
		}catch(javax.persistence.EntityNotFoundException e) {
			logger.error("ERRO AO EXECUTAR METODO update DE FLIGHT, ID INEXISTENTE");
			throw new ResourceNotFoundException(new Throwable("Recurso Inexistente"),"Recurso flight que deseja atualizar não existe, verifique as informações passadas e tente novamente !");
		}
	}
	
	public void delete(Long id) {
		try {
			logger.info("INICIANDO METODO delete DE FLIGHT");
			repository.deleteById(id);
		}catch(org.springframework.dao.EmptyResultDataAccessException e) {
			logger.error("ERRO AO EXECUTAR METODO delete DE FLIGHT, ID INEXISTENTE");
			throw new ResourceNotFoundException(new Throwable("ID"),"Não foi possivel encontrar um recurso flight válido com o id: "+id);
		}catch(SQLIntegrityConstraintViolationException | DataIntegrityViolationException ex) {
			logger.error("ERRO AO EXECUTAR METODO delete DE FLIGHT, ESTE RECURSO POSSUI VINCULOS NO BANCO DE DADOS");
			throw new SQLIntegrityConstraintViolationException(new Throwable("Violação de constraint"),"Não foi possível finalizar a operação, pois este recurso já possui movimentações");
		}
	}
	
	private void updateData(Flight atual, Flight novo) {
		atual.setArrivalTime(novo.getArrivalTime());
		atual.setDepartureTime(novo.getDepartureTime());
		atual.setDestiny(novo.getDestiny());
		atual.setOrigin(novo.getOrigin());
		atual.setPrice(novo.getPrice());
	}
}

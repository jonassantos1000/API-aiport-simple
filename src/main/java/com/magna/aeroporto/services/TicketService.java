package com.magna.aeroporto.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.magna.aeroporto.entities.Ticket;
import com.magna.aeroporto.exceptions.ResourceNotFoundException;
import com.magna.aeroporto.repositories.TicketRepository;

@Service
public class TicketService {
	
	@Autowired
	TicketRepository repository;
	
	private static Logger logger = LoggerFactory.getLogger(TicketService.class);
	
	public Ticket insert(Ticket ticket) {
		try {
			logger.info("INICIANDO METODO INSERT DE TICKET");
			return repository.save(ticket);
		}catch(DataIntegrityViolationException e) {
			logger.error("ERRO AO EXECUTAR METODO insert DE TICKET, VIOLAÇÃO DE CONSTRAINT");
			throw new DataIntegrityViolationException("VIOLAÇÃO DE CONSTRAINT");
		}
	}
	
	public List<Ticket> findAll(){
		logger.info("INICIANDO METODO findAll DE TICKET");
		return repository.findAll();
	}
	
	public Ticket findById(Long id) {
		logger.info("INICIANDO METODO findById DE TICKET");
		Optional<Ticket> ticket = repository.findById(id);
		return ticket.orElseThrow(() -> new ResourceNotFoundException(new Throwable("ID"),"Não foi possivel encontrar um recurso Ticket válido com o id: "+id));
	}
	
	public Ticket update(Ticket ticket, Long id) {
		try {
			logger.info("INICIANDO METODO update DE TICKET");
			Ticket atual = repository.getReferenceById(id);
			updateData(atual, ticket);
			return repository.save(atual);
		}catch(javax.persistence.EntityNotFoundException e) {
			logger.error("ERRO AO EXECUTAR METODO update DE TICKET, ID INEXISTENTE");
			throw new ResourceNotFoundException(new Throwable("Recurso Inexistente"),"Recurso Ticket que deseja atualizar não existe, verifique as informações passadas e tente novamente !");
		}
	}
	
	public void delete(Long id) {
		try {
			logger.info("INICIANDO METODO delete DE TICKET");
			repository.deleteById(id);
		}catch(org.springframework.dao.EmptyResultDataAccessException e) {
			logger.error("ERRO AO EXECUTAR METODO delete DE TICKET, ID INEXISTENTE");
			throw new ResourceNotFoundException(new Throwable("ID"),"Não foi possivel encontrar um recurso Ticket válido com o id: "+id);
		}
	}
	
	private void updateData(Ticket atual, Ticket novo) {
		atual.setClient(novo.getClient());
		atual.setFlight(novo.getFlight());
	}
}

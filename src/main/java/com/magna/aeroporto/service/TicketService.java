package com.magna.aeroporto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magna.aeroporto.entities.Ticket;
import com.magna.aeroporto.repositories.TicketRepository;
import com.magna.aeroporto.service.exceptions.ResourceNotFoundException;

@Service
public class TicketService {
	
	@Autowired
	TicketRepository repository;
	
	public Ticket insert(Ticket ticket) {
		return repository.save(ticket);
	}
	
	public List<Ticket> findAll(){
		return repository.findAll();
	}
	
	public Ticket findById(Long id) {
		Optional<Ticket> ticket = repository.findById(id);
		return ticket.orElseThrow(() -> new ResourceNotFoundException(new Throwable("ID"),"Não foi possivel encontrar um recurso válido com o id: "+id));
	}
	
	public Ticket update(Ticket ticket, Long id) {
		try {
			Ticket atual = repository.getReferenceById(id);
			updateData(atual, ticket);
			return repository.save(atual);
		}catch(javax.persistence.EntityNotFoundException e) {
			throw new ResourceNotFoundException(new Throwable("Recurso Inexistente"),"Recurso que deseja atualizar não existe, verifique as informações passadas e tente novamente !");
		}
	}
	
	public void updateData(Ticket atual, Ticket novo) {
		atual.setClient(novo.getClient());
		atual.setFlight(novo.getFlight());
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(org.springframework.dao.EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(new Throwable("ID"),"Não foi possivel encontrar um recurso válido com o id: "+id);
		}
	}
}

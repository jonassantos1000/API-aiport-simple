package com.magna.aeroporto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magna.aeroporto.entities.Ticket;
import com.magna.aeroporto.repositories.TicketRepository;

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
		return ticket.orElseThrow();
	}
	
	public Ticket update(Ticket ticket) {
		Ticket atual = findById(ticket.getId());
		updateData(atual, ticket);
		return repository.save(atual);
	}
	
	public void updateData(Ticket atual, Ticket novo) {
		atual.setClient(novo.getClient());
		atual.setFlight(novo.getFlight());
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}

package com.magna.aeroporto.resources;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magna.aeroporto.dto.TicketDTO;
import com.magna.aeroporto.entities.Ticket;
import com.magna.aeroporto.service.ClientService;
import com.magna.aeroporto.service.FlightService;
import com.magna.aeroporto.service.TicketService;

@RestController
@RequestMapping(value = "/ticket")
public class TicketResource {
	
	@Autowired
	TicketService service;
	
	@Autowired
	FlightService flightService;
	
	@Autowired
	ClientService clientService;
	
	@PostMapping
	public ResponseEntity<Ticket> insert(@Valid @RequestBody TicketDTO dto){
		Ticket ticket = service.insert(Ticket.converteDTO(dto));
		ticket.setFlight(flightService.findById(ticket.getFlight().getId()));
		ticket.setClient(clientService.findById(ticket.getClient().getId()));
		return new ResponseEntity<>(ticket, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<TicketDTO>> findAll() {
		List<Ticket> ticket = service.findAll();
		List<TicketDTO> listDTO = new ArrayList<>();
		for (Ticket t: ticket) {
			listDTO.add(TicketDTO.converteEntity(t));
		}
		return new ResponseEntity<>(listDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TicketDTO> findById(@PathVariable Long id){
		TicketDTO dto = TicketDTO.converteEntity(service.findById(id));
		return new ResponseEntity<> (dto, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Ticket> update(@RequestBody TicketDTO dto, @PathVariable Long id){
		Ticket ticket = service.update(Ticket.converteDTO(dto), id);
		ticket.setFlight(flightService.findById(ticket.getFlight().getId()));
		ticket.setClient(clientService.findById(ticket.getClient().getId()));
		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}
		
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}

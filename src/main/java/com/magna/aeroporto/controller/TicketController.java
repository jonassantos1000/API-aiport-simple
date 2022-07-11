package com.magna.aeroporto.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magna.aeroporto.controller.dto.TicketDTO;
import com.magna.aeroporto.controller.form.TicketForm;
import com.magna.aeroporto.entities.Ticket;
import com.magna.aeroporto.services.ClientService;
import com.magna.aeroporto.services.FlightService;
import com.magna.aeroporto.services.TicketService;

@RestController
@RequestMapping(value = "/ticket")
@CrossOrigin(origins = "http://localhost:4200")
public class TicketController {
	
	@Autowired
	TicketService service;
	
	@Autowired
	FlightService flightService;
	
	@Autowired
	ClientService clientService;
	
	@PostMapping
	public ResponseEntity<HttpStatus> insert(@Valid @RequestBody TicketForm dto){
		service.insert(populaDTO(dto));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<TicketDTO>> findAll() {
		List<TicketDTO> listDTO = service.findAll().stream().map(TicketDTO::converteEntity).collect(Collectors.toList());
		return new ResponseEntity<>(listDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TicketDTO> findById(@PathVariable Long id){
		return new ResponseEntity<> (TicketDTO.converteEntity(service.findById(id)), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<HttpStatus> update(@RequestBody TicketForm dto, @PathVariable Long id){
		service.update(populaDTO(dto), id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	private Ticket populaDTO(TicketForm dto) {
		Ticket ticket = new Ticket(); 
		ticket.setId(dto.getId());
		ticket.setDataCompra(dto.getDataCompra());
		ticket.setClient(clientService.findById(dto.getIdClient()));
		ticket.setFlight(flightService.findById(dto.getIdFlight()));
		return ticket;
	}
}

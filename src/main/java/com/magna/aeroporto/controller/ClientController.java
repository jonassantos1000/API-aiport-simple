package com.magna.aeroporto.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.magna.aeroporto.controller.dto.ClientDTO;
import com.magna.aeroporto.controller.form.ClientForm;
import com.magna.aeroporto.entities.Client;
import com.magna.aeroporto.services.ClientService;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

	@Autowired
	ClientService service;

	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll() {
		List<ClientDTO> listDTO = service.findAll().stream().map(ClientDTO::converteEntity).collect(Collectors.toList());
		return new ResponseEntity<>(listDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id){
		return new ResponseEntity<>(service.findById(id),HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<HttpStatus> insert(@Valid @RequestBody ClientForm clientDTO) {
		service.insert(Client.converterForm(clientDTO));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<HttpStatus> update(@Valid @RequestBody ClientForm clientDTO, @PathVariable Long id){
		service.update(Client.converterForm(clientDTO), id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}

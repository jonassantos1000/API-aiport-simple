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

import com.magna.aeroporto.entities.Client;
import com.magna.aeroporto.resources.dto.ClientDTO;
import com.magna.aeroporto.resources.form.ClientForm;
import com.magna.aeroporto.service.ClientService;

@RestController
@RequestMapping(value = "/client")
public class ClientResource {

	@Autowired
	ClientService service;

	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll() {
		List<ClientDTO> listDTO = new ArrayList<>();
		List<Client> client = service.findAll();
		for(Client c : client) {
			ClientDTO clientDTO = ClientDTO.converteEntity(c);
			listDTO.add(clientDTO);
		}
		return new ResponseEntity<>(listDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id){
		Client c = service.findById(id);
		return new ResponseEntity<>(c,HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Client> insert(@Valid @RequestBody ClientForm clientDTO) {
		Client client = Client.converterForm(clientDTO); 	
		service.insert(client);
		return new ResponseEntity<>(client, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Client> update(@Valid @RequestBody ClientForm clientDTO, @PathVariable Long id){
		Client client = service.update(Client.converterForm(clientDTO), id);
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}

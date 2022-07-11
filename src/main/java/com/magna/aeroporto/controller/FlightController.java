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

import com.magna.aeroporto.controller.dto.FlightDTO;
import com.magna.aeroporto.controller.form.FlightForm;
import com.magna.aeroporto.entities.Flight;
import com.magna.aeroporto.services.FlightService;

@RestController
@RequestMapping(value = "/flight")
@CrossOrigin(origins = "http://localhost:4200")
public class FlightController {
	
	@Autowired
	FlightService service;
	
	@PostMapping
	public ResponseEntity<HttpStatus> insert(@Valid @RequestBody FlightForm dto){
		service.insert(Flight.converteForm(dto));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<FlightDTO> findById(@PathVariable Long id){
		return new ResponseEntity<>(FlightDTO.converteEntity(service.findById(id)), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<FlightDTO>> findAll() {
		List<FlightDTO> listDTO = service.findAll().stream().map(FlightDTO::converteEntity).collect(Collectors.toList());
		return new ResponseEntity<>(listDTO, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<HttpStatus> update(@RequestBody FlightForm dto, @PathVariable Long id){
		service.update(Flight.converteForm(dto), id);
		return new ResponseEntity<> (HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}

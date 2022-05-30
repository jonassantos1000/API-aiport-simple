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

import com.magna.aeroporto.entities.Flight;
import com.magna.aeroporto.resources.dto.FlightDTO;
import com.magna.aeroporto.resources.form.FlightForm;
import com.magna.aeroporto.service.FlightService;

@RestController
@RequestMapping(value = "/flight")
public class FlightResource {
	
	@Autowired
	FlightService service;
	
	@PostMapping
	public ResponseEntity<Flight> insert(@Valid @RequestBody FlightForm dto){
		Flight flight = service.insert(Flight.converteForm(dto));
		return new ResponseEntity<>(flight, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<FlightDTO> findById(@PathVariable Long id){
		FlightDTO dto = FlightDTO.converteEntity(service.findById(id));
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<FlightDTO>> findAll() {
		List<Flight> flight = service.findAll();
		List<FlightDTO> listDTO = new ArrayList<>();
		for(Flight f : flight) {
			FlightDTO dto = FlightDTO.converteEntity(f);
			listDTO.add(dto);
		}
		return new ResponseEntity<>(listDTO, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Flight> update(@RequestBody FlightForm dto, @PathVariable Long id){
		Flight flight = service.update(Flight.converteForm(dto), id);
		return new ResponseEntity<> (flight, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}

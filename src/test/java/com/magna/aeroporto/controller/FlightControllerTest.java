package com.magna.aeroporto.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.magna.aeroporto.entities.Flight;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
class FlightControllerTest {
	
	@Autowired
	TestRestTemplate restTemplate;
	
	private static String PORT = "8080";
	private static StringBuilder URL = new StringBuilder("http://127.0.0.1:").append(PORT).append("/flight/");
	private static URI uri = URI.create(URL.toString());

	@Test
	void deveriaInserirFlight() {
		Flight flight = populaEntidade();
		
		ResponseEntity<Flight> entidade = restTemplate.postForEntity(uri,flight, Flight.class);
		
		assertEquals(HttpStatus.CREATED, entidade.getStatusCode());
	}
	
	@Test
	void deveriaBuscarFlightPorId() {		
		ResponseEntity<Flight> entidade = restTemplate.getForEntity(uri+"/1",Flight.class);
		
		assertEquals(HttpStatus.OK, entidade.getStatusCode());
	}
	
	@Test
	void deveriaBuscarTodosFlight() {		
		ResponseEntity<Flight[]> entidade = restTemplate.getForEntity(uri,Flight[].class);
		
		assertEquals(HttpStatus.OK, entidade.getStatusCode());
	}
	
	@Test
	void deveriaAlterarFlight() {
		Flight flight = populaEntidade();
		flight.setPrice(700.0);
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		ResponseEntity<Flight> entidade = restTemplate.exchange(uri+"/1", HttpMethod.PUT, new HttpEntity<Flight>(flight,header), Flight.class);
		
		assertEquals(HttpStatus.OK, entidade.getStatusCode());
	}
	
	@Test
	void deveriaApagarFlight() {
		deveriaInserirFlight();
		
		Flight flight = populaEntidade();
		flight.setPrice(700.0);
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		ResponseEntity<Flight> entidade = restTemplate.exchange(uri+"/2", HttpMethod.DELETE, new HttpEntity<Flight>(flight,header), Flight.class);
		
		assertEquals(HttpStatus.NO_CONTENT, entidade.getStatusCode());
	}
	
	@Test
	void naoDeveriaEncontrarFLightComIdInvalido() {
		
		ResponseEntity<Flight> entidade = restTemplate.getForEntity(uri+"/0",Flight.class);
		
		assertEquals(HttpStatus.NOT_FOUND, entidade.getStatusCode());
	}
	
	@Test
	void naoDeveriaAlterarFlightInexistente() {
		Flight flight = populaEntidade();
		flight.setPrice(700.0);
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		ResponseEntity<Flight> entidade = restTemplate.exchange(uri+"/0", HttpMethod.PUT, new HttpEntity<Flight>(flight,header), Flight.class);
		
		assertEquals(HttpStatus.NOT_FOUND, entidade.getStatusCode());
	}
	
	@Test
	void naoDeveriaApagarFlightComIdInexistente() {
		deveriaInserirFlight();
		
		Flight flight = populaEntidade();
		flight.setPrice(700.0);
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		ResponseEntity<Flight> entidade = restTemplate.exchange(uri+"/2", HttpMethod.DELETE, new HttpEntity<Flight>(flight,header), Flight.class);
		
		assertEquals(HttpStatus.NOT_FOUND, entidade.getStatusCode());
	}
	
	@Test
	void naoDeveriaApagarFlightQueJaPossuiMovimentacao() {
		deveriaInserirFlight();
		
		Flight flight = populaEntidade();
		flight.setPrice(700.0);
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		ResponseEntity<Flight> entidade = restTemplate.exchange(uri+"/1", HttpMethod.DELETE, new HttpEntity<Flight>(flight,header), Flight.class);
		
		assertEquals(HttpStatus.BAD_REQUEST, entidade.getStatusCode());
	}
		
	private Flight populaEntidade() {
		Flight flight = new Flight (3l, "japao", "brasil", LocalDateTime.of(2021,05,10,10,00,00), LocalDateTime.of(2021,05,13,15,00,00), 500.0);
		return flight;
	}

}

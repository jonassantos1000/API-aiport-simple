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

import com.magna.aeroporto.controller.form.TicketForm;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
class TicketControllerTest {
	
	@Autowired
	TestRestTemplate restTemplate;
	
	private static String PORT = "8080";
	private static StringBuilder URL = new StringBuilder("http://127.0.0.1:").append(PORT).append("/ticket");
	private static URI uri = URI.create(URL.toString());

	@Test
	void deveriaInserirTicket() {
		TicketForm ticket = new TicketForm(null,1l,1l,LocalDateTime.of(2021,05,10,10,00,00));
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		ResponseEntity<TicketForm> entidade = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<TicketForm>(ticket, header), TicketForm.class);
		
		assertEquals(HttpStatus.CREATED, entidade.getStatusCode());
	}

	@Test
	void deveriaBuscarTicketPorId() {		
		ResponseEntity<TicketForm> entidade = restTemplate.getForEntity(uri+"/1",TicketForm.class);
		
		assertEquals(HttpStatus.OK, entidade.getStatusCode());
	}
	
	@Test
	void deveriaBuscarTodosTicket() {		
		ResponseEntity<TicketForm[]> entidade = restTemplate.getForEntity(uri,TicketForm[].class);
		
		assertEquals(HttpStatus.OK, entidade.getStatusCode());
	}
	
	@Test
	void deveriaAlterarTicket() {
	
		TicketForm ticket = populaEntidade();
		ticket.setIdClient(2l);

		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		ResponseEntity<TicketForm> entidade = restTemplate.exchange(uri+"/1", HttpMethod.PUT, new HttpEntity<TicketForm>(ticket,header), TicketForm.class);
		
		assertEquals(HttpStatus.OK, entidade.getStatusCode());
	}
	
	@Test
	void deveriaApagarTicket() {
		deveriaInserirTicket();
		
		TicketForm ticket = populaEntidade();
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		ResponseEntity<TicketForm> entidade = restTemplate.exchange(uri+"/2", HttpMethod.DELETE, new HttpEntity<TicketForm>(ticket,header), TicketForm.class);
		
		assertEquals(HttpStatus.NO_CONTENT, entidade.getStatusCode());
	}
	
	@Test
	void naoDeveriaEncontrarTicketComIdInvalido() {
		
		ResponseEntity<TicketForm> entidade = restTemplate.getForEntity(uri+"/0",TicketForm.class);
		
		assertEquals(HttpStatus.NOT_FOUND, entidade.getStatusCode());
	}
	
	@Test
	void naoDeveriaAlterarTicketInexistente() {
		TicketForm ticket = populaEntidade();
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		ResponseEntity<TicketForm> entidade = restTemplate.exchange(uri+"/0", HttpMethod.PUT, new HttpEntity<TicketForm>(ticket,header), TicketForm.class);
		
		assertEquals(HttpStatus.NOT_FOUND, entidade.getStatusCode());
	}
	
	@Test
	void naoDeveriaApagarTicketComIdInexistente() {
		deveriaInserirTicket();
		
		TicketForm ticket = populaEntidade();
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		ResponseEntity<TicketForm> entidade = restTemplate.exchange(uri+"/0", HttpMethod.DELETE, new HttpEntity<TicketForm>(ticket,header), TicketForm.class);
		
		assertEquals(HttpStatus.NOT_FOUND, entidade.getStatusCode());
	}
		
	private TicketForm populaEntidade() {
		TicketForm ticket = new TicketForm(null,1l,1l,LocalDateTime.of(2021,05,10,10,00,00));
		return ticket;
	}

}

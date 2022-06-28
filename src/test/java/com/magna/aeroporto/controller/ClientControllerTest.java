package com.magna.aeroporto.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

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

import com.magna.aeroporto.entities.Client;
import com.magna.aeroporto.services.ClientService;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
class ClientControllerTest {

	@Autowired
	TestRestTemplate restTemplate;
	
	@Autowired
	ClientService service;

	private static String PORT = "8080";
	private static String PATH = "/client";
	private static StringBuilder URL = new StringBuilder("http://127.0.0.1:").append(PORT).append(PATH);
	private static URI uri = URI.create(URL.toString());
	
	Client popularEntidade() {
		Client client = new Client(null, "joao", "50229624898", "rua maria", "20541155", "joao@hotmail.com");
		return client;
	}
	
	@Test
	void deveriaInserirCliente() {		
		Client client = popularEntidade(); 
		
		ResponseEntity<Client> resposta = restTemplate.postForEntity(uri, client, Client.class);

		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}
	
	@Test
	void deveriaAlterarCliente() {	
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		Client client = popularEntidade();
		client.setEmail("testejunit@gmail.com");
		client.setId(1l);
		
		ResponseEntity<Client> entidade = restTemplate.exchange(uri+"/1", HttpMethod.PUT , new HttpEntity<Client>(client,header) ,Client.class);
	
		Client alterado = service.findById(1l);
		
		assertEquals(client, alterado);
		assertEquals(HttpStatus.OK, entidade.getStatusCode());
	}
	

	
	@Test
	void deveriaBuscarClientePorId() {
		
		ResponseEntity<Client> resposta = restTemplate.getForEntity(uri+"/1", Client.class);
		
		Client client = service.findById(1l);
		
		assertEquals(client, resposta.getBody());
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	void deveriaBuscarTodosClientes() {
		
		ResponseEntity<Client[]> resposta = restTemplate.getForEntity(uri, Client[].class);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	void naoDeveriaEncontrarIdInexistente() {
		ResponseEntity<Client> entidade = restTemplate.getForEntity(uri+"/0", Client.class);
		
		assertEquals(HttpStatus.NOT_FOUND, entidade.getStatusCode());
	}
	
	@Test
	void DeveriaApagarCliente() {
		deveriaInserirCliente();
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		Client client = popularEntidade();
		
		ResponseEntity<Client> entidade = restTemplate.exchange(uri+"/3", HttpMethod.DELETE, new HttpEntity<Client>(client,header), Client.class);
		
		assertEquals(HttpStatus.NO_CONTENT, entidade.getStatusCode());
	}	
	
	@Test
	void naoDeveriaApagarClienteInexistente() {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		Client client = popularEntidade();
		
		ResponseEntity<Client> entidade = restTemplate.exchange(uri+"/0", HttpMethod.DELETE, new HttpEntity<Client>(client,header), Client.class);
		
		assertEquals(HttpStatus.NOT_FOUND, entidade.getStatusCode());
	}
	
	@Test
	void naoDeveriaApagarClienteQueJaPossuiMovimentacao() {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		Client client = popularEntidade();
		
		ResponseEntity<Client> entidade = restTemplate.exchange(uri+"/1", HttpMethod.DELETE, new HttpEntity<Client>(client,header), Client.class);
		
		assertEquals(HttpStatus.BAD_REQUEST, entidade.getStatusCode());
	}

	@Test
	void naoDeveriaAlterarClienteInexistente() {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		Client client = popularEntidade();
		client.setEmail("testejunit@gmail.com");
		
		ResponseEntity<Client> entidade = restTemplate.exchange(uri+"/0", HttpMethod.PUT , new HttpEntity<Client>(client,header) ,Client.class);
	
		assertEquals(HttpStatus.NOT_FOUND, entidade.getStatusCode());
	}
	
}

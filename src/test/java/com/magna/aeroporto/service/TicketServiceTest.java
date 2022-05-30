package com.magna.aeroporto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.magna.aeroporto.entities.Client;
import com.magna.aeroporto.entities.Flight;
import com.magna.aeroporto.entities.Ticket;

@RunWith(SpringRunner.class)
@SpringBootTest
class TicketServiceTest {
	@Autowired
	private TicketService service;
	
	@Autowired
	private FlightService flightService;

	@Test
	void deveriaRetornarOhTicketPeloID() {
		Long id = 1l;
		Ticket ticket = service.findById(id);
		System.out.println(ticket.getId());
		Assert.assertNotNull(ticket);
		Assert.assertEquals(id, ticket.getId());
	}

	@Test
	void deveriaLancarExceptionAoBuscarTicketPeloIdInexistente() {
		try {
			Long id = 999l;
			Ticket ticket = service.findById(id);
			fail();
		} catch (Exception e) {
			Assert.assertEquals(e.getMessage(),
					"Não foi possivel encontrar um recurso Ticket válido com o id: " + 999l);
		}
	}
	
	@Test
	void deveriaInserirOhTicket() {
		Flight flight = new Flight (1l, "japao", "brasil", LocalDateTime.of(2021,05,10,10,00,00), LocalDateTime.of(2021,05,13,15,00,00), 500.0);
		Client client = new Client(1l, "joao", "50229624898", "rua maria", "20541155", "joao@hotmail.com");
		Ticket ticket = new Ticket(2l, flight, client, LocalDateTime.of(2021, 05, 8, 10, 00, 00));
		service.insert(ticket);
		Ticket retorno = service.findById(2l);
		assertEquals(ticket, retorno);
	}

	@Transactional
	@Test
	void deveriaAlterarOhTicket() {
		Flight flight = new Flight (2l, "japao", "brasil", LocalDateTime.of(2021,05,10,10,00,00), LocalDateTime.of(2021,05,13,15,00,00), 500.0);
		flightService.insert(flight);
		
		Client client = new Client(null, "joao", "50229624898", "rua maria", "20541155", "joao@hotmail.com");
		Ticket ticket = new Ticket(1l, flight, client, LocalDateTime.of(2021, 05, 8, 10, 00, 00));

		Ticket retorno = service.update(ticket, ticket.getId());
		assertEquals(ticket, retorno);
	}

	@Transactional
	@Test
	void deveriaLancarExceptionAoAlterarTicketInexistente() {
		try {
			Flight flight = new Flight (2l, "japao", "brasil", LocalDateTime.of(2021,05,10,10,00,00), LocalDateTime.of(2021,05,13,15,00,00), 500.0);
			Client client = new Client(null, "joao", "50229624898", "rua maria", "20541155", "joao@hotmail.com");
			Ticket ticket = new Ticket(99l,flight, client, LocalDateTime.of(2021, 05, 10, 10, 00, 00));
			Ticket retorno = service.update(ticket, ticket.getId());
			fail();
		} catch (Exception e) {

		}
	}

	@Test
	void deveriaExcluirTicket() {
		service.delete(1l);
		try {
			service.findById(1l);
			fail();
		} catch (Exception e) {

		}
	}

	@Test
	void deveriaLancarExceptionAoExcluirTicketeInexistente() {
		try {
			service.delete(3l);
			fail();
		} catch (Exception e) {
			Assert.assertEquals(e.getMessage(), "Não foi possivel encontrar um recurso Ticket válido com o id: " + 3l);
		}
	}
	
}

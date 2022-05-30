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

import com.magna.aeroporto.entities.Flight;

@RunWith(SpringRunner.class)
@SpringBootTest
class FlightServiceTest {
		@Autowired
		private FlightService service;
		
		@Test
		void deveriaRetornarOhFlightPeloID() {
			Long id = 1l;
			Flight flight = service.findById(id);
			System.out.println(flight.getId());
			Assert.assertNotNull(flight);
			Assert.assertEquals(id, flight.getId());
		}
		 
		@Test
		 void deveriaLancarExceptionAoBuscarFlightPeloIdInexistente() {
			try {
				Long id = 999l;
				Flight flight = service.findById(id);
				fail();
			}catch(Exception e) {
				Assert.assertEquals(e.getMessage(), "Não foi possivel encontrar um recurso flight válido com o id: " + 999l);
			}
		}
		
		@Test
		void deveriaInserirOhFlight() {	
			Flight flight = new Flight (3l, "japao", "brasil", LocalDateTime.of(2021,05,10,10,00,00), LocalDateTime.of(2021,05,13,15,00,00), 500.0);
			service.insert(flight);
			Flight retorno = service.findById(3l);
			assertEquals(flight, retorno);
		}
		

		@Transactional
		@Test
		void deveriaAlterarOhFlight() {
			Flight flight = new Flight(1l, "japao", "brasil", LocalDateTime.of(2021,05,10,10,00,00), LocalDateTime.of(2021,05,13,15,00,00), 500.0);
			Flight retorno = service.update(flight, flight.getId());
			assertEquals(flight, retorno);
		}
		
		@Transactional
		@Test
		void deveriaLancarExceptionAoAlterarFlightInexistente() {
			try {
				Flight flight = new Flight(999l, "japao", "brasil", LocalDateTime.of(2021,05,10,10,00,00), LocalDateTime.of(2021,05,13,15,00,00), 500.0);
				Flight retorno = service.update(flight, flight.getId());
				fail();
			}catch(Exception e) {
				
			}
		}
		
		@Test
		void deveriaExcluirFlight() {
			service.delete(2l);
			try{
				service.findById(2l);
				fail();
			}catch(Exception e) {
				
			}
		}
		
		@Test
		void deveriaLancarExceptionAoExcluirFlighteInexistente() {
			try {
				service.delete(3l);
				fail();
			}catch(Exception  e){
				Assert.assertEquals(e.getMessage(), "Não foi possivel encontrar um recurso flight válido com o id: "+3l);
			}
		}
		
}
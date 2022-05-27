package com.magna.aeroporto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.magna.aeroporto.entities.Client;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {
	@Autowired
	private ClientService service;
	
	@Test
	public void deveriaRetornarOhClientPeloID() {
		Long id = 1l;
		Client client = service.findById(id);
		Assert.assertNotNull(client);
		Assert.assertEquals(id, client.getId());
	}
	
	@Test
	public void deveriaLancarExceptionAoBuscarClientPeloIdInexistente() {
		try {
			Long id = 999l;
			Client client = service.findById(id);
			fail();
		}catch(Exception e) {
			Assert.assertEquals(e.getMessage(), "Não foi possivel encontrar um recurso válido com o id: " + 999l);
		}
	}
	
	@Test
	public void deveriaInserirOhClient() {
		Client client = new Client(null, "joao", "50229624898", "rua maria", "20541155", "joao@hotmail.com");
		service.insert(client);
		Client retorno = service.findById(2l);
		assertEquals(client, retorno);
	}
	
	@Transactional
	@Test
	public void deveriaAlterarOhClient() {
		Client client = new Client(1l, "jotaPE", "50229624898", "rua maria", "20541155", "joao@hotmail.com");
		Client retorno = service.update(client, client.getId());
		assertEquals(client, retorno);
	}
	
	@Transactional
	@Test
	public void deveriaLancarExceptionAoAlterarClientInexistente() {
		try {
			Client client = new Client(5l, "jotaPE", "50229624898", "rua maria", "20541155", "joao@hotmail.com");
			Client retorno = service.update(client, client.getId());
			fail();
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void deveriaExcluirClient() {
		service.delete(1l);
	}
	
	@Test
	public void deveriaLancarExceptionAoExcluirClienteInexistente() {
		try {
			service.delete(3l);
			fail();
		}catch(Exception  e){
			Assert.assertEquals(e.getMessage(), "Não foi possivel encontrar um recurso válido com o id: "+3l);
		}
	}
	 
}

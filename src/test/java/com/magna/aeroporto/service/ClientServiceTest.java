package com.magna.aeroporto.service;

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
	public void findById() {
		Long id = 1l;
		Client client = service.findById(id);
		Assert.assertNotNull(client);
		Assert.assertEquals(id, client.getId());
	}
}

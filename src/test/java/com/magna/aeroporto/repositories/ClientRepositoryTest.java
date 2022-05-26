package com.magna.aeroporto.repositories;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.magna.aeroporto.entities.Client;

@RunWith(SpringRunner.class)
@DataJpaTest
class ClientRepositoryTest {

	@Autowired
	ClientRepository repository;
	
	@Test
	public void test() {
		Long id = 1l;
		Client client = repository.findById(id).get();
		Assert.assertNotNull(client);
		Assert.assertEquals(id, client.getId());
	}

}

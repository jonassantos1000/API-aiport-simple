package com.magna.aeroporto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magna.aeroporto.entities.Client;
import com.magna.aeroporto.repositories.ClientRepository;
import com.magna.aeroporto.service.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	ClientRepository repository;
	
	public List<Client> findAll(){
		return repository.findAll();
	}
	
	public Client findById(Long id) {
		Optional<Client> client = repository.findById(id);
		if (client.isPresent())
			return client.get();
		else
			throw new ResourceNotFoundException(new Throwable("ID"),"Não foi possivel encontrar um recurso válido com o id: "+id);
	}
	
	public Client insert(Client client) {
		return repository.save(client);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(org.springframework.dao.EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(new Throwable("ID"),"Não foi possivel encontrar um recurso válido com o id: "+id);
		}
	}
	
	public Client update(Client client, Long id) {
		Client clientAtual = repository.getReferenceById(id);
		updateData(clientAtual, client);
		return clientAtual;
	}
	
	private void updateData(Client atual, Client novo) {
		atual.setCpf(novo.getCpf());
		atual.setEmail(novo.getEmail());
		atual.setLogradouro(novo.getLogradouro());
		atual.setNome(novo.getNome());
		atual.setTelefone(novo.getTelefone());
	}
	
}

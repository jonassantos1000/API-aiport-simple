package com.magna.aeroporto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magna.aeroporto.entities.Client;
import com.magna.aeroporto.repositories.ClientRepository;

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
			throw new IllegalArgumentException("Id Invalido");
	}
	
	public Client insert(Client client) {
		return repository.save(client);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Client update(Client client) {
		Client clientAtual = findById(client.getId());
		updateData(clientAtual, client);
		return repository.save(clientAtual);
	}
	
	private void updateData(Client atual, Client novo) {
		atual.setCpf(novo.getCpf());
		atual.setEmail(novo.getEmail());
		atual.setLogradouro(novo.getLogradouro());
		atual.setNome(novo.getNome());
		atual.setTelefone(novo.getTelefone());
	}
	
}

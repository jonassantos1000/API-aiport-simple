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
	
	public Client insert(Client client) {
		return repository.save(client);
	}
	
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
	
	public Client update(Client client, Long id) {
		try {
			Client clientAtual = repository.getReferenceById(id);
			updateData(clientAtual, client);
			return repository.save(clientAtual);
		}catch(javax.persistence.EntityNotFoundException e) {
			throw new ResourceNotFoundException(new Throwable("Recurso Inexistente"),"Recurso que deseja atualizar não existe, verifique as informações passadas e tente novamente !");
		}
	}
	
	private void updateData(Client atual, Client novo) {
		atual.setCpf(novo.getCpf());
		atual.setEmail(novo.getEmail());
		atual.setLogradouro(novo.getLogradouro());
		atual.setNome(novo.getNome());
		atual.setTelefone(novo.getTelefone());
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(org.springframework.dao.EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(new Throwable("ID"),"Não foi possivel encontrar um recurso válido com o id: "+id);
		}
	}
	
	
}

package com.magna.aeroporto.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.magna.aeroporto.entities.Client;
import com.magna.aeroporto.exceptions.ResourceNotFoundException;
import com.magna.aeroporto.exceptions.SQLIntegrityConstraintViolationException;
import com.magna.aeroporto.repositories.ClientRepository;

@Service
public class ClientService {
	
	private static Logger logger = LoggerFactory.getLogger(ClientService.class);
	
	@Autowired
	ClientRepository repository;
	
	public Client insert(Client client) {
		logger.info("INICIANDO METODO INSERT DE CLIENTE");
		return repository.save(client);
	}
	public List<Client> findAll(){
		logger.info("INICIANDO METODO findAll DE CLIENTES");
		return repository.findAll();
	}
	
	public Client findById(Long id) {
		logger.info("INICIANDO METODO findById DE CLIENTE");
		Optional<Client> client = repository.findById(id);
		if (client.isPresent())
			return client.get();
			
		logger.error("ERRO AO EXECUTAR METODO findById DE CLIENTE, ID INEXISTENTE");
		throw new ResourceNotFoundException(new Throwable("ID"),"Não foi possivel encontrar um recurso client válido com o id: "+id);
	}
	
	public Client update(Client client, Long id) {
		try {
			logger.info("INICIANDO METODO update DE CLIENTE");
			Client clientAtual = repository.getReferenceById(id);
			updateData(clientAtual, client);
			return repository.save(clientAtual);
		}catch(javax.persistence.EntityNotFoundException e) {
			logger.error("ERRO AO EXECUTAR METODO update DE CLIENTE, ID INEXISTENTE");
			throw new ResourceNotFoundException(new Throwable("Recurso Inexistente"),"Recurso client que deseja atualizar não existe, verifique as informações passadas e tente novamente !");
		}
	}
	
	public void delete(Long id) {
		try {
			logger.info("INICIANDO METODO delete DE CLIENTE");
			repository.deleteById(id);
		}catch(org.springframework.dao.EmptyResultDataAccessException e) {
			logger.error("ERRO AO EXECUTAR METODO delete DE CLIENTE, ID INEXISTENTE");
			throw new ResourceNotFoundException(new Throwable("ID"),"Não foi possivel encontrar um recurso client válido com o id: "+id);
		}catch(SQLIntegrityConstraintViolationException | DataIntegrityViolationException ex) {
			logger.error("ERRO AO EXECUTAR METODO delete DE CLIENTE, ESTE RECURSO POSSUI VINCULOS NO BANCO DE DADOS");
			throw new SQLIntegrityConstraintViolationException(new Throwable("Violação de constraint"),"Não foi possível finalizar a operação, pois este recurso client já possui movimentações");
		}
	}	 
	
	private void updateData(Client atual, Client novo) {
		atual.setCpf(novo.getCpf());
		atual.setEmail(novo.getEmail());
		atual.setLogradouro(novo.getLogradouro());
		atual.setNome(novo.getNome());
		atual.setTelefone(novo.getTelefone());
	}
}

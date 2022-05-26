package com.magna.aeroporto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magna.aeroporto.config.validacao.Users;
import com.magna.aeroporto.repositories.UserRepository;

@Service
public class UsersService {

	@Autowired
	UserRepository repository;
	
	public Users insert(Users user) {
		return repository.save(user);
	}
}

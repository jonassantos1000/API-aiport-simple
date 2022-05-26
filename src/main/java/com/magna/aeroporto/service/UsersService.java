package com.magna.aeroporto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magna.aeroporto.entities.Users;
import com.magna.aeroporto.repositories.UserRepository;

@Service
public class UsersService {

	@Autowired
	UserRepository repository;
	
	public Users insert(Users user) {
		return repository.save(user);
	}
	
	public Users findById(Long id) {
		Optional <Users> user = repository.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		return null;
	}
}

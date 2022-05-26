package com.magna.aeroporto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magna.aeroporto.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long>{
	Optional<Users> findByEmail(String email);
}

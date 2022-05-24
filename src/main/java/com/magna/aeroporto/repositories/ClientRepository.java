package com.magna.aeroporto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magna.aeroporto.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}

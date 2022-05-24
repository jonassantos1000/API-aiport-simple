package com.magna.aeroporto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magna.aeroporto.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long>{

}

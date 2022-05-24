package com.magna.aeroporto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magna.aeroporto.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

}

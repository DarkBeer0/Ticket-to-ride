package com.tickettoride.tickettoride.repository;

import com.tickettoride.tickettoride.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}

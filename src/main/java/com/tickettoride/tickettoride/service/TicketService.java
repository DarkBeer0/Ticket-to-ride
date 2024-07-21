package com.tickettoride.tickettoride.service;

import com.tickettoride.tickettoride.entity.Ticket;
import com.tickettoride.tickettoride.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing tickets.
 */
@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    /**
     * Retrieves all tickets.
     *
     * @return a list of all tickets
     */
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    /**
     * Saves a ticket.
     *
     * @param ticket the ticket to save
     * @return the saved ticket
     */
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
}

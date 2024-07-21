package com.tickettoride.tickettoride;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.tickettoride.tickettoride.entity.Ticket;
import com.tickettoride.tickettoride.repository.TicketRepository;
import com.tickettoride.tickettoride.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTickets() {
        List<Ticket> tickets = List.of(new Ticket(), new Ticket());
        when(ticketRepository.findAll()).thenReturn(tickets);

        List<Ticket> result = ticketService.getAllTickets();

        assertEquals(2, result.size());
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    public void testSaveTicket() {
        Ticket ticket = new Ticket();
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket savedTicket = ticketService.saveTicket(ticket);

        assertNotNull(savedTicket);
        verify(ticketRepository, times(1)).save(ticket);
    }
}

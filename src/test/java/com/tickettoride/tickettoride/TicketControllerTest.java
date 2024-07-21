package com.tickettoride.tickettoride;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.tickettoride.tickettoride.controller.TicketController;
import com.tickettoride.tickettoride.entity.Town;
import com.tickettoride.tickettoride.entity.Traveller;
import com.tickettoride.tickettoride.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TicketControllerTest {

    @Mock
    private TicketService ticketService;

    @Mock
    private TownService townService;

    @Mock
    private TravellerService travellerService;

    @Mock
    private RouteService routeService;

    @InjectMocks
    private TicketController ticketController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = standaloneSetup(ticketController).build();
    }

    @Test
    public void testCalculatePrice() throws Exception {
        Town london = new Town();
        london.setName("London");
        Town bristol = new Town();
        bristol.setName("Bristol");

        when(townService.findByName("London")).thenReturn(london);
        when(townService.findByName("Bristol")).thenReturn(bristol);

        Map<String, Object> routeInfo = new HashMap<>();
        routeInfo.put("segments_count", 7);
        when(routeService.findRoute(london, bristol)).thenReturn(routeInfo);

        mockMvc.perform(get("/api/tickets/calculate")
                        .param("departure", "London")
                        .param("arrival", "Bristol"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(25))
                .andExpect(jsonPath("$.segments").value(7))
                .andExpect(jsonPath("$.currency").value("GBP"));
    }

    @Test
    public void testBuyTicket() throws Exception {
        Town london = new Town();
        london.setName("London");
        Town bristol = new Town();
        bristol.setName("Bristol");

        Traveller traveller = new Traveller();
        traveller.setName("John Doe");
        traveller.setBalance(new BigDecimal("30.00"));

        when(travellerService.findByName("John Doe")).thenReturn(traveller);
        when(townService.findByName("London")).thenReturn(london);
        when(townService.findByName("Bristol")).thenReturn(bristol);

        String ticketJson = "{\"departure\":\"London\",\"arrival\":\"Bristol\",\"segments\":7,\"price\":25,\"travellerAmount\":30,\"traveller\":\"John Doe\"}";

        mockMvc.perform(post("/api/tickets/buy")
                        .contentType("application/json")
                        .content(ticketJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"))
                .andExpect(jsonPath("$.currency").value("GBP"));
    }
}


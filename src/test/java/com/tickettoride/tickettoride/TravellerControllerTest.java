package com.tickettoride.tickettoride;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.tickettoride.tickettoride.controller.TravellerController;
import com.tickettoride.tickettoride.entity.Traveller;
import com.tickettoride.tickettoride.service.TravellerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

public class TravellerControllerTest {

    @Mock
    private TravellerService travellerService;

    @InjectMocks
    private TravellerController travellerController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = standaloneSetup(travellerController).build();
    }

    @Test
    public void testGetAllTravellers() throws Exception {
        List<Traveller> travellers = List.of(new Traveller(), new Traveller());
        when(travellerService.getAllTravellers()).thenReturn(travellers);

        mockMvc.perform(get("/api/travellers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }
}


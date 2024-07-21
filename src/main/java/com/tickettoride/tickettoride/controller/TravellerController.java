package com.tickettoride.tickettoride.controller;

import com.tickettoride.tickettoride.entity.Traveller;
import com.tickettoride.tickettoride.service.TravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing travellers.
 */
@RestController
@RequestMapping("/api/travellers")
public class TravellerController {
    @Autowired
    private TravellerService travellerService;

    /**
     * Retrieves all travellers.
     *
     * @return a list of all travellers.
     */
    @GetMapping
    public List<Traveller> getAllTravellers() {
        return travellerService.getAllTravellers();
    }

    /**
     * Creates a new traveller.
     *
     * @param traveller the traveller to create.
     * @return the created traveller.
     */
    @PostMapping
    public Traveller createTraveller(@RequestBody Traveller traveller) {
        return travellerService.saveTraveller(traveller);
    }
}

package com.tickettoride.tickettoride.controller;

import com.tickettoride.tickettoride.entity.Traveller;
import com.tickettoride.tickettoride.service.TravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travellers")
public class TravellerController {
    @Autowired
    private TravellerService travellerService;

    @GetMapping
    public List<Traveller> getAllTravellers() {
        return travellerService.getAllTravellers();
    }

    @PostMapping
    public Traveller createTraveller(@RequestBody Traveller traveller) {
        return travellerService.saveTraveller(traveller);
    }
}

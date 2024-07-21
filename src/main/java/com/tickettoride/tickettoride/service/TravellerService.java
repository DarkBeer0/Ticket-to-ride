package com.tickettoride.tickettoride.service;

import com.tickettoride.tickettoride.entity.Traveller;
import com.tickettoride.tickettoride.repository.TravellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravellerService {
    @Autowired
    private TravellerRepository travellerRepository;

    public List<Traveller> getAllTravellers() {
        return travellerRepository.findAll();
    }

    public Traveller saveTraveller(Traveller traveller) {
        return travellerRepository.save(traveller);
    }

    public Traveller findById(Long id) {
        return travellerRepository.findById(id).orElseThrow(() -> new RuntimeException("Traveller not found"));
    }

    public Traveller findByName(String name) {
        return travellerRepository.findByName(name).orElseThrow(() -> new RuntimeException("Traveller not found"));
    }
}

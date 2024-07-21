package com.tickettoride.tickettoride.service;

import com.tickettoride.tickettoride.entity.Traveller;
import com.tickettoride.tickettoride.repository.TravellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing travellers.
 */
@Service
public class TravellerService {
    @Autowired
    private TravellerRepository travellerRepository;

    /**
     * Retrieves all travellers.
     *
     * @return a list of all travellers
     */
    public List<Traveller> getAllTravellers() {
        return travellerRepository.findAll();
    }

    /**
     * Saves a traveller.
     *
     * @param traveller the traveller to save
     * @return the saved traveller
     */
    public Traveller saveTraveller(Traveller traveller) {
        return travellerRepository.save(traveller);
    }

    /**
     * Finds a traveller by its ID.
     *
     * @param id the ID of the traveller
     * @return the found traveller
     * @throws RuntimeException if the traveller is not found
     */
    public Traveller findById(Long id) {
        return travellerRepository.findById(id).orElseThrow(() -> new RuntimeException("Traveller not found"));
    }

    /**
     * Finds a traveller by its name.
     *
     * @param name the name of the traveller
     * @return the found traveller
     * @throws RuntimeException if the traveller is not found
     */
    public Traveller findByName(String name) {
        return travellerRepository.findByName(name).orElseThrow(() -> new RuntimeException("Traveller not found"));
    }
}

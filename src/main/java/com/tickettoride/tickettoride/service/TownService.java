package com.tickettoride.tickettoride.service;

import com.tickettoride.tickettoride.entity.Town;
import com.tickettoride.tickettoride.repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing towns.
 */
@Service
public class TownService {
    @Autowired
    private TownRepository townRepository;

    /**
     * Retrieves all towns.
     *
     * @return a list of all towns
     */
    public List<Town> getAllTowns() {
        return townRepository.findAll();
    }

    /**
     * Saves a town.
     *
     * @param town the town to save
     * @return the saved town
     */
    public Town saveTown(Town town) {
        return townRepository.save(town);
    }

    /**
     * Finds a town by its name.
     *
     * @param name the name of the town
     * @return the found town
     */
    public Town findByName(String name) {
        return townRepository.findByName(name);
    }
}

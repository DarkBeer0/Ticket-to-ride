package com.tickettoride.tickettoride.service;

import com.tickettoride.tickettoride.entity.Town;
import com.tickettoride.tickettoride.repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TownService {
    @Autowired
    private TownRepository townRepository;

    public List<Town> getAllTowns() {
        return townRepository.findAll();
    }

    public Town saveTown(Town town) {
        return townRepository.save(town);
    }

    public Town findByName(String name) {
        return townRepository.findByName(name);
    }
}

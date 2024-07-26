package com.tickettoride.tickettoride.service;

import com.tickettoride.tickettoride.dto.RegisterTravellerDto;
import com.tickettoride.tickettoride.entity.Traveller;
import com.tickettoride.tickettoride.repository.TravellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service class for managing travellers.
 */
@Service
public class TravellerService {
    @Autowired
    private TravellerRepository travellerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Traveller> getAllTravellers() {
        return travellerRepository.findAll();
    }

    public Traveller saveTraveller(Traveller traveller) {
        traveller.setPassword(passwordEncoder.encode(traveller.getPassword()));
        return travellerRepository.save(traveller);
    }

    public Traveller registerTraveller(RegisterTravellerDto registerTravellerDto) {
        Traveller traveller = new Traveller();
        traveller.setName(registerTravellerDto.getName());
        traveller.setEmail(registerTravellerDto.getEmail());
        traveller.setPassword(passwordEncoder.encode(registerTravellerDto.getPassword()));
        traveller.setBalance(BigDecimal.ZERO);
        return travellerRepository.save(traveller);
    }

    public Traveller findById(Long id) {
        return travellerRepository.findById(id).orElseThrow(() -> new RuntimeException("Traveller not found"));
    }

    public Traveller findByName(String name) {
        return travellerRepository.findByName(name).orElseThrow(() -> new RuntimeException("Traveller not found"));
    }

    public Traveller findByEmail(String email) {
        return travellerRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Traveller not found"));
    }
}

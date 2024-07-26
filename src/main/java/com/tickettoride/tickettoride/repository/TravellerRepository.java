package com.tickettoride.tickettoride.repository;

import com.tickettoride.tickettoride.entity.Traveller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing Traveller entities.
 */
public interface TravellerRepository extends JpaRepository<Traveller, Long> {
    Optional<Traveller> findByName(String name);
    Optional<Traveller> findByEmail(String email);
}

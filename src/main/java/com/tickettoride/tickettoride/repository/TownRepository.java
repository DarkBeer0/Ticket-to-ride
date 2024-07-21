package com.tickettoride.tickettoride.repository;

import com.tickettoride.tickettoride.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TownRepository extends JpaRepository<Town, Long> {
    Town findByName(String name);
}

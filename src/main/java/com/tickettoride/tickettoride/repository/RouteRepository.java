package com.tickettoride.tickettoride.repository;

import com.tickettoride.tickettoride.entity.Route;
import com.tickettoride.tickettoride.entity.Town;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface RouteRepository extends CrudRepository<Route, Long> {

    @Query("SELECT r.segmentsCount as segments_count FROM Route r WHERE r.startTown = :startTown AND r.endTown = :endTown")
    Map<String, Object> findByStartTownAndEndTown(Town startTown, Town endTown);
}

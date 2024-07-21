package com.tickettoride.tickettoride.service;

import com.tickettoride.tickettoride.entity.Town;
import com.tickettoride.tickettoride.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service class for managing routes between towns.
 */
@Service
public class RouteService {
    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private PathFinderService pathFinderService;

    /**
     * Finds a route between two towns.
     *
     * @param start the starting town
     * @param end   the destination town
     * @return a map containing route information
     */
    public Map<String, Object> findRoute(Town start, Town end) {
        Map<String, Object> routeInfo = routeRepository.findByStartTownAndEndTown(start, end);
        if (routeInfo == null) {
            routeInfo = routeRepository.findByStartTownAndEndTown(end, start);
        }
        if (routeInfo == null) {
            int distance = pathFinderService.dijkstra(start.getName(), end.getName());
            if (distance == Integer.MAX_VALUE) {
                throw new RuntimeException("Route not found");
            }
            routeInfo = new HashMap<>();
            routeInfo.put("start_town", start.getName());
            routeInfo.put("end_town", end.getName());
            routeInfo.put("segments_count", distance);
        }
        return routeInfo;
    }
}

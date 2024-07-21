package com.tickettoride.tickettoride.controller;

import com.tickettoride.tickettoride.entity.Ticket;
import com.tickettoride.tickettoride.entity.Town;
import com.tickettoride.tickettoride.entity.Traveller;
import com.tickettoride.tickettoride.service.TicketService;
import com.tickettoride.tickettoride.service.TownService;
import com.tickettoride.tickettoride.service.TravellerService;
import com.tickettoride.tickettoride.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Controller for managing ticket-related operations.
 */
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TownService townService;

    @Autowired
    private TravellerService travellerService;

    @Autowired
    private RouteService routeService;

    /**
     * Calculates the price of the most optimal travel route between two towns.
     *
     * @param departure the departure town
     * @param arrival   the arrival town
     * @return a map containing the number of segments, price, and currency
     */
    @GetMapping("/calculate")
    public Map<String, Object> calculatePrice(@RequestParam String departure, @RequestParam String arrival) {
        Town start = townService.findByName(departure);
        Town end = townService.findByName(arrival);

        Map<String, Object> routeInfo = routeService.findRoute(start, end);
        int segmentsCount = (int) routeInfo.get("segments_count");
        BigDecimal price = calculatePrice(segmentsCount);

        return Map.of(
                "segments", segmentsCount,
                "price", price,
                "currency", "GBP"
        );
    }

    /**
     * Buys a ticket if the traveller has enough money.
     *
     * @param request a map containing the ticket details
     * @return a map indicating the result of the purchase
     */
    @PostMapping("/buy")
    public Map<String, Object> buyTicket(@RequestBody Map<String, Object> request) {
        String departure = (String) request.get("departure");
        String arrival = (String) request.get("arrival");
        int segmentsCount = (int) request.get("segments");
        BigDecimal price = new BigDecimal(request.get("price").toString());
        String travellerName = (String) request.get("traveller");

        Traveller traveller = travellerService.findByName(travellerName);

        if (traveller.getBalance().compareTo(price) >= 0) {
            Ticket ticket = new Ticket();
            ticket.setTraveller(traveller);
            ticket.setDepartureTown(townService.findByName(departure));
            ticket.setArrivalTown(townService.findByName(arrival));
            ticket.setSegmentsCount(segmentsCount);
            ticket.setPrice(price);
            ticket.setCurrency("GBP");
            ticketService.saveTicket(ticket);

            traveller.setBalance(traveller.getBalance().subtract(price));
            travellerService.saveTraveller(traveller);

            return Map.of(
                    "result", "success",
                    "change", traveller.getBalance(),
                    "currency", "GBP"
            );
        } else {
            return Map.of(
                    "result", "failure",
                    "lackOf", price.subtract(traveller.getBalance()),
                    "currency", "GBP"
            );
        }
    }

    /**
     * Calculates the price based on the number of segments.
     *
     * @param segmentsCount the number of segments
     * @return the calculated price
     */
    private BigDecimal calculatePrice(int segmentsCount) {
        int cost = 0;
        while (segmentsCount > 0) {
            if (segmentsCount >= 3) {
                cost += 10;
                segmentsCount -= 3;
            } else if (segmentsCount == 2) {
                cost += 7;
                segmentsCount -= 2;
            } else {
                cost += 5;
                segmentsCount -= 1;
            }
        }
        return BigDecimal.valueOf(cost);
    }
}

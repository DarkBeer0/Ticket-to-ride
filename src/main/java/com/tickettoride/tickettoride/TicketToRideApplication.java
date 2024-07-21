package com.tickettoride.tickettoride;

import com.tickettoride.tickettoride.controller.TicketController;
import com.tickettoride.tickettoride.entity.Town;
import com.tickettoride.tickettoride.entity.Traveller;
import com.tickettoride.tickettoride.service.TownService;
import com.tickettoride.tickettoride.service.TravellerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.HashMap;

@SpringBootApplication
public class TicketToRideApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketToRideApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(TownService townService, TravellerService travellerService, TicketController ticketController) {
		return (args) -> {
			Town london = townService.findByName("London");
			Town bristol = townService.findByName("Bristol");
			Town coventry = townService.findByName("Coventry");
			Town swindon = townService.findByName("Swindon");

			Traveller traveller = travellerService.findById(1L);

			createAndSaveTicket(ticketController, traveller, london, bristol);
			createAndSaveTicket(ticketController, traveller, coventry, bristol);
			createAndSaveTicket(ticketController, traveller, london, swindon);
		};
	}

	private void createAndSaveTicket(TicketController ticketController, Traveller traveller, Town departureTown, Town arrivalTown) {
		var priceInfo = ticketController.calculatePrice(departureTown.getName(), arrivalTown.getName());
		int segmentsCount = (int) priceInfo.get("segments");
		BigDecimal price = (BigDecimal) priceInfo.get("price");

		var saveTicketRequest = new HashMap<String, Object>();
		saveTicketRequest.put("departure", departureTown.getName());
		saveTicketRequest.put("arrival", arrivalTown.getName());
		saveTicketRequest.put("segments", segmentsCount);
		saveTicketRequest.put("price", price);
		saveTicketRequest.put("currency", "GBP");
		saveTicketRequest.put("travellerAmount", traveller.getBalance());
		saveTicketRequest.put("traveller", traveller.getName());

		ticketController.buyTicket(saveTicketRequest);
	}
}

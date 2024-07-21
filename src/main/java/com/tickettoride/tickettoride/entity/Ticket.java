package com.tickettoride.tickettoride.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "traveller_id", nullable = false)
    private Traveller traveller;

    @ManyToOne
    @JoinColumn(name = "departure_town_id", nullable = false)
    private Town departureTown;

    @ManyToOne
    @JoinColumn(name = "arrival_town_id", nullable = false)
    private Town arrivalTown;

    @Column(name = "segments_count", nullable = false)
    private int segmentsCount;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String currency;

    @Column(name = "purchase_time", nullable = false)
    private LocalDateTime purchaseTime = LocalDateTime.now();

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Traveller getTraveller() {
        return traveller;
    }

    public void setTraveller(Traveller traveller) {
        this.traveller = traveller;
    }

    public Town getDepartureTown() {
        return departureTown;
    }

    public void setDepartureTown(Town departureTown) {
        this.departureTown = departureTown;
    }

    public Town getArrivalTown() {
        return arrivalTown;
    }

    public void setArrivalTown(Town arrivalTown) {
        this.arrivalTown = arrivalTown;
    }

    public int getSegmentsCount() {
        return segmentsCount;
    }

    public void setSegmentsCount(int segmentsCount) {
        this.segmentsCount = segmentsCount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }
}

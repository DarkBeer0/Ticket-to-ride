package com.tickettoride.tickettoride.entity;

import jakarta.persistence.*;

/**
 * Entity representing a route between two towns.
 */
@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "start_town_id", nullable = false)
    private Town startTown;

    @ManyToOne
    @JoinColumn(name = "end_town_id", nullable = false)
    private Town endTown;

    @Column(name = "segments_count", nullable = false)
    private int segmentsCount;

    public int getSegmentsCount() {
        return segmentsCount;
    }

    public Town getEndTown() {
        return endTown;
    }

    public Town getStartTown() {
        return startTown;
    }
}

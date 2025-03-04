package com.tickettoride.tickettoride.service;

import com.tickettoride.tickettoride.entity.Segment;
import com.tickettoride.tickettoride.repository.SegmentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service class for finding the shortest path between towns using Dijkstra's algorithm.
 */
@Service
public class PathFinderService {
    private Map<String, List<Edge>> adjList = new HashMap<>();

    @Autowired
    private SegmentRepository segmentRepository;

    /**
     * Initializes the adjacency list from the segments stored in the repository.
     */
    @PostConstruct
    public void init() {
        List<Segment> segments = segmentRepository.findAll();
        for (Segment segment : segments) {
            addEdge(segment.getFromCity(), segment.getToCity(), segment.getDistance());
        }
    }

    /**
     * Adds an edge to the adjacency list.
     *
     * @param from   the starting city
     * @param to     the destination city
     * @param weight the weight of the edge
     */
    public void addEdge(String from, String to, int weight) {
        adjList.putIfAbsent(from, new ArrayList<>());
        adjList.putIfAbsent(to, new ArrayList<>());
        adjList.get(from).add(new Edge(to, weight));
        adjList.get(to).add(new Edge(from, weight));
    }

    /**
     * Finds the shortest path between two cities using Dijkstra's algorithm.
     *
     * @param start the starting city
     * @param end   the destination city
     * @return the distance of the shortest path
     */
    public int dijkstra(String start, String end) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        Map<String, Integer> distances = new HashMap<>();
        Set<String> visited = new HashSet<>();

        for (String city : adjList.keySet()) {
            distances.put(city, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            if (!visited.add(current.to)) continue;

            for (Edge edge : adjList.get(current.to)) {
                if (!visited.contains(edge.to)) {
                    int newDist = distances.get(current.to) + edge.weight;
                    if (newDist < distances.get(edge.to)) {
                        distances.put(edge.to, newDist);
                        pq.add(new Edge(edge.to, newDist));
                    }
                }
            }
        }
        return distances.get(end);
    }

    /**
     * Inner class representing an edge in the graph.
     */
    static class Edge {
        String to;
        int weight;

        Edge(String to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}

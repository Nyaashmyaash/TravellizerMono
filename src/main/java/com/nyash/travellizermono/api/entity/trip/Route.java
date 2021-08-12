package com.nyash.travellizermono.api.entity.trip;

import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Route that links stations using any kind of transport (bus, train, air, water)
 *
 * @author Nyash
 */
@Setter
public class Route {

    /**
     * Starting point of the route
     */
    private String start;

    /**
     * Endpoint of the route
     */
    private String destination;

    /**
     * Start time of the route
     */
    private LocalDateTime startTime;

    /**
     * End time of the route
     */
    private LocalDateTime endTime;

    /**
     * Current price of the route
     */
    private double price;

    /**
     * Set of trips for the specified route
     */
    private Set<Trip> trips;

    public Route() {
    }

    public String getStart() {
        return start;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public double getPrice() {
        return price;
    }

    public Set<Trip> getTrips() {
        return trips;
    }

    /**
     * Adds specified trip to the trips list
     *
     * @param trip
     */
    public Trip addTrip(final Trip trip) {
        if (trips == null) {
            trips = new HashSet<>();
        }
        trips.add(trip);
        trip.setRoute(this);

        return trip;
    }
}

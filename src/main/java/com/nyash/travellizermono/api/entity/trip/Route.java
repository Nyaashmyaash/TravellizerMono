package com.nyash.travellizermono.api.entity.trip;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Route that links stations using any kind of transport (bus, train, air, water)
 *
 * @author Nyash
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "ROUTE")
public class Route {

    /**
     * Current order id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * Starting point of the route
     */
    @NonNull
    @Column(name = "START_ID")
    String start;

    /**
     * Endpoint of the route
     */
    @NonNull
    @Column(name = "DESTINATION_ID")
    String destination;

    /**
     * Start time of the route
     */
    LocalDateTime startTime;

    /**
     * End time of the route
     */
    LocalDateTime endTime;

    /**
     * Current price of the route
     */
    Double price;

    /**
     * Set of trips for the specified route
     */
    Set<Trip> trips;

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

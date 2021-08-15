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
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "ROUTE")
public class RouteEntity {

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
    @NonNull
    @Column(name = "START_TIME")
    LocalDateTime startTime;

    /**
     * End time of the route
     */
    @NonNull
    @Column(name = "END_TIME")
    LocalDateTime endTime;

    /**
     * Current price of the route
     */
    @Column(name = "PRICE")
    Double price;

    /**
     * Set of trips for the specified route
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "routeEntity", orphanRemoval = true)
    Set<TripEntity> tripEntities;

    /**
     * Adds specified trip to the trips list
     *
     * @param tripEntity
     */
    public TripEntity addTrip(final TripEntity tripEntity) {
        if (tripEntities == null) {
            tripEntities = new HashSet<>();
        }
        tripEntities.add(tripEntity);
        tripEntity.setRouteEntity(this);

        return tripEntity;
    }
}

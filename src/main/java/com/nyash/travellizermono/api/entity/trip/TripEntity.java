package com.nyash.travellizermono.api.entity.trip;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Concrete trip according the given route at the specified date/time
 *
 * @author Nyash
 *
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "TRIP", indexes = @Index(columnList = "ROUTE_ID, START_DATE, START_TIME", unique = true))
public class TripEntity {

    /**
     * Current order id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * Route of the trip
     */
    @NonNull
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "ROUTE_ID")
    RouteEntity routeEntity;

    /**
     * Date of the trip start
     */
    @NonNull
    @Column(name = "START_DATE")
    LocalDate startDate;

    /**
     * Date of the trip finish
     */
    @NonNull
    @Column(name = "END_DATE")
    LocalDate endDate;

    /**
     * Time of the trip start
     */
    @NonNull
    @Column(name = "START_TIME", columnDefinition = "time")
    LocalTime startTime;

    /**
     * Time of the trip finish
     */
    @NonNull
    @Column(name = "END_TIME", columnDefinition = "time")
    LocalTime endTime;

    /**
     * Maximum number of seats that trip can have
     */
    @NonNull
    @Column(name = "MAX_SEATS")
    Integer maxSeats;

    /**
     * Remaining number of available seats
     */
    @NonNull
    @Column(name = "AVAILABLE_SEATS")
    Integer availableSeats;

    /**
     * Current price of the ticket
     */
    @NonNull
    @Column(name = "PRICE")
    Double price;

}

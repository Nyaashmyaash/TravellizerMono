package com.nyash.travellizermono.api.entity.trip;

import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Concrete trip according the given route at the specified date/time
 *
 * @author Nyash
 *
 */
@Setter
public class Trip {

    /**
     * Route of the trip
     */
    private Route route;

    /**
     * Date of the trip start
     */
    private LocalDate startDate;

    /**
     * Date of the trip finish
     */
    private LocalDate endDate;

    /**
     * Time of the trip start
     */
    private LocalTime startTime;

    /**
     * Time of the trip finish
     */
    private LocalTime endTime;

    /**
     * Maximum number of seats that trip can have
     */
    private int maxSeats;

    /**
     * Remaining number of available seats
     */
    private int availableSeats;

    /**
     * Current price of the ticket
     */
    private double price;

    public Trip() {
    }

    public Route getRoute() {
        return route;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getPrice() {
        return price;
    }
}

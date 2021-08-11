package com.nyash.travellizermono.api.entity.geography;

import java.util.Objects;

/**
 * Station where passengers can get off or take specific kind of transport.
 * Multiple stationts compose route of the trip.
 *
 * @author Nyash
 */
public class Station {

    City city;

    Address address;

    /**
     * Optional.
     * if station have office
     */
    String phone;

    Coordinate coordinate;

    TransportType transportType;

}

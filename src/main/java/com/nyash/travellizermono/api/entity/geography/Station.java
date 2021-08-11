package com.nyash.travellizermono.api.entity.geography;

import com.nyash.travellizer.model.entity.geography.search.criteria.StationCriteria;
import org.thymeleaf.util.StringUtils;

import java.util.Objects;

/**
 * Station where passengers can get off or take specific kind of transport.
 * Multiple stationts compose route of the trip.
 *
 * @author Nyash
 */
public class Station {
    private com.nyash.travellizer.model.entity.geography.City city;

    private Address address;

    /**
     * Optional.
     * if station have office
     */
    private String phone;

    private com.nyash.travellizer.model.entity.geography.Coordinate coordinate;

    private TransportType transportType;

    public Station() {
    }

    public Station(final com.nyash.travellizer.model.entity.geography.City city, final TransportType transportType) {
        this.city = city;
        this.transportType = transportType;
    }

    public com.nyash.travellizer.model.entity.geography.City getCity() {
        return city;
    }

    public void setCity(com.nyash.travellizer.model.entity.geography.City city) {
        this.city = city;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public com.nyash.travellizer.model.entity.geography.Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(com.nyash.travellizer.model.entity.geography.Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    /**
     * Verifies if current station matches specified criteria
     *
     * @param criteria
     * @return
     */
    public boolean match(final StationCriteria criteria) {
        Objects.requireNonNull(criteria, "Station criteria is not initialized");

        if (!StringUtils.isEmpty(criteria.getName())) {
            if (!city.getName().equals(criteria.getName())) {
                return false;
            }
        }

        if (criteria.getTransportType(transportType) != null) {
            if (transportType != criteria.getTransportType(transportType)) {
                return false;
            }
        }
        return true;
    }
}

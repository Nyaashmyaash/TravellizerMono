package com.nyash.travellizermono.api.entity.geography;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Objects;

/**
 * Station where passengers can get off or take specific kind of transport.
 * Multiple stationts compose route of the trip.
 *
 * @author Nyash
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
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

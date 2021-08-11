package com.nyash.travellizermono.api.entity.geography;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

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
    @JoinColumn(name = "city_id")
    City city;

    @Embedded
    Address address;

    /**
     * Optional.
     * if station have office
     */
    @NonNull
    @Column(name = "phone")
    String phone;

    @Embedded
    Coordinate coordinate;

    @Enumerated
    @Column(nullable = false, name = "transport_type")
    TransportType transportType;

}

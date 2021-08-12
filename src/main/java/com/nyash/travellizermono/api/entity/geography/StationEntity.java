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
@Table(name = "STATION")
public class StationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NonNull
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "CITY_ID")
    CityEntity cityEntity;

    @Embedded
    AddressEntity addressEntity;

    /**
     * Optional.
     * if station have office
     */
    @NonNull
    @Column(name = "PHONE")
    String phone;

    @Embedded
    CoordinateEntity coordinateEntity;

    @Enumerated
    @Column(nullable = false, name = "TRANSPORT_TYPE")
    TransportType transportType;

}

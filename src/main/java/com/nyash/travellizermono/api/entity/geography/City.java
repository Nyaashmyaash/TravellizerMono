package com.nyash.travellizermono.api.entity.geography;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

/**
 * Any locality that contains transport stations
 *
 * @author Nyash
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NonNull
    @Column(name = "name")
    String name;

    /**
     * Name of the district where city is placed
     */
    @NonNull
    @Column(name = "district")
    String district;

    /**
     * Name of the region where district is located. Region is top-level area in the
     * country
     */
    @NonNull
    @Column(name = "region")
    String region;

    /**
     * Set of transport stations that is linked to this locality
     */
    @Column(name = "stations")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city", orphanRemoval = true)
    Set<Station> stations;


//    /**
//     * Adds specified station to the city station list
//     *
//     * @param station
//     */
//    public Station addStation(final TransportType transportType) {
//        if (stations == null) {
//            stations = new HashSet<>();
//        }
//        Station station = new Station(this, transportType);
//        stations.add(station);
//
//        return station;
//    }
//
//    /**
//     * Removes specified station from city station list
//     *
//     * @param station
//     */
//    public void removeStation(final Station station) {
//        Objects.requireNonNull(station, "station parameter is not initialized");
//        if (stations == null) {
//            return;
//        }
//        stations.remove(station);
//    }

}

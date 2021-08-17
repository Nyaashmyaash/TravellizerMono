package com.nyash.travellizermono.api.entity.geography;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Any locality that contains transport stations
 *
 * @author Nyash
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "CITY")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NonNull
    @Column(name = "NAME")
    String name;

    /**
     * Name of the district where city is placed
     */
    @NonNull
    @Column(name = "DISTRICT")
    String district;

    /**
     * Name of the region where district is located. Region is top-level area in the
     * country
     */
    @NonNull
    @Column(name = "REGION")
    String region;

    /**
     * Set of transport stations that is linked to this locality
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "STATION_ID")
    Set<StationEntity> stationEntities;

    public static CityEntity makeDefault(
            String name,
            String district,
            String region) {

        return builder()
                .name(name)
                .district(district)
                .region(region)
                .build();
    }


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

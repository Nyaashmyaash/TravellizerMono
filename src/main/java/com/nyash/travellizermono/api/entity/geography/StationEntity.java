package com.nyash.travellizermono.api.entity.geography;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Getter
@Setter
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

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "CITY_ID")
    CityEntity cityEntity;

    @NonNull
    @Column(name = "STATION_NAME")
    String stationName;

    @NonNull
    @Column(name = "ZIP_CODE", length = 10)
    String zipCode;

    @NonNull
    @Column(name = "STREET", length = 32)
    String street;

    @NonNull
    @Column(name = "HOUSE_NUMBER", length = 16)
    String houseNumber;

    /**
     * (Optional) if it's address of the apartment
     */
    @Column(name = "APARTMENT", length = 16)
    String apartment;

    /**
     * Optional.
     * if station have office
     */
    @NonNull
    @Column(name = "PHONE")
    String phone;

    /**
     * Station coordinates. X.
     */
    @Column(name = "X")
    double x;

    /**
     * Station coordinates. Y.
     */
    @Column(name = "Y")
    double y;

    @Enumerated
    @Column(nullable = false, name = "TRANSPORT_TYPE")
    TransportType transportType;

    /**
     * You shouldn't create station object directly. Use {@link CityEntity} functionality
     * instead
     *
     * @param cityEntity
     * @param transportType
     */
    public StationEntity(final CityEntity cityEntity, final TransportType transportType) {
        this.cityEntity = Objects.requireNonNull(cityEntity);
        this.transportType = Objects.requireNonNull(transportType);
    }


    public static StationEntity makeDefault(
            String stationName,
            CityEntity cityEntity,
            String zipCode,
            String street,
            String houseNumber,
            String apartment,
            String phone,
            double x,
            double y,
            TransportType transportType) {

        return builder()
                .stationName(stationName)
                .cityEntity(cityEntity)
                .zipCode(zipCode)
                .street(street)
                .houseNumber(houseNumber)
                .apartment(apartment)
                .phone(phone)
                .x(x)
                .y(y)
                .transportType(transportType)
                .build();
    }
}

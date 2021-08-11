package com.nyash.travellizermono.api.entity.geography;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

/**
 * Value type that stores address attributes
 * of the specific office or person
 *
 * @author Nyash
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NonNull
    @Column(name = "zip_code")
    String zipCode;

    @NonNull
    @Column(name = "street")
    String street;

    @NonNull
    @Column(name = "house_number")
    String houseNumber;

    /**
     * (Optional) if it's address of the apartment
     */
    @Column(name = "apartment")
    String apartment;

}
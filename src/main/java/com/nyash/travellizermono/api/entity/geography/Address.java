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
@Embeddable
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NonNull
    @Column(name = "zip_code", length = 10)
    String zipCode;

    @NonNull
    @Column(name = "street", length = 32)
    String street;

    @NonNull
    @Column(name = "house_number", length = 16)
    String houseNumber;

    /**
     * (Optional) if it's address of the apartment
     */
    @Column(name = "apartment", length = 16)
    String apartment;

}
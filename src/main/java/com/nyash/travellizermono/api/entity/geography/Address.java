package com.nyash.travellizermono.api.entity.geography;

/**
 * Value type that stores address attributes
 * of the specific office or person
 *
 * @author Nyash
 */
public class Address {
    private String zipCode;

    private String street;

    private String houseNumber;
    /**
     * (Optional) if it's address of the apartment
     */
    private String apartment;

}
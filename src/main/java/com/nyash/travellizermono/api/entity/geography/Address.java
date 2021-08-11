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

    public Address() {
    }

    public Address(String zipCode, String street, String houseNumber, String apartment) {
        this.zipCode = zipCode;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartment = apartment;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }
}

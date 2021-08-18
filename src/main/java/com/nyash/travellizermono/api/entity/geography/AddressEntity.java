//package com.nyash.travellizermono.api.entity.geography;
//
//import lombok.*;
//import lombok.experimental.FieldDefaults;
//
//import javax.persistence.*;
//
///**
// * Value type that stores address attributes
// * of the specific office or person
// *
// * @author Nyash
// */
//
//@Getter
//@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//@Embeddable
//public class AddressEntity {
//
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    Long id;
//
//    @NonNull
//    @Column(name = "ZIP_CODE", length = 10)
//    String zipCode;
//
//    @NonNull
//    @Column(name = "STREET", length = 32)
//    String street;
//
//    @NonNull
//    @Column(name = "HOUSE_NUMBER", length = 16)
//    String houseNumber;
//
//    /**
//     * (Optional) if it's address of the apartment
//     */
//    @Column(name = "APARTMENT", length = 16)
//    String apartment;
//
//}
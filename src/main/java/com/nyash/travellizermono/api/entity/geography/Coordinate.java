package com.nyash.travellizermono.api.entity.geography;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Geographical coordinate of an object
 *
 * @author Nyash
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Embeddable
public class Coordinate {


    @Column(name = "x")
    double x;

    @Column(name = "y")
    double y;

}

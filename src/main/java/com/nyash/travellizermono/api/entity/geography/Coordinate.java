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


    @Column(name = "X")
    double x;

    @Column(name = "Y")
    double y;

}

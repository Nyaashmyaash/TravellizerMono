package com.nyash.travellizermono.api.entity.geography;

import java.util.Set;

/**
 * Any locality that contains transport stations
 *
 * @author Nyash
 */
public class City {
    private String name;

    /**
     * Name of the district where city is placed
     */
    private String district;

    /**
     * Name of the region where district is located. Region is top-level area in the
     * country
     */
    private String region;

    /**
     * Set of transport stations that is linked to this locality
     */
    private Set<Station> stations;

}

package com.nyash.travellizermono.api.entity;

import com.nyash.travellizermono.api.entity.geography.CityEntity;
import com.nyash.travellizermono.api.entity.geography.StationEntity;
import com.nyash.travellizermono.api.entity.geography.TransportType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Contains unit-tests to check functionality of {@link CityEntity} class
 *
 * @author Nyash
 */
public class CityEntityTest {

    private CityEntity city;

    @BeforeEach
    void setup() {
        city = new CityEntity("London");
    }

    @Nested
    @DisplayName("Checks addStation method")
    class AddStationTest {
        @Test
        public void successIfValidStation() {
            StationEntity station = new StationEntity(city, TransportType.ROADWAY);
            city.addStation(station);

            assertTrue(containsStation(city, station));
            assertEquals(city, station.getCityEntity());
        }
    }

    @Nested
    @DisplayName("Checks addStation method")
    class RemoveStationTest {
        @Test
        void success() {
            StationEntity station = new StationEntity(city, TransportType.AIR);
            city.addStation(station);
            city.removeStation(station);

            assertTrue(city.getStationEntities().isEmpty());
        }

        @Test
        void throwsExceptionIfStationNull() {
            assertThrows(NullPointerException.class, () -> city.removeStation(null));
        }
    }

    private boolean containsStation(CityEntity city, StationEntity station) {
        return city.getStationEntities().contains(station);
    }
}

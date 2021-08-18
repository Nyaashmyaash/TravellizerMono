package com.nyash.travellizermono.api.repository;

import com.nyash.travellizermono.api.entity.geography.StationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Defines CRUD methods to access Station objects in the persistent storage
 * @author Nyash
 */
public interface StationRepository extends JpaRepository<StationEntity, Long> {

    //TODO: actual filter criteria

    @Query("SELECT s FROM StationEntity s " +
            "WHERE :isFiltered = FALSE " +
            "OR (LOWER(s.stationName) LIKE LOWER(CONCAT('%', :filter, '%'))) " +
            "AND s.cityEntity.id =:cityId " +
            "ORDER BY s.stationName")
    List<StationEntity> findAllByFilterAndCityId(boolean isFiltered, String filter, Long cityId);

    @Query("SELECT s FROM StationEntity s where s.cityEntity.id =:cityId")
    List<StationEntity> findAllByCityId(Long cityId);


    StationEntity getByStationName(String stationName);
}

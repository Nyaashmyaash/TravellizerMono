package com.nyash.travellizermono.api.repository;

import com.nyash.travellizermono.api.entity.geography.StationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StationRepository extends JpaRepository<StationEntity, Long> {

    @Query("SELECT s FROM StationEntity s " +
            "WHERE :isFiltered = FALSE " +
            "OR (LOWER(s.stationName) LIKE LOWER(CONCAT('%', :filter, '%')))" +
            "ORDER BY s.stationName")
    List<StationEntity> findAllByFilter(boolean isFiltered, String filter);
}

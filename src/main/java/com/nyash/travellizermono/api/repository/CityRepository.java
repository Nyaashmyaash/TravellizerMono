package com.nyash.travellizermono.api.repository;

import com.nyash.travellizermono.api.entity.geography.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

/**
 * Defines CRUD methods to access City objects in the persistent storage
 * @author Nyash
 */
public interface CityRepository extends JpaRepository<CityEntity, Long> {

    @Query("SELECT c FROM CityEntity c " +
            "WHERE :isFiltered = FALSE " +
            "OR (LOWER(c.name) LIKE LOWER(CONCAT('%', :filter, '%')))" +
            "ORDER BY c.name")
    List<CityEntity> findAllByFilter(boolean isFiltered, String filter);

   Optional<CityEntity> findByName(String name);
}

package com.nyash.travellizermono.api.repository;

import com.nyash.travellizermono.api.entity.geography.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEntity, Long> {
}

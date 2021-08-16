package com.nyash.travellizermono.api.repository;

import com.nyash.travellizermono.api.entity.geography.StationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<StationEntity, Long> {
}

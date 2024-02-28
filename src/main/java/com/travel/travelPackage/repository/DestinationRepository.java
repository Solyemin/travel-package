package com.travel.travelPackage.repository;

import com.travel.travelPackage.entity.DestinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<DestinationEntity, Long> {
}

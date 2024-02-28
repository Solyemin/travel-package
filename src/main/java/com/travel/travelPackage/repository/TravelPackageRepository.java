package com.travel.travelPackage.repository;

import com.travel.travelPackage.entity.TravelPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelPackageRepository extends JpaRepository<TravelPackageEntity, Long> {
}

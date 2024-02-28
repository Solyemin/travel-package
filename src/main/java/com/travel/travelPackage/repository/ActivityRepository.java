package com.travel.travelPackage.repository;

import com.travel.travelPackage.entity.ActivityEntity;
import com.travel.travelPackage.entity.DestinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {

    @Query("SELECT a FROM ActivityEntity a WHERE a.capacity > 0")
    List<ActivityEntity> findActivitiesWithSpacesAvailable();

    @Query("SELECT a FROM ActivityEntity a WHERE a.capacity > 0 AND a.destination IN :itinerary")
    List<ActivityEntity> findAvailableActivitiesInItinerary(@Param("itinerary") List<DestinationEntity> itinerary);
}

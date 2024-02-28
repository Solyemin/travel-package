package com.travel.travelPackage.repository;

import com.travel.travelPackage.entity.PassengerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<PassengerEntity, Long> {
}

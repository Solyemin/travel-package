package com.travel.travelPackage.service;

import com.travel.travelPackage.entity.PassengerEntity;
import com.travel.travelPackage.entity.TravelPackageEntity;

import java.util.List;

public interface TravelPackageService {
    TravelPackageEntity getTravelPackageById(Long id);

    TravelPackageEntity addTravelPackage(TravelPackageEntity travelPackage);

    TravelPackageEntity updateTravelPackage(Long id, TravelPackageEntity updatedTravelPackage);

    void deleteTravelPackage(Long id);

    String printItinerary(Long travelPackageId);
    String printPassengerList(Long travelPackageId);
}

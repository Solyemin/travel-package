package com.travel.travelPackage.service;

import com.travel.travelPackage.entity.DestinationEntity;

import java.util.List;

public interface DestinationService {
    List<DestinationEntity> getAllDestinations();

    DestinationEntity getDestinationById(Long id);

    DestinationEntity addDestination(DestinationEntity destination);

    DestinationEntity updateDestination(Long id, DestinationEntity updatedDestination);

    void deleteDestination(Long id);
}

package com.travel.travelPackage.service;

import com.travel.travelPackage.entity.*;

import java.util.List;

public interface PassengerService {
    PassengerEntity getPassengerById(Long id);
    List<PassengerEntity> getAllPassengers();
    PassengerEntity addPassenger(PassengerEntity passenger);

    PassengerEntity updatePassenger(Long passengerId, PassengerEntity updatedPassenger);

    void deletePassenger(Long id);

    String printPassengerDetails(Long passengerId);

}

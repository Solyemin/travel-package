package com.travel.travelPackage.service;

import com.travel.travelPackage.entity.ActivityEntity;
import com.travel.travelPackage.entity.DestinationEntity;
import com.travel.travelPackage.entity.PassengerEntity;
import com.travel.travelPackage.entity.TravelPackageEntity;
import com.travel.travelPackage.repository.TravelPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelPackageServiceImpl implements TravelPackageService {
    private final TravelPackageRepository travelPackageRepository;

    @Autowired
    public TravelPackageServiceImpl(TravelPackageRepository travelPackageRepository) {
        this.travelPackageRepository = travelPackageRepository;
    }

    @Override
    public TravelPackageEntity getTravelPackageById(Long id) {
        Optional<TravelPackageEntity> travelPackageOptional = travelPackageRepository.findById(id);
        return travelPackageOptional.orElse(null);
    }

    @Override
    public TravelPackageEntity addTravelPackage(TravelPackageEntity travelPackage) {
        return travelPackageRepository.save(travelPackage);
    }

    @Override
    public TravelPackageEntity updateTravelPackage(Long travelPackageId, TravelPackageEntity updatedTravelPackage) {
        if (travelPackageRepository.existsById(travelPackageId)) {
            updatedTravelPackage.setId(travelPackageId);
            return travelPackageRepository.save(updatedTravelPackage);
        } else {
            throw new RuntimeException("Travel Package not found");
        }
    }

    @Override
    public void deleteTravelPackage(Long id) {
        if (!travelPackageRepository.existsById(id)) {
            throw new RuntimeException("Travel package not found");
        }

        travelPackageRepository.deleteById(id);
    }


    @Override
    public String printItinerary(Long travelPackageId) {
        TravelPackageEntity travelPackage = travelPackageRepository.findById(travelPackageId)
                .orElseThrow(() -> new RuntimeException("Travel package not found"));

        StringBuilder itinerary = new StringBuilder();
        itinerary.append("Travel Package Name: ").append(travelPackage.getName()).append("\n");
        itinerary.append("Destinations and Activities:\n");

        for (DestinationEntity destination : travelPackage.getItinerary()) {
            itinerary.append("Destination: ").append(destination.getName()).append("\n");

            for (ActivityEntity activity : destination.getActivities()) {
                itinerary.append("\tActivity: ").append(activity.getName())
                        .append(", Cost: ").append(activity.getCost())
                        .append(", Capacity: ").append(activity.getCapacity())
                        .append(", Description: ").append(activity.getDescription()).append("\n");
            }
        }

        return itinerary.toString();
    }

    public String printPassengerList(Long travelPackageId) {
        TravelPackageEntity travelPackage = travelPackageRepository.findById(travelPackageId)
                .orElseThrow(() -> new RuntimeException("Travel package not found"));

        StringBuilder passengerList = new StringBuilder();
        passengerList.append("Travel Package Name: ").append(travelPackage.getName()).append("\n");
        passengerList.append("Passenger List:\n");

        for (PassengerEntity passenger : travelPackage.getPassengers()) {
            passengerList.append("Passenger Name: ").append(passenger.getName())
                    .append(", Passenger Number: ").append(passenger.getPassengerNumber()).append("\n");
        }

        return passengerList.toString();
    }


}


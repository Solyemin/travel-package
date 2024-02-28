package com.travel.travelPackage.service;

import com.travel.travelPackage.entity.*;
import com.travel.travelPackage.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;

    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public List<PassengerEntity> getAllPassengers() {
        return passengerRepository.findAll();
    }

    @Override
    public PassengerEntity getPassengerById(Long id) {
        Optional<PassengerEntity> passengerOptional = passengerRepository.findById(id);
        return passengerOptional.orElse(null);
    }

    @Override
    public PassengerEntity addPassenger(PassengerEntity passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public PassengerEntity updatePassenger(Long passengerId, PassengerEntity updatedPassenger) {
        if (passengerRepository.existsById(passengerId)) {
            updatedPassenger.setId(passengerId);
            return passengerRepository.save(updatedPassenger);
        } else {
            throw new RuntimeException("Passenger not found");
        }
    }

    @Override
    public void deletePassenger(Long id) {
        if (!passengerRepository.existsById(id)) {
            throw new RuntimeException("Passenger not found");
        }

        passengerRepository.deleteById(id);
    }

    @Override
    public String printPassengerDetails(Long passengerId) {
        PassengerEntity passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        StringBuilder passengerDetails = new StringBuilder();
        passengerDetails.append("Passenger Name: ").append(passenger.getName()).append("\n");
        passengerDetails.append("Passenger Number: ").append(passenger.getPassengerNumber()).append("\n");
        passengerDetails.append("Passenger Type: ").append(passenger.getPassenger_type()).append("\n");

        if (passenger.getBalance() != 0.0) {
            passengerDetails.append("Balance: ").append(passenger.getBalance()).append("\n");
        }

        List<ActivityEntity> activities = passenger.getActivities();
        if (!activities.isEmpty()) {
            passengerDetails.append("Activities Signed Up For:\n");
            for (ActivityEntity activity : activities) {
                passengerDetails.append("\tActivity: ").append(activity.getName())
                        .append(", Destination: ").append(activity.getDestination().getName())
                        .append(", Price Paid: ").append(calculatePriceForPassenger(passenger, activity)).append("\n");
            }
        } else {
            passengerDetails.append("No activities signed up for.\n");
        }

        return passengerDetails.toString();
    }

    double calculatePriceForPassenger(PassengerEntity passenger, ActivityEntity activity) {
        if ("Standard".equals(passenger.getPassenger_type())) {
            return activity.getCost();
        } else if ("Gold".equals(passenger.getPassenger_type())) {
            return activity.getCost() * 0.9; // 10% discount for gold passengers
        } else {
            return 0.0; // Premium passengers sign up for activities for free
        }
    }




}


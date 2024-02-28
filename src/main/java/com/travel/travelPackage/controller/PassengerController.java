package com.travel.travelPackage.controller;

import com.travel.travelPackage.entity.ActivityEntity;
import com.travel.travelPackage.entity.PassengerEntity;
import com.travel.travelPackage.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;
    @GetMapping("/all")
    public List<PassengerEntity> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping("/{id}")
    public PassengerEntity getPassengerById(@PathVariable Long id) {
        return passengerService.getPassengerById(id);
    }

    @PostMapping
    public PassengerEntity createPassenger(@RequestBody PassengerEntity passenger) {
        return passengerService.addPassenger(passenger);
    }

    @PutMapping("/{passengerId}")
    public PassengerEntity updatePassenger(@PathVariable Long passengerId, @RequestBody PassengerEntity updatedPassenger) {
        return passengerService.updatePassenger(passengerId, updatedPassenger);
    }

    @DeleteMapping("/{passengerId}")
    public void deletePassenger(@PathVariable Long passengerId) {
        passengerService.deletePassenger(passengerId);
    }

    @GetMapping("/{passengerId}/details")
    public String printPassengerDetails(@PathVariable Long passengerId) {
        return passengerService.printPassengerDetails(passengerId);
    }
}


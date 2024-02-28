package com.travel.travelPackage.controller;

import com.travel.travelPackage.entity.DestinationEntity;
import com.travel.travelPackage.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @GetMapping("/all")
    public List<DestinationEntity> getAllDestinations() {
        return destinationService.getAllDestinations();
    }

    @GetMapping("/{destinationId}")
    public DestinationEntity getDestinationById(@PathVariable Long destinationId) {
        return destinationService.getDestinationById(destinationId);
    }

    @PostMapping
    public DestinationEntity createDestination(@RequestBody DestinationEntity destination) {
        return destinationService.addDestination(destination);
    }

    @PutMapping("/{destinationId}")
    public DestinationEntity updateDestination(@PathVariable Long destinationId, @RequestBody DestinationEntity updatedDestination) {
        return destinationService.updateDestination(destinationId, updatedDestination);
    }

    @DeleteMapping("/{destinationId}")
    public void deleteDestination(@PathVariable Long destinationId) {
        destinationService.deleteDestination(destinationId);
    }

}


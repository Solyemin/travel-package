package com.travel.travelPackage.controller;

import com.travel.travelPackage.entity.ActivityEntity;
import com.travel.travelPackage.entity.PassengerEntity;
import com.travel.travelPackage.entity.TravelPackageEntity;
import com.travel.travelPackage.service.ActivityService;
import com.travel.travelPackage.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travel-packages")
public class TravelPackageController {

    @Autowired
    private TravelPackageService travelPackageService;

    @Autowired
    private ActivityService activityService;

    @GetMapping("/{id}")
    public TravelPackageEntity getTravelPackageById(@PathVariable Long id) {
        return travelPackageService.getTravelPackageById(id);
    }

    @PostMapping
    public TravelPackageEntity createTravelPackage(@RequestBody TravelPackageEntity travelPackage) {
        return travelPackageService.addTravelPackage(travelPackage);
    }

    @PutMapping("/{travelPackageId}")
    public TravelPackageEntity updateTravelPackage(@PathVariable Long travelPackageId, @RequestBody TravelPackageEntity updatedTravelPackage) {
        return travelPackageService.updateTravelPackage(travelPackageId, updatedTravelPackage);
    }

    @DeleteMapping("/{travelPackageId}")
    public void deleteTravelPackage(@PathVariable Long travelPackageId) {
        travelPackageService.deleteTravelPackage(travelPackageId);
    }

    @GetMapping("/{travelPackageId}/itinerary")
    public String printItinerary(@PathVariable Long travelPackageId) {
        return travelPackageService.printItinerary(travelPackageId);
    }

    @GetMapping("/{travelPackageId}/passenger-list")
    public String printPassengerList(@PathVariable Long travelPackageId) {
        return travelPackageService.printPassengerList(travelPackageId);
    }

    @GetMapping("/{travelPackageId}/available-activities")
    public List<ActivityEntity> printAvailableActivities(@PathVariable Long travelPackageId) {
        TravelPackageEntity travelPackage = travelPackageService.getTravelPackageById(travelPackageId);
        return activityService.getAvailableActivities(travelPackage);
    }
}


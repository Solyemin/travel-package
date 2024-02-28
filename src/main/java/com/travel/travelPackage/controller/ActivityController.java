package com.travel.travelPackage.controller;

import com.travel.travelPackage.entity.ActivityEntity;
import com.travel.travelPackage.entity.TravelPackageEntity;
import com.travel.travelPackage.service.ActivityService;
import com.travel.travelPackage.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
    @Autowired
    public ActivityService activityService;
    @Autowired
    public TravelPackageService travelPackageService;

    @GetMapping("/{activityId}")
    public ActivityEntity getActivityById(@PathVariable Long activityId) {
        return activityService.getActivityById(activityId);
    }

    @PostMapping("/add")
    public ResponseEntity<ActivityEntity> addActivity(@RequestBody ActivityEntity activity) {
        ActivityEntity addedActivity = activityService.addActivity(activity);
        return new ResponseEntity<>(addedActivity, HttpStatus.CREATED);
    }

    @PutMapping("/{activityId}")
    public ActivityEntity updateActivity(@PathVariable Long activityId, @RequestBody ActivityEntity updatedActivity) {
        return activityService.updateActivity(activityId, updatedActivity);
    }

    @DeleteMapping("/{activityId}")
    public void deleteActivity(@PathVariable Long activityId) {
        activityService.deleteActivity(activityId);
    }

    @GetMapping("/{travelPackageId}/available")
    public List<ActivityEntity> getAvailableActivities(@PathVariable Long travelPackageId) {
        TravelPackageEntity travelPackage = travelPackageService.getTravelPackageById(travelPackageId);
        return activityService.getAvailableActivities(travelPackage);
    }
}


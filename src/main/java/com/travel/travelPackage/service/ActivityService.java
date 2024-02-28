package com.travel.travelPackage.service;

import com.travel.travelPackage.entity.ActivityEntity;
import com.travel.travelPackage.entity.TravelPackageEntity;

import java.util.List;

public interface ActivityService {

    ActivityEntity getActivityById(Long id);

    List<ActivityEntity> getAvailableActivities(TravelPackageEntity travelPackage);

    ActivityEntity addActivity(ActivityEntity activity);

    ActivityEntity updateActivity(Long acrivityId,ActivityEntity activity);

    void deleteActivity(Long id);


    String printAvailableActivities();


}

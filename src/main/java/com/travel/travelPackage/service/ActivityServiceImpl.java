package com.travel.travelPackage.service;

import com.travel.travelPackage.entity.ActivityEntity;
import com.travel.travelPackage.entity.DestinationEntity;
import com.travel.travelPackage.entity.TravelPackageEntity;
import com.travel.travelPackage.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public ActivityEntity getActivityById(Long id) {
        Optional<ActivityEntity> activityOptional = activityRepository.findById(id);
        return activityOptional.orElse(null);
    }
    @Override
    public ActivityEntity addActivity(ActivityEntity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public ActivityEntity updateActivity(Long activityId, ActivityEntity updatedActivity) {
        if (activityRepository.existsById(activityId)) {
            updatedActivity.setId(activityId);
            return activityRepository.save(updatedActivity);
        } else {
            throw new RuntimeException("Activity not found");
        }
    }

    @Override
    public void deleteActivity(Long id) {
        if (!activityRepository.existsById(id)) {
            throw new RuntimeException("Activity not found");
        }

        activityRepository.deleteById(id);
    }

    @Override
    public List<ActivityEntity> getAvailableActivities(TravelPackageEntity travelPackage) {
        List<DestinationEntity> itinerary = travelPackage.getItinerary();
        return activityRepository.findAvailableActivitiesInItinerary(itinerary);
    }

    @Override
    public String printAvailableActivities() {
        List<ActivityEntity> availableActivities = activityRepository.findActivitiesWithSpacesAvailable();

        StringBuilder availableActivitiesDetails = new StringBuilder();
        availableActivitiesDetails.append("Available Activities:\n");

        for (ActivityEntity activity : availableActivities) {
            availableActivitiesDetails.append("Activity: ").append(activity.getName())
                    .append(", Destination: ").append(activity.getDestination() != null ? activity.getDestination().getName() : "Unknown")
                    .append(", Cost: ").append(activity.getCost())
                    .append(", Capacity: ").append(activity.getCapacity()).append("\n");
        }


        return availableActivitiesDetails.toString();
    }

}


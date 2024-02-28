package com.travel.travelPackage.service;

import static org.junit.jupiter.api.Assertions.*;

import com.travel.travelPackage.entity.ActivityEntity;
import com.travel.travelPackage.entity.DestinationEntity;
import com.travel.travelPackage.entity.TravelPackageEntity;
import com.travel.travelPackage.repository.ActivityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivityServiceImplTest {

    @Mock
    private ActivityRepository activityRepository;

    @InjectMocks
    private ActivityServiceImpl activityService;

    @Test
    void getActivityById_ActivityExists_ReturnsActivity() {
        Long activityId = 1L;
        ActivityEntity expectedActivity = new ActivityEntity();
        expectedActivity.setId(activityId);

        when(activityRepository.findById(activityId)).thenReturn(Optional.of(expectedActivity));

        ActivityEntity result = activityService.getActivityById(activityId);

        assertNotNull(result);
        assertEquals(activityId, result.getId());
    }

    @Test
    void getActivityById_ActivityDoesNotExist_ReturnsNull() {
        Long activityId = 1L;

        when(activityRepository.findById(activityId)).thenReturn(Optional.empty());

        ActivityEntity result = activityService.getActivityById(activityId);

        assertNull(result);
    }

    @Test
    void addActivity_ValidActivity_ReturnsAddedActivity() {
        ActivityEntity activityToAdd = new ActivityEntity();
        when(activityRepository.save(any())).thenReturn(activityToAdd);

        ActivityEntity result = activityService.addActivity(activityToAdd);

        assertNotNull(result);
        verify(activityRepository, times(1)).save(activityToAdd);
    }

    @Test
    void updateActivity_ActivityExists_ReturnsUpdatedActivity() {
        // Arrange
        Long activityId = 1L;
        ActivityEntity updatedActivity = new ActivityEntity();
        updatedActivity.setId(activityId);

        when(activityRepository.existsById(activityId)).thenReturn(true);
        when(activityRepository.save(updatedActivity)).thenReturn(updatedActivity);

        ActivityEntity result = activityService.updateActivity(activityId, updatedActivity);

        assertNotNull(result);
        assertEquals(activityId, result.getId());
        verify(activityRepository, times(1)).save(updatedActivity);
    }

    @Test
    void updateActivity_ActivityDoesNotExist_ThrowsRuntimeException() {
        Long activityId = 1L;
        ActivityEntity updatedActivity = new ActivityEntity();

        when(activityRepository.existsById(activityId)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> activityService.updateActivity(activityId, updatedActivity));
        verify(activityRepository, never()).save(updatedActivity);
    }

    @Test
    void deleteActivity_ActivityExists_DeletesActivity() {
        Long activityId = 1L;

        when(activityRepository.existsById(activityId)).thenReturn(true);

        activityService.deleteActivity(activityId);

        verify(activityRepository, times(1)).deleteById(activityId);
    }

    @Test
    void deleteActivity_ActivityDoesNotExist_ThrowsRuntimeException() {

        Long activityId = 1L;

        when(activityRepository.existsById(activityId)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> activityService.deleteActivity(activityId));
        verify(activityRepository, never()).deleteById(activityId);
    }

    @Test
    void getAvailableActivities_ValidTravelPackage_ReturnsAvailableActivities() {
        TravelPackageEntity travelPackage = new TravelPackageEntity();
        List<DestinationEntity> itinerary = new ArrayList<>();
        travelPackage.setItinerary(itinerary);
        List<ActivityEntity> expectedAvailableActivities = new ArrayList<>();

        when(activityRepository.findAvailableActivitiesInItinerary(itinerary)).thenReturn(expectedAvailableActivities);

        List<ActivityEntity> result = activityService.getAvailableActivities(travelPackage);

        assertEquals(expectedAvailableActivities, result);
        verify(activityRepository, times(1)).findAvailableActivitiesInItinerary(itinerary);
    }

    @Test
    void printAvailableActivities_ActivitiesExist_ReturnsActivitiesDetails() {
        List<ActivityEntity> availableActivities = new ArrayList<>();
        ActivityEntity activity1 = new ActivityEntity();
        activity1.setName("Snorkeling");
        activity1.setDescription("Explore underwater life");
        activity1.setCost(50.0);
        activity1.setCapacity(20);
        availableActivities.add(activity1);

        ActivityEntity activity2 = new ActivityEntity();
        activity2.setName("Sunbathing");
        activity2.setDescription("Relax under the sun");
        activity2.setCost(0.0);
        activity2.setCapacity(20);
        availableActivities.add(activity2);

        when(activityRepository.findActivitiesWithSpacesAvailable()).thenReturn(availableActivities);

        String result = activityService.printAvailableActivities();

        String expectedOutput = "Available Activities:\n" +
                "Activity: Snorkeling, Destination: null, Cost: 50.0, Capacity: 20\n" +
                "Activity: Sunbathing, Destination: null, Cost: 0.0, Capacity: 20\n";

        assertEquals(expectedOutput, result);
    }
}

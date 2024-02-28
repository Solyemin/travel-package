package com.travel.travelPackage.service;

import static org.junit.jupiter.api.Assertions.*;

import com.travel.travelPackage.entity.*;
import com.travel.travelPackage.repository.TravelPackageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TravelPackageServiceImplTest {

    @InjectMocks
    private TravelPackageServiceImpl travelPackageService;

    @Mock
    private TravelPackageRepository travelPackageRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTravelPackageById() {
        Long travelPackageId = 1L;
        TravelPackageEntity mockTravelPackage = new TravelPackageEntity();
        mockTravelPackage.setId(travelPackageId);

        when(travelPackageRepository.findById(travelPackageId)).thenReturn(Optional.of(mockTravelPackage));

        TravelPackageEntity result = travelPackageService.getTravelPackageById(travelPackageId);

        assertNotNull(result);
        assertEquals(travelPackageId, result.getId());
    }

    @Test
    void testGetTravelPackageByIdNotFound() {
        Long travelPackageId = 1L;

        when(travelPackageRepository.findById(travelPackageId)).thenReturn(Optional.empty());

        TravelPackageEntity result = travelPackageService.getTravelPackageById(travelPackageId);

        assertNull(result);
    }

    @Test
    void testAddTravelPackage() {
        TravelPackageEntity travelPackageToAdd = new TravelPackageEntity();
        when(travelPackageRepository.save(travelPackageToAdd)).thenReturn(travelPackageToAdd);

        TravelPackageEntity result = travelPackageService.addTravelPackage(travelPackageToAdd);

        assertNotNull(result);
        assertEquals(travelPackageToAdd, result);
    }

    @Test
    void testUpdateTravelPackage() {
        Long travelPackageId = 1L;
        TravelPackageEntity existingTravelPackage = new TravelPackageEntity();
        existingTravelPackage.setId(travelPackageId);

        TravelPackageEntity updatedTravelPackage = new TravelPackageEntity();
        updatedTravelPackage.setId(travelPackageId);
        updatedTravelPackage.setName("Updated Travel Package");

        when(travelPackageRepository.existsById(travelPackageId)).thenReturn(true);
        when(travelPackageRepository.save(updatedTravelPackage)).thenReturn(updatedTravelPackage);

        TravelPackageEntity result = travelPackageService.updateTravelPackage(travelPackageId, updatedTravelPackage);

        assertNotNull(result);
        assertEquals(updatedTravelPackage, result);
    }

    @Test
    void testUpdateTravelPackageNotFound() {
        Long travelPackageId = 1L;
        TravelPackageEntity updatedTravelPackage = new TravelPackageEntity();
        updatedTravelPackage.setId(travelPackageId);

        when(travelPackageRepository.existsById(travelPackageId)).thenReturn(false);

        assertThrows(RuntimeException.class, () ->
                travelPackageService.updateTravelPackage(travelPackageId, updatedTravelPackage));
    }

    @Test
    void testDeleteTravelPackage() {
        Long travelPackageId = 1L;
        when(travelPackageRepository.existsById(travelPackageId)).thenReturn(true);

        assertDoesNotThrow(() -> travelPackageService.deleteTravelPackage(travelPackageId));
        verify(travelPackageRepository, times(1)).deleteById(travelPackageId);
    }

    @Test
    void testDeleteTravelPackageNotFound() {
        Long travelPackageId = 1L;
        when(travelPackageRepository.existsById(travelPackageId)).thenReturn(false);

        assertThrows(RuntimeException.class, () ->
                travelPackageService.deleteTravelPackage(travelPackageId));
    }

    @Test
    void testPrintItinerary() {
        Long travelPackageId = 1L;
        TravelPackageEntity mockTravelPackage = createMockTravelPackage(travelPackageId);

        when(travelPackageRepository.findById(travelPackageId)).thenReturn(Optional.of(mockTravelPackage));

        String result = travelPackageService.printItinerary(travelPackageId);

        assertTrue(result.contains("Travel Package Name:"));
        assertTrue(result.contains("Destinations and Activities:"));
        assertTrue(result.contains("Destination:"));
        assertTrue(result.contains("Activity:"));
    }

    @Test
    void testPrintPassengerList() {
        Long travelPackageId = 1L;
        TravelPackageEntity mockTravelPackage = createMockTravelPackage(travelPackageId);

        when(travelPackageRepository.findById(travelPackageId)).thenReturn(Optional.of(mockTravelPackage));

        String result = travelPackageService.printPassengerList(travelPackageId);

        assertTrue(result.contains("Travel Package Name:"));
        assertTrue(result.contains("Passenger List:"));
        assertTrue(result.contains("Passenger Name:"));
    }

    private TravelPackageEntity createMockTravelPackage(Long travelPackageId) {
        TravelPackageEntity travelPackage = new TravelPackageEntity();
        travelPackage.setId(travelPackageId);
        travelPackage.setName("Test Travel Package");

        DestinationEntity destination = new DestinationEntity();
        destination.setName("Test Destination");

        ActivityEntity activity = new ActivityEntity();
        activity.setName("Test Activity");
        activity.setCost(100.0);
        activity.setCapacity(10);
        activity.setDescription("Test Description");

        List<ActivityEntity> activities = new ArrayList<>();
        activities.add(activity);

        destination.setActivities(activities);

        List<DestinationEntity> destinations = new ArrayList<>();
        destinations.add(destination);

        travelPackage.setItinerary(destinations);

        PassengerEntity passenger = new PassengerEntity();
        passenger.setName("Test Passenger");
        passenger.setPassengerNumber(123456);

        List<PassengerEntity> passengers = new ArrayList<>();
        passengers.add(passenger);

        travelPackage.setPassengers(passengers);

        return travelPackage;
    }


}

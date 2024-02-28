package com.travel.travelPackage.service;

import static org.junit.jupiter.api.Assertions.*;

import com.travel.travelPackage.entity.ActivityEntity;
import com.travel.travelPackage.entity.PassengerEntity;
import com.travel.travelPackage.repository.PassengerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PassengerServiceImplTest {

    @Mock
    private PassengerRepository passengerRepository;

    @InjectMocks
    private PassengerServiceImpl passengerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPassengerById() {
        PassengerEntity mockPassenger = new PassengerEntity(1L, "John Doe", 123456, 100.0, "Standard", null, null);
        when(passengerRepository.findById(1L)).thenReturn(Optional.of(mockPassenger));

        PassengerEntity result = passengerService.getPassengerById(1L);

        assertEquals("John Doe", result.getName());
    }

    @Test
    void testAddPassenger() {
        PassengerEntity passengerToSave = new PassengerEntity(null, "Jane Doe", 789012, 150.0, "Gold", null, null);
        when(passengerRepository.save(passengerToSave)).thenReturn(passengerToSave);

        PassengerEntity result = passengerService.addPassenger(passengerToSave);

        assertEquals("Jane Doe", result.getName());
    }

    @Test
    void testUpdatePassenger() {
        PassengerEntity existingPassenger = new PassengerEntity(1L, "John Doe", 123456, 100.0, "Standard", null, null);
        PassengerEntity updatedPassenger = new PassengerEntity(1L, "Updated Name", 789012, 150.0, "Gold", null, null);
        when(passengerRepository.existsById(1L)).thenReturn(true);
        when(passengerRepository.save(updatedPassenger)).thenReturn(updatedPassenger);

        PassengerEntity result = passengerService.updatePassenger(1L, updatedPassenger);

        assertEquals("Updated Name", result.getName());
    }

    @Test
    void testDeletePassenger() {
        when(passengerRepository.existsById(1L)).thenReturn(true);

        passengerService.deletePassenger(1L);

        verify(passengerRepository, times(1)).deleteById(1L);
    }

    @Test
    void testPrintPassengerDetails() {
        ActivityEntity mockActivity = new ActivityEntity(1L, "Swimming", "Swim in the pool", 20.0, 30, null);
        PassengerEntity mockPassenger = new PassengerEntity(1L, "John Doe", 123456, 100.0, "Standard", null, Collections.singletonList(mockActivity));
        when(passengerRepository.findById(1L)).thenReturn(Optional.of(mockPassenger));

        String result = passengerService.printPassengerDetails(1L);

    }

    @Test
    void testCalculatePriceForPassenger() {
        PassengerEntity mockPassenger = mock(PassengerEntity.class);
        ActivityEntity mockActivity = mock(ActivityEntity.class);

        when(mockPassenger.getPassenger_type()).thenReturn("Standard");
        when(mockActivity.getCost()).thenReturn(50.0);

        double resultStandard = passengerService.calculatePriceForPassenger(mockPassenger, mockActivity);

        assertEquals(50.0, resultStandard, 0.01);

        when(mockPassenger.getPassenger_type()).thenReturn("Gold");
        double resultGold = passengerService.calculatePriceForPassenger(mockPassenger, mockActivity);

        assertEquals(45.0, resultGold, 0.01);

        when(mockPassenger.getPassenger_type()).thenReturn("Premium");
        double resultPremium = passengerService.calculatePriceForPassenger(mockPassenger, mockActivity);

        assertEquals(0.0, resultPremium, 0.01);
    }

}

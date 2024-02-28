package com.travel.travelPackage.service;

import static org.junit.jupiter.api.Assertions.*;

import com.travel.travelPackage.entity.DestinationEntity;
import com.travel.travelPackage.repository.DestinationRepository;
import com.travel.travelPackage.service.DestinationService;
import com.travel.travelPackage.service.DestinationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DestinationServiceImplTest {

    @Mock
    private DestinationRepository destinationRepository;

    @InjectMocks
    private DestinationService destinationService = new DestinationServiceImpl(destinationRepository);
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllDestinations_shouldReturnAllDestinations() {
        DestinationEntity destination1 = new DestinationEntity(1L, "Destination1", null,null);
        DestinationEntity destination2 = new DestinationEntity(2L, "Destination2", null,null);
        List<DestinationEntity> expectedDestinations = Arrays.asList(destination1, destination2);

        when(destinationRepository.findAll()).thenReturn(expectedDestinations);

        List<DestinationEntity> actualDestinations = destinationService.getAllDestinations();

        assertEquals(expectedDestinations, actualDestinations);
    }

    @Test
    void getDestinationById_existingId_shouldReturnDestination() {
        Long destinationId = 1L;
        DestinationEntity expectedDestination = new DestinationEntity(destinationId, "Destination1", null,null);

        when(destinationRepository.findById(destinationId)).thenReturn(Optional.of(expectedDestination));
        DestinationEntity actualDestination = destinationService.getDestinationById(destinationId);

        assertEquals(expectedDestination, actualDestination);
    }

    @Test
    void getDestinationById_nonExistingId_shouldReturnNull() {
        Long destinationId = 1L;

        when(destinationRepository.findById(destinationId)).thenReturn(Optional.empty());

        DestinationEntity actualDestination = destinationService.getDestinationById(destinationId);

        assertNull(actualDestination);
    }

    @Test
    void addDestination_shouldReturnAddedDestination() {
        DestinationEntity destinationToSave = new DestinationEntity(null, "New Destination", null,null);
        DestinationEntity expectedSavedDestination = new DestinationEntity(1L, "New Destination", null,null);

        when(destinationRepository.save(destinationToSave)).thenReturn(expectedSavedDestination);

        DestinationEntity actualSavedDestination = destinationService.addDestination(destinationToSave);

        assertEquals(expectedSavedDestination, actualSavedDestination);
    }

    @Test
    void updateDestination_existingId_shouldReturnUpdatedDestination() {
        Long destinationId = 1L;
        DestinationEntity updatedDestination = new DestinationEntity(destinationId, "Updated Destination", null,null);

        when(destinationRepository.existsById(destinationId)).thenReturn(true);
        when(destinationRepository.save(updatedDestination)).thenReturn(updatedDestination);

        DestinationEntity actualUpdatedDestination = destinationService.updateDestination(destinationId, updatedDestination);

        assertEquals(updatedDestination, actualUpdatedDestination);
    }

    @Test
    void updateDestination_nonExistingId_shouldThrowException() {
        Long destinationId = 1L;
        DestinationEntity updatedDestination = new DestinationEntity(destinationId, "Updated Destination", null,null);

        when(destinationRepository.existsById(destinationId)).thenReturn(false);

        assertThrows(RuntimeException.class, () ->
                destinationService.updateDestination(destinationId, updatedDestination));
    }

    @Test
    void deleteDestination_existingId_shouldDeleteDestination() {
        Long destinationId = 1L;

        when(destinationRepository.existsById(destinationId)).thenReturn(true);

        destinationService.deleteDestination(destinationId);

        verify(destinationRepository, times(1)).deleteById(destinationId);
    }

    @Test
    void deleteDestination_nonExistingId_shouldThrowException() {
        Long destinationId = 1L;

        when(destinationRepository.existsById(destinationId)).thenReturn(false);

        assertThrows(RuntimeException.class, () ->
                destinationService.deleteDestination(destinationId));
    }
}

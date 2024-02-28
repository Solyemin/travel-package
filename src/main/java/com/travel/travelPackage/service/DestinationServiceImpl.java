package com.travel.travelPackage.service;

import com.travel.travelPackage.entity.DestinationEntity;
import com.travel.travelPackage.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepository;

    @Autowired
    public DestinationServiceImpl(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Override
    public List<DestinationEntity> getAllDestinations() {
        return destinationRepository.findAll();
    }

    @Override
    public DestinationEntity getDestinationById(Long id) {
        Optional<DestinationEntity> destinationOptional = destinationRepository.findById(id);
        return destinationOptional.orElse(null);
    }

    @Override
    public DestinationEntity addDestination(DestinationEntity destination) {
        return destinationRepository.save(destination);
    }

    @Override
    public DestinationEntity updateDestination(Long destinationId, DestinationEntity updatedDestination) {
        if (destinationRepository.existsById(destinationId)) {
            updatedDestination.setId(destinationId);
            return destinationRepository.save(updatedDestination);
        } else {
            throw new RuntimeException("Destination not found");
        }
    }

    @Override
    public void deleteDestination(Long destinationId) {
        if (destinationRepository.existsById(destinationId)) {
            destinationRepository.deleteById(destinationId);
        } else {
            throw new RuntimeException("Destination not found");
        }
    }

}


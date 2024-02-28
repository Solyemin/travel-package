package com.travel.travelPackage.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "travel_package")
public class TravelPackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String name;
    private int passengerCapacity;

    @Getter
    @OneToMany(mappedBy = "travelPackage", cascade = CascadeType.ALL)
    private List<DestinationEntity> itinerary;

    @Getter
    @OneToMany(mappedBy = "travelPackage", cascade = CascadeType.ALL)
    private List<PassengerEntity> passengers;

}

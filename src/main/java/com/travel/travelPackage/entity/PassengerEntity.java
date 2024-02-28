package com.travel.travelPackage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "passenger")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class PassengerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int passengerNumber;
    @Column(name = "balance")
    private double balance;

   private String passenger_type;


    @ManyToOne
    @JoinColumn(name = "travel_package_id")
    private TravelPackageEntity travelPackage;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "passenger_activity",
            joinColumns = @JoinColumn(name = "passenger_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id"))
    private List<ActivityEntity> activities;

}

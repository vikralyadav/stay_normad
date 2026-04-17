package com.example.Normad_stay_real_backend.Stay.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "stays")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stay {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String stayName;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private String city;

    private String state;
    private String country;

    @Column(nullable = false)
    private Double basePrice;

    private Double price7Days;
    private Double price15Days;
    private Double price30Days;


    private int maxGuests;
    private int bedrooms;
    private int bathrooms;

    @ElementCollection
    @CollectionTable(name = "stay_amenities", joinColumns = @JoinColumn(name = "stay_id"))
    @Column(name = "amenity")
    private List<String> amenities;


    @ElementCollection
    @CollectionTable(name = "stay_images", joinColumns = @JoinColumn(name = "stay_id"))
    @Column(name = "image_url")
    private List<String> images;


    private Double rating;
    private Integer totalReviews;

    private Boolean isAvailable;
}
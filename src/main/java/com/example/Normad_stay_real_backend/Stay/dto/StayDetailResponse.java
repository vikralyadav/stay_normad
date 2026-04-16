package com.example.Normad_stay_real_backend.Stay.dto;

import jakarta.persistence.Table;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StayDetailResponse {

    private UUID id;

    private String stayName;
    private String description;

    private String city;
    private String state;
    private String country;

    private List<String> images;

    private List<String> amenities;

    private Double basePrice;
    private Double price7Days;
    private Double price15Days;
    private Double price30Days;

    private int maxGuests;
    private int bedrooms;
    private int bathrooms;

    private Double rating;
    private Integer totalReviews;

    private Boolean isAvailable;

    private UUID ownerId;
}
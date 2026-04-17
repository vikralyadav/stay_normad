package com.example.Normad_stay_real_backend.Stay.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@Data
public class AddStayRequest {

    @NotBlank(message = "Please Enter Stay Name first")
    private String stayName;
    private String description;

   @NotBlank(message = "Please Enter your city")
    private String city;
    private String state;
    private String country;

    @NotNull(message = "Base price is required")
    @Positive(message = "Base price must be greater than 0")
    private Double basePrice;
    private Double price7Days;
    private Double price15Days;
    private Double price30Days;

    private Integer maxGuests;
    private Integer bedrooms;
    private Integer bathrooms;

    private List<String> amenities;

    private List<String> images;


    private Boolean isAvailable;
}

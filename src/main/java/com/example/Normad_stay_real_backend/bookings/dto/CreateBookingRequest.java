package com.example.Normad_stay_real_backend.bookings.dto;

import com.example.Normad_stay_real_backend.bookings.enums.PackageType;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CreateBookingRequest {
    @NotBlank(message = "Please Select stay first before create booking")
    public UUID stay_id;

    @NotBlank(message = "Select your plan")
    public PackageType packageType;

    @NotBlank(message = "please select no of guests")
    public int guests;


    @NotBlank(message = "please select checking date")
    public LocalDate checkInDate;


    @NotBlank(message = "please enter your mail")
    @Email(message = "please enter valid email")
    private String email;




}

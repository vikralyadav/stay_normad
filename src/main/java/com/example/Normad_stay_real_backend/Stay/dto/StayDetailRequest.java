package com.example.Normad_stay_real_backend.Stay.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class StayDetailRequest {
    @NotBlank(message = "Please Enter Stay Id First")
    private UUID stayId;
}

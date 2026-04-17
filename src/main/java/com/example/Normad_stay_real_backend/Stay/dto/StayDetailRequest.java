package com.example.Normad_stay_real_backend.Stay.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class StayDetailRequest {
    @NotNull(message = "StayID is required")
    private UUID stayId;
}

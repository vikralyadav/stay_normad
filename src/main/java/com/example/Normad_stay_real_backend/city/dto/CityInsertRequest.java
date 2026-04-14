package com.example.Normad_stay_real_backend.city.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class CityInsertRequest {
    @NotBlank(message = "Please Enter City Name First")
    private    String cityName;
}

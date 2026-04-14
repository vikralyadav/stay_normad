package com.example.Normad_stay_real_backend.auth.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "phone no is required")
    private String phoneNo;

    @NotBlank(message = "Password is required")
    private String password;



}

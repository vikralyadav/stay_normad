package com.example.Normad_stay_real_backend.auth.dto;


import com.example.Normad_stay_real_backend.common.entity.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AuthResponse {
    public String accessToken;

    @JsonProperty("id")
    public UUID id;

    @JsonProperty("role")
    public Role role;



}

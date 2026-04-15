package com.example.Normad_stay_real_backend.user.dto;

import lombok.Data;

@Data
public class UpdateProfileRequest {

    private String username;
    private String profilePic;
    private String bio;
    private String address;

}

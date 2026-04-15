package com.example.Normad_stay_real_backend.user.dto;

import com.example.Normad_stay_real_backend.common.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserDetailResponse {


    public UUID id;
    public UUID credentialId;
    public String username;
    public String profilePic;
    public String bio;
    public String address;
    public Role role;
    public String phoneNo;
    public String email;


}

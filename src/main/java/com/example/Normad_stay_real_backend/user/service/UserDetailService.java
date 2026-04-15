package com.example.Normad_stay_real_backend.user.service;

import com.example.Normad_stay_real_backend.common.exception.BadRequestException;
import com.example.Normad_stay_real_backend.common.exception.ResourceNotFoundException;
import com.example.Normad_stay_real_backend.common.utils.JwtUtils;
import com.example.Normad_stay_real_backend.user.dto.UpdateProfileRequest;
import com.example.Normad_stay_real_backend.user.dto.UserDetailResponse;
import com.example.Normad_stay_real_backend.user.entity.UserDetail;
import com.example.Normad_stay_real_backend.user.repository.UserDetailsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailService {



    private final UserDetailsRepository userDetailsRepository;
    private final JwtUtils jwtUtils;



    public UserDetailResponse getUserDetails(String authHeader){

        UUID credentialId = extractCredentialId(authHeader);

        UserDetail user = userDetailsRepository
                .findByCredentialId(credentialId)
                .orElseThrow(() -> new ResourceNotFoundException("UserDetail", "credentialId", credentialId.toString()));


        return mapToResponse(user);
    }


    @Transactional
    public UserDetailResponse updateProfile(String authHeader, UpdateProfileRequest request){

        UUID credentialId = extractCredentialId(authHeader);

        UserDetail user = userDetailsRepository
                .findByCredentialId(credentialId)
                .orElseThrow(() -> new ResourceNotFoundException("UserDetail", "credentialId", credentialId.toString()));

        if(request.getUsername() != null){
            user.setUsername(request.getUsername());
        }
        if(request.getProfilePic() != null){
            user.setProfilePic(request.getProfilePic());
        }
        if(request.getBio() != null){
            user.setBio(request.getBio());
        }
        if(request.getAddress() != null){
            user.setAddress(request.getAddress());
        }

        user = userDetailsRepository.save(user);
        log.info("Profile updated for credentialId: {}", credentialId);

        return mapToResponse(user);
    }

    private UUID extractCredentialId(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new BadRequestException("Missing or invalid Authorization header");
        }
        String token = authHeader.substring(7);
        if (!jwtUtils.isTokenValid(token)) {
            throw new BadRequestException("Invalid or expired token");
        }
        return jwtUtils.getUserIdFromToken(token);
    }



    private UserDetailResponse mapToResponse(UserDetail user) {
        return UserDetailResponse.builder()
                .id(user.getId())
                .credentialId(user.getCredentialId())
                .username(user.getUsername())
                .profilePic(user.getProfilePic())
                .bio(user.getBio())
                .address(user.getAddress())
                .role(user.getRole())
                .phoneNo(user.getPhoneNo())
                .email(user.getEmail())
                .build();
    }

}

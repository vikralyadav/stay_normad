package com.example.Normad_stay_real_backend.user.controller;
import com.example.Normad_stay_real_backend.common.dto.ApiResponse;
import com.example.Normad_stay_real_backend.user.dto.UpdateProfileRequest;
import com.example.Normad_stay_real_backend.user.dto.UserDetailResponse;
import com.example.Normad_stay_real_backend.user.service.UserDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserDetailController {



    private final UserDetailService userDetailService;



    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<UserDetailResponse>> getUserDetails(
            @RequestHeader("Authorization") String authHeader
    ){

        UserDetailResponse userDetailResponse = userDetailService.getUserDetails(authHeader);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("User details fetched successfully", userDetailResponse));
    }


    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<UserDetailResponse>> updateProfile(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody UpdateProfileRequest updateProfileRequest
    ){

        UserDetailResponse userDetailResponse = userDetailService.updateProfile(authHeader, updateProfileRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Profile updated successfully", userDetailResponse));
    }



}

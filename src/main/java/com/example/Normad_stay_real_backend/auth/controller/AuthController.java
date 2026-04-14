package com.example.Normad_stay_real_backend.auth.controller;

import com.example.Normad_stay_real_backend.auth.dto.AuthResponse;
import com.example.Normad_stay_real_backend.auth.dto.LoginRequest;
import com.example.Normad_stay_real_backend.auth.dto.SignupRequest;
import com.example.Normad_stay_real_backend.auth.service.AuthService;
import com.example.Normad_stay_real_backend.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;



    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<AuthResponse>> signUpUser(
            @Valid @RequestBody SignupRequest request
    ){
        AuthResponse response = authService.signupUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("User registered successfully", response));
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> loginUser(@Valid @RequestBody LoginRequest request){
        AuthResponse response = authService.loginUser(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("LoginSuccess", response));
    }

}

package com.example.Normad_stay_real_backend.auth.service;

import com.example.Normad_stay_real_backend.auth.dto.AuthResponse;
import com.example.Normad_stay_real_backend.auth.dto.LoginRequest;
import com.example.Normad_stay_real_backend.auth.dto.SignupRequest;
import com.example.Normad_stay_real_backend.auth.entity.UserCredential;
import com.example.Normad_stay_real_backend.auth.repository.UserCredentialRepository;
import com.example.Normad_stay_real_backend.common.entity.Role;
import com.example.Normad_stay_real_backend.common.exception.BadRequestException;
import com.example.Normad_stay_real_backend.common.exception.ResourceNotFoundException;
import com.example.Normad_stay_real_backend.common.utils.JwtUtils;
import com.example.Normad_stay_real_backend.user.entity.UserDetail;
import com.example.Normad_stay_real_backend.user.repository.UserDetailsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {


    private final UserCredentialRepository userCredentialRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final ApplicationEventPublisher eventPublisher;



    @Transactional
    public AuthResponse signupUser(SignupRequest signupRequest){


        if(userCredentialRepository.existsByEmail(signupRequest.getEmail())){
          throw new BadRequestException("User with Email Already Exists");

        }
        if(userCredentialRepository.existsByPhoneNo(signupRequest.getPhoneNo())){
            throw new BadRequestException("User with Same Phone No Already Exists");
        }


        Role role = Role.USER;
        if (signupRequest.getRole() != null) {
            try {
                role = Role.valueOf(signupRequest.getRole().toUpperCase());
                if (role == Role.ADMIN) {
                    throw new BadRequestException("Cannot register as ADMIN");
                }
            } catch (IllegalArgumentException e) {
                throw new BadRequestException("Invalid role: " + signupRequest.getRole());
            }
        }


        UserCredential user = UserCredential.builder()
                .email(signupRequest.getEmail())
                .phoneNo(signupRequest.getPhoneNo())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .role(role)
                .build();



        user = userCredentialRepository.save(user);

        UserDetail userDetail = UserDetail.builder()
                .credentialId(user.getId())
                .phoneNo(user.getPhoneNo())
                .email(user.getEmail())
                .role(role)
                .build();

        userDetailsRepository.save(userDetail);
        log.info("Created UserDetail for credentialId: {}", user.getId());


      String accessToken = jwtUtils.generateAccessToken(user.getPhoneNo(), user.getRole(), user.getId());

      return AuthResponse.builder()
              .accessToken(accessToken)
              .id(user.getId())
              .role(role)
              .build();

    }




    @Transactional
    public AuthResponse loginUser(LoginRequest loginRequest){
        UserCredential user = userCredentialRepository.findByPhoneNo(loginRequest.getPhoneNo())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", loginRequest.getPhoneNo()));



        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new BadRequestException("Invalid Password");
        }

        String accessToken = jwtUtils.generateAccessToken(user.getPhoneNo(), user.getRole(), user.getId());



        return AuthResponse.builder()
                .accessToken(accessToken)
                .id(user.getId())
                .role(user.getRole())
                .build();




    }
}

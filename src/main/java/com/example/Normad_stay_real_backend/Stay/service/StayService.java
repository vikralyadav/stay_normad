package com.example.Normad_stay_real_backend.Stay.service;


import com.example.Normad_stay_real_backend.Stay.dto.AddStayRequest;
import com.example.Normad_stay_real_backend.Stay.dto.GetStayRequest;
import com.example.Normad_stay_real_backend.Stay.dto.StayDetailRequest;
import com.example.Normad_stay_real_backend.Stay.dto.StayDetailResponse;
import com.example.Normad_stay_real_backend.Stay.entity.Stay;
import com.example.Normad_stay_real_backend.Stay.repository.StayRepository;
import com.example.Normad_stay_real_backend.common.exception.BadRequestException;
import com.example.Normad_stay_real_backend.common.exception.ResourceNotFoundException;
import com.example.Normad_stay_real_backend.common.utils.JwtUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StayService {


    private final StayRepository stayRepository;

     private final JwtUtils jwtUtils;


    @Transactional
     public StayDetailResponse getStayDetails(String authHeader, StayDetailRequest stayDetailReq){


         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
             throw new BadRequestException("Missing or invalid Authorization header");
         }
         String token = authHeader.substring(7);
         if (!jwtUtils.isTokenValid(token)) {
             throw new BadRequestException("Invalid or expired token");
         }


        Stay stay = stayRepository.findById(stayDetailReq.getStayId())
                .orElseThrow(() -> new ResourceNotFoundException("Stay not found"));




         return StayDetailResponse.builder()
                 .id(stay.getId())
                 .stayName(stay.getStayName())
                 .description(stay.getDescription())
                 .city(stay.getCity())
                 .state(stay.getState())
                 .country(stay.getCountry())
                 .images(stay.getImages())
                 .amenities(stay.getAmenities())
                 .basePrice(stay.getBasePrice())
                 .price7Days(stay.getPrice7Days())
                 .price15Days(stay.getPrice15Days())
                 .price30Days(stay.getPrice30Days())
                 .maxGuests(stay.getMaxGuests())
                 .bedrooms(stay.getBedrooms())
                 .bathrooms(stay.getBathrooms())
                 .rating(stay.getRating())
                 .totalReviews(stay.getTotalReviews())
                 .isAvailable(stay.getIsAvailable())
                 .build();
     }



     @Transactional
     public String addyourStay(String authHeader, AddStayRequest addStayRequest){



         String role =  getUserRole(authHeader);


         if(!"OWNER".equals(role)){
             throw new BadRequestException("You are not authorize to add stay");
         }


         if ( !authHeader.startsWith("Bearer ")) {
             throw new BadRequestException("Missing or invalid Authorization header");
         }
         String token = authHeader.substring(7);
         if (!jwtUtils.isTokenValid(token)) {
             throw new BadRequestException("Invalid or expired token");
         }

         Stay stay = Stay.builder()
                 .stayName(addStayRequest.getStayName())
                 .description(addStayRequest.getDescription())
                 .city(addStayRequest.getCity())
                 .state(addStayRequest.getState())
                 .country(addStayRequest.getCountry())
                 .images(addStayRequest.getImages())
                 .amenities(addStayRequest.getAmenities())
                 .basePrice(addStayRequest.getBasePrice())
                 .price7Days(addStayRequest.getPrice7Days())
                 .price15Days(addStayRequest.getPrice15Days())
                 .price30Days(addStayRequest.getPrice30Days())
                 .maxGuests(addStayRequest.getMaxGuests())
                 .bedrooms(addStayRequest.getBedrooms())
                 .bathrooms(addStayRequest.getBathrooms())
                 .isAvailable(true)
                 .rating(0.0)
                 .totalReviews(0)
                 .build();

         stayRepository.save(stay);

         return "Stay added successfully";








     }



     @Transactional
     public List<Stay> getAllStay(String authHeader, GetStayRequest request){

         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
             throw new BadRequestException("Missing or invalid Authorization header");
         }
         String token = authHeader.substring(7);
         if (!jwtUtils.isTokenValid(token)) {
             throw new BadRequestException("Invalid or expired token");
         }



         return stayRepository.findStayByCity(request.getCity())
                 .orElse(Collections.emptyList());



     }


    private String getUserRole(String authHeader){
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new BadRequestException("Missing or invalid Authorization header");
        }


        String token = authHeader.substring(7);
        if (!jwtUtils.isTokenValid(token)) {
            throw new BadRequestException("Invalid or expired token");
        }
        return jwtUtils.getRoleFromToken(token);


    }










}

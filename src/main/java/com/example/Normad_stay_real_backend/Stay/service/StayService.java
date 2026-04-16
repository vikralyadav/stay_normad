package com.example.Normad_stay_real_backend.Stay.service;


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
                 .ownerId(stay.getOwnerId())
                 .build();





     }








}

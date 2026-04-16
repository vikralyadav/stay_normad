package com.example.Normad_stay_real_backend.city.service;

import com.example.Normad_stay_real_backend.city.dto.CityInsertRequest;
import com.example.Normad_stay_real_backend.city.entity.Cities;
import com.example.Normad_stay_real_backend.city.repository.CitiesRepositories;
import com.example.Normad_stay_real_backend.common.exception.BadRequestException;
import com.example.Normad_stay_real_backend.common.utils.JwtUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class CityServices {


    private final CitiesRepositories citiesRepositories;
    private final JwtUtils jwtUtils;


    @Transactional
    public List<Cities> getAllCities(String authHeader){


        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new BadRequestException("Missing or invalid Authorization header");
        }
        String token = authHeader.substring(7);
        if (!jwtUtils.isTokenValid(token)) {
            throw new BadRequestException("Invalid or expired token");
        }

        List<Cities> cities = citiesRepositories.findAll();
        return cities.stream()
                .map(city -> Cities.builder()
                        .id(city.getId())
                        .cityName(city.getCityName())
                        .build())
                .toList();

    }

    @Transactional
    public String addCities(String authHeader, CityInsertRequest cityInsertRequest) {

      String role =  getUserRole(authHeader);


        if(!"OWNER".equals(role)){
            throw new BadRequestException("You are not authorize to add city");
        }

        if(citiesRepositories.existsByCityName(cityInsertRequest.getCityName())){
            throw new BadRequestException("City Already Exist");

        }




        Cities cities = Cities
                .builder().cityName(cityInsertRequest.getCityName())
                .build();


        citiesRepositories.save(cities);


        return "New City Added Successfully";

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

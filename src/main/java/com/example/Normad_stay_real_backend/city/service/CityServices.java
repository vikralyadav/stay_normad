package com.example.Normad_stay_real_backend.city.service;

import com.example.Normad_stay_real_backend.city.dto.CityInsertRequest;
import com.example.Normad_stay_real_backend.city.entity.Cities;
import com.example.Normad_stay_real_backend.city.repository.CitiesRepositories;
import com.example.Normad_stay_real_backend.common.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static ch.qos.logback.classic.spi.ThrowableProxyVO.build;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityServices {


    private final CitiesRepositories citiesRepositories;
    public List<Cities> getAllCities(){

        List<Cities> cities = citiesRepositories.findAll();
        return cities.stream()
                .map(city -> Cities.builder()
                        .id(city.getId())
                        .cityName(city.getCityName())
                        .build())
                .toList();

    }


    public String addCities(CityInsertRequest cityInsertRequest) {

        if(citiesRepositories.existsByCityName(cityInsertRequest.getCityName())){
            throw new BadRequestException("City Already Exist");

        }


        Cities cities = Cities
                .builder().cityName(cityInsertRequest.getCityName())
                .build();


        citiesRepositories.save(cities);


        return "New City Added Successfully";

    }




}

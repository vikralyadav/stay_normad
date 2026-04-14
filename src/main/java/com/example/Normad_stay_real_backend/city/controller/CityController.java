package com.example.Normad_stay_real_backend.city.controller;

import com.example.Normad_stay_real_backend.auth.dto.LoginRequest;
import com.example.Normad_stay_real_backend.city.dto.CityInsertRequest;
import com.example.Normad_stay_real_backend.city.entity.Cities;
import com.example.Normad_stay_real_backend.city.service.CityServices;
import com.example.Normad_stay_real_backend.common.dto.ApiResponse;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dest")
@RequiredArgsConstructor
public class CityController {



    private final CityServices cityServices;


    @GetMapping("/getCities")

    public ResponseEntity<ApiResponse<List<Cities>>> getAllCities(){
        List<Cities> cityList = cityServices.getAllCities();

        ApiResponse<List<Cities>> response = ApiResponse.<List<Cities>>builder()
                .statusCode("200")
                .success(true)
                .message("Cities fetched successfully")
                .data(cityList)
                .build();

        return ResponseEntity.ok(response);

    }


    @PostMapping("/addCity")

    public ResponseEntity<ApiResponse<String>> addNewCity(@Valid @RequestBody CityInsertRequest request){
         String res =  cityServices.addCities(request);


         return ResponseEntity.status(HttpStatus.OK)
                 .body(ApiResponse.success("New City Added Success", res));

    }
}

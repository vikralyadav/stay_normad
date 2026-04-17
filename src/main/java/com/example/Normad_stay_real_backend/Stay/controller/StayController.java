package com.example.Normad_stay_real_backend.Stay.controller;


import com.example.Normad_stay_real_backend.Stay.dto.AddStayRequest;
import com.example.Normad_stay_real_backend.Stay.dto.StayDetailRequest;
import com.example.Normad_stay_real_backend.Stay.dto.StayDetailResponse;
import com.example.Normad_stay_real_backend.Stay.service.StayService;
import com.example.Normad_stay_real_backend.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stays")
@RequiredArgsConstructor
public class StayController {



    private final StayService stayService;


    @PostMapping("/getStayDetails")
    private ResponseEntity<ApiResponse<StayDetailResponse>> _getStayDetails(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody StayDetailRequest request){

        StayDetailResponse res = stayService.getStayDetails(authHeader, request);


        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("New City Added Success", res));

    }


    @PostMapping("/addYourStay")
    private ResponseEntity<ApiResponse<String>> addYourStay(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody AddStayRequest request){
        String res = stayService.addyourStay(authHeader, request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("city addded success", res));
    }









}

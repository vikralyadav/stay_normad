package com.example.Normad_stay_real_backend.bookings.controller;
import com.example.Normad_stay_real_backend.bookings.dto.CreateBookingRequest;
import com.example.Normad_stay_real_backend.bookings.service.BookingService;
import com.example.Normad_stay_real_backend.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

  private final BookingService bookingService;



    @PostMapping("/createBooking")
    public ResponseEntity<ApiResponse<String>> createBooking(@RequestHeader("Authorization") String authHeader, @RequestBody CreateBookingRequest request){
      String res = bookingService.createBooking(authHeader, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Booking Created", res));


    }



}

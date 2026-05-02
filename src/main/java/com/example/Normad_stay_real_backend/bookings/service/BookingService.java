package com.example.Normad_stay_real_backend.bookings.service;


import com.example.Normad_stay_real_backend.bookings.dto.CreateBookingRequest;
import com.example.Normad_stay_real_backend.bookings.entity.BookingEntity;
import com.example.Normad_stay_real_backend.bookings.enums.BookingStatus;
import com.example.Normad_stay_real_backend.bookings.enums.PaymentStatus;
import com.example.Normad_stay_real_backend.bookings.repository.BookingRepository;
import com.example.Normad_stay_real_backend.common.exception.BadRequestException;
import com.example.Normad_stay_real_backend.common.utils.JwtUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {


    private final BookingRepository bookingRepository;
    private final JwtUtils jwtUtils;



    @Transactional
    public String createBooking(String authHeader, CreateBookingRequest request) {

        String role = getUserRole(authHeader);

        if (!"USER".equals(role)) {
            throw new BadRequestException("You are not authorized to create booking");
        }

        int totalDays = 7;

        LocalDate checkIn = request.getCheckInDate();
        LocalDate checkOut = checkIn.plusDays(totalDays);



        BookingEntity booking = BookingEntity.builder()
                .stayId(request.stay_id)
                .checkInDate(checkIn)
                .checkOutDate(checkOut)
                .totalDays(totalDays)
                .packageType(request.getPackageType())
                .totalAmount(500.0)
                .guests(request.getGuests())
                .status(BookingStatus.PENDING)
                .paymentStatus(PaymentStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        bookingRepository.save(booking);

        return "Booking created successfully";
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

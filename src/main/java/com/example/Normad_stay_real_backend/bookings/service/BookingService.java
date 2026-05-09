package com.example.Normad_stay_real_backend.bookings.service;


import com.example.Normad_stay_real_backend.bookings.dto.CreateBookingRequest;
import com.example.Normad_stay_real_backend.bookings.entity.BookingEntity;
import com.example.Normad_stay_real_backend.bookings.enums.BookingStatus;
import com.example.Normad_stay_real_backend.bookings.enums.PaymentStatus;
import com.example.Normad_stay_real_backend.bookings.repository.BookingRepository;
import com.example.Normad_stay_real_backend.common.exception.BadRequestException;
import com.example.Normad_stay_real_backend.common.exception.ResourceNotFoundException;
import com.example.Normad_stay_real_backend.common.utils.JwtUtils;
import com.example.Normad_stay_real_backend.user.entity.UserDetail;
import com.example.Normad_stay_real_backend.user.repository.UserDetailsRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
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
    private final UserDetailsRepository userDetailsRepository;



    @Transactional
    public String createBooking(String authHeader, CreateBookingRequest request) {

        String token = authHeader.substring(7);
        String email = request.getEmail();

        UserDetail user = userDetailsRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        int totalDays = 7;
        LocalDate checkIn = request.getCheckInDate();
        LocalDate checkOut = checkIn.plusDays(totalDays);

        BookingEntity booking = BookingEntity.builder()
                .userId(user.getId())
                .stayId(request.getStay_id())
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


}

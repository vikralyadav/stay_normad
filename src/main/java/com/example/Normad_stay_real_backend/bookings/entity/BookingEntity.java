package com.example.Normad_stay_real_backend.bookings.entity;


import com.example.Normad_stay_real_backend.bookings.enums.BookingStatus;
import com.example.Normad_stay_real_backend.bookings.enums.PackageType;
import com.example.Normad_stay_real_backend.bookings.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "bookings")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID bookingId;

    @Column(nullable = false)
    private UUID stayId;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private LocalDate checkInDate;

    @Column(nullable = false)
    private LocalDate checkOutDate;

    @Column(nullable = false)
    private Integer totalDays;

    @Enumerated(EnumType.STRING)
    private PackageType packageType;

    @Column(nullable = false)
    private Double totalAmount;

    private Integer guests;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private String paymentId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


package com.example.Normad_stay_real_backend.bookings.repository;

import com.example.Normad_stay_real_backend.bookings.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {

}

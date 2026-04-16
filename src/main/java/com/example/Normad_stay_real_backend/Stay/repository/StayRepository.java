package com.example.Normad_stay_real_backend.Stay.repository;

import com.example.Normad_stay_real_backend.Stay.entity.Stay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StayRepository extends JpaRepository<Stay, UUID> {

    Optional<Stay> findById(UUID uuid);
}

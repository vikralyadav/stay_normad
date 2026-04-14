package com.example.Normad_stay_real_backend.city.repository;

import com.example.Normad_stay_real_backend.city.entity.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CitiesRepositories extends JpaRepository<Cities, UUID> {
    boolean existsByCityName(String cityName);

}

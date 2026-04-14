package com.example.Normad_stay_real_backend.auth.repository;

import com.example.Normad_stay_real_backend.auth.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;



@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, UUID> {

    Optional<UserCredential> findById(UUID uuid);
    Optional<UserCredential> findByPhoneNo(String phoneNo);
    boolean existsByEmail(String email);
    boolean existsByPhoneNo(String phoneNo);

}

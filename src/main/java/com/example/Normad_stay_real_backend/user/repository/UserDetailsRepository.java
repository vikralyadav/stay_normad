package com.example.Normad_stay_real_backend.user.repository;



import com.example.Normad_stay_real_backend.user.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetail, UUID> {

    Optional<UserDetail> findById(UUID id);
    Optional<UserDetail> findByCredentialId(UUID credentialId);

}

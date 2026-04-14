package com.example.Normad_stay_real_backend.auth.entity;


import com.example.Normad_stay_real_backend.common.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_credentials")
public class UserCredential {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 10)
    private String phoneNo;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;


    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;



    @PrePersist
    public void prePersist(){

        if(this.role == null){
            this.role = Role.USER;
        }
        if(this.createdAt == null){
            this.createdAt = LocalDateTime.now();
        }

    }




}

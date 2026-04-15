package com.example.Normad_stay_real_backend.user.entity;

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
@Table(name = "user_details")
public class UserDetail {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private UUID credentialId;

    @Column(unique = false, nullable = true)
    private String username;

    @Column(nullable = true)
    private String profilePic;

    @Column(nullable = true)
    private String bio;

    @Column(nullable = true)
    private String address;

    @Column(nullable = false, unique = true, length = 10)
    private String phoneNo;

    @Column(nullable = false, unique = true, length = 100)
    private String email;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;



    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime updatedAt;


    @PrePersist
    public void prePersist(){
        if(this.createdAt == null){
            this.createdAt = LocalDateTime.now();
        }

    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }


}

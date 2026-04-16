package com.example.Normad_stay_real_backend.common.utils;

import com.example.Normad_stay_real_backend.common.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;



@Component
public class JwtUtils {


    private final SecretKey key;
    private final long accessTokenExpiry;


    public JwtUtils(
            @Value("${nomadstay.jwt.secret}") String secret,
            @Value("${nomadstay.jwt.access-token-expiry}") long accessTokenExpiry
            ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpiry = accessTokenExpiry;
    }


    public String generateAccessToken(String phoneNo, Role role, UUID userId) {
        return Jwts.builder()
                .subject(phoneNo)
                .claim("role", role)
                .claim("userId", userId.toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpiry))
                .signWith(key)
                .compact();
    }


    public Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public UUID getUserIdFromToken(String token) {
        Claims claims = parseToken(token);
        return UUID.fromString(claims.get("userId", String.class));
    }


//    public Role getUserRole(String token){
//        Claims claims = parseToken(token);
//
//        return Role.fromRole(claims.get("Role", String.class));
//    }

    public String getRoleFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("role", String.class);
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = parseToken(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }


}

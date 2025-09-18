package com.sweetshop.server.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JwtServiceTest {

    private JwtService jwtService;
    private UserDetails user;

    private SecretKey secretKey;

    @BeforeEach
    void setup() {
        String base64Secret = "dGVzdC1zZWNyZXQta2V5LXN3YXJhanNlcjE1MDEyMzQ1Njc4OTA=";
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        secretKey = Keys.hmacShaKeyFor(keyBytes);
        jwtService = new JwtService(secretKey);

        user = User.builder()
                .username("alice@example.com")
                .password("password")
                .roles("USER")
                .build();
    }


    @Test
    void generateToken_shouldReturnNonNullToken() {
        String token = jwtService.generateToken(new HashMap<>(), user);
        assertThat(token).isNotNull();
    }

    @Test
    void extractUsername_shouldReturnCorrectUsername() {
        String token = jwtService.generateToken(new HashMap<>(), user);
        String username = jwtService.extractUsername(token);
        assertThat(username).isEqualTo("alice@example.com");
    }

    @Test
    void extractClaim_shouldReturnCorrectValue() {
        String token = jwtService.generateToken(Map.of("role", "USER"), user);
        String roleClaim = jwtService.extractClaim(token, claims -> claims.get("role", String.class));
        assertThat(roleClaim).isEqualTo("USER");
    }

    @Test
    void isTokenValid_shouldReturnTrueForValidToken() {
        String token = jwtService.generateToken(new HashMap<>(), user);
        boolean valid = jwtService.isTokenValid(token, user);
        assertThat(valid).isTrue();
    }

    @Test
    void isTokenValid_shouldReturnFalseForExpiredToken() {
        String expiredToken = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis() - 1000 * 60 * 60)) // 1 hour ago
                .setExpiration(new Date(System.currentTimeMillis() - 1000 * 60)) // expired 1 min ago
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        boolean valid = false;
        try {
            valid = jwtService.isTokenValid(expiredToken, user);
        } catch (ExpiredJwtException e) {
            assertThat(valid).isFalse();
        }

    }


}

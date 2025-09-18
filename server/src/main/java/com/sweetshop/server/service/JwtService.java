package com.sweetshop.server.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j

public class JwtService {

    private final SecretKey signingKey;

    public Claims extractAllClaims(String jwt){
       throw new UnsupportedOperationException("method not implemented yet");
    }
    public <T> T extractClaim(String jwt, Function<Claims,T> claimsResolver){
        throw new UnsupportedOperationException("method not implemented yet");

    }
    public String extractUsername(String jwt) {
        throw new UnsupportedOperationException("method not implemented yet");

    }
    public Date extractExpiration(String jwt) {
        throw new UnsupportedOperationException("method not implemented yet");
    }
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        throw new UnsupportedOperationException("method not implemented yet");

    }

    public String generateToken(UserDetails userDetails) {
        throw new UnsupportedOperationException("method not implemented yet");

    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        throw new UnsupportedOperationException("method not implemented yet");

    }

    public boolean isTokenExpired(String jwt) {
        throw new UnsupportedOperationException("method not implemented yet");
    }

    public String extractToken(HttpServletRequest request) {
        throw new UnsupportedOperationException("method not implemented yet");

    }
}

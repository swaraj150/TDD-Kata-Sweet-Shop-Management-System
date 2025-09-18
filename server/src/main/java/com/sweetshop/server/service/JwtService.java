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
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
    public <T> T extractClaim(String jwt, Function<Claims,T> claimsResolver){
        Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }
    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }
    public Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        String username = extractUsername(jwt);
        return !isTokenExpired(jwt) && username.equals(userDetails.getUsername());
    }

    public boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    public String extractToken(HttpServletRequest request) {
        String token = request.getParameter("token");
        if (token != null) {
            return token;
        }
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;


    }
}

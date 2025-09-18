package com.sweetshop.server.config;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtConfig {
        @Value("${SECRET_KEY}")
        private String secretKey;

        @Bean
        public SecretKey jwtSigningKey(){
            try {
                byte[] keyBytes = Decoders.BASE64.decode(secretKey);
                return Keys.hmacShaKeyFor(keyBytes);
            } catch (IllegalArgumentException e){
                throw new IllegalStateException("Invalid JWT secret key in application properties", e);
            }
        }
}

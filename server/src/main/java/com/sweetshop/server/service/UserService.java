package com.sweetshop.server.service;

import com.sweetshop.server.dto.user.request.CreateUserRequest;
import com.sweetshop.server.dto.user.request.LoginUserRequest;
import com.sweetshop.server.dto.user.response.UserResponse;
import com.sweetshop.server.entity.User;
import com.sweetshop.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private final AuthenticationManager authenticationManager;
   private final JwtService jwtService;
   private final UserDetailsService userDetailsService;


    public UserResponse createUser(CreateUserRequest request){
        throw new UnsupportedOperationException("createUser method is not implemented yet");
    }
    public UserResponse authenticate(LoginUserRequest request){
        throw new UnsupportedOperationException("authenticate method is not implemented yet");

    }

    public UserResponse loadUser(String email){
        throw new UnsupportedOperationException("loadUser method is not implemented yet");

    }
    public UserResponse loadCurrentUser(){
        throw new UnsupportedOperationException("loadCurrentUser method is not implemented yet");
    }
}


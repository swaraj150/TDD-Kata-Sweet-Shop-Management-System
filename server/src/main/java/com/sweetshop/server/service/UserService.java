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
        User user=new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        String jwtToken=jwtService.generateToken(user);
        return UserResponse.toUserResponse(user,jwtService.generateToken(user));
    }
    public UserResponse authenticate(LoginUserRequest request){
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword());
        authenticationManager.authenticate(authenticationToken);
        UserDetails user=userDetailsService.loadUserByUsername(request.getEmail());
        String jwtToken=jwtService.generateToken(user);
        return UserResponse.toUserResponse((User)user,jwtToken);
    }

    public UserResponse loadUser(String email){
        return UserResponse.toUserResponse((User)userDetailsService.loadUserByUsername(email));
    }
    public UserResponse loadCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new SecurityException("No authentication information found");
        }
        String email=authentication.getName();
        return loadUser(email);
    }
}


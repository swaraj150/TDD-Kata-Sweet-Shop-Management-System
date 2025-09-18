package com.sweetshop.server.service;


import com.sweetshop.server.dto.user.request.*;
import com.sweetshop.server.dto.user.response.UserResponse;
import com.sweetshop.server.entity.User;
import com.sweetshop.server.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtService jwtService;
    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_shouldSaveUserAndReturnResponse() {
        CreateUserRequest request = new CreateUserRequest("Alice", "alice@example.com", "Password@123","1234567890");
        User user = new User();
        user.setName("Alice");
        user.setEmail("alice@example.com");
        user.setPhoneNumber("1234567890");
        user.setPassword("encodedPassword");

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtService.generateToken(any(User.class))).thenReturn("jwt-token");

        UserResponse response = userService.createUser(request);

        assertThat(response.getEmail()).isEqualTo("alice@example.com");
        assertThat(response.getJwtToken()).isEqualTo("jwt-token");

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void authenticate_shouldReturnUserResponse() {
        LoginUserRequest request = new LoginUserRequest("bob@example.com", "password");
        User user = new User();
        user.setEmail("bob@example.com");
        user.setPassword("encodedPassword");

        when(userDetailsService.loadUserByUsername("bob@example.com")).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn("jwt-token");

        UserResponse response = userService.authenticate(request);

        assertThat(response.getEmail()).isEqualTo("bob@example.com");
        assertThat(response.getJwtToken()).isEqualTo("jwt-token");

        verify(authenticationManager, times(1))
                .authenticate(any());
    }
}

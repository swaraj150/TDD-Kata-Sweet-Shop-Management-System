package com.sweetshop.server.controller;

import com.sweetshop.server.dto.user.request.CreateUserRequest;
import com.sweetshop.server.dto.user.request.LoginUserRequest;
import com.sweetshop.server.dto.user.response.UserResponse;
import com.sweetshop.server.entity.UserRole;
import com.sweetshop.server.service.JwtService;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sweetshop.server.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@WebMvcTest(controllers = UserController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private UserDetailsService userDetailsService;

    @Test
    void registerUser_shouldReturnUserResponse() throws Exception {
        CreateUserRequest request = new CreateUserRequest("John Doe","john@example.com","Password@123", "1234567890");
        UserResponse mockResponse = new UserResponse();
        mockResponse.setEmail("john@example.com");
        mockResponse.setJwtToken("fake_jwt_token");
        Mockito.when(userService.createUser(Mockito.any())).thenReturn(mockResponse);
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andExpect(jsonPath("$.jwtToken").value("fake_jwt_token"));
    }

    @Test
    void loginUser_shouldReturnToken() throws Exception {
        LoginUserRequest request = new LoginUserRequest("john@example.com", "Password@123");
        UserResponse mockResponse = new UserResponse();
        mockResponse.setEmail("john@example.com");
        mockResponse.setJwtToken("fake_jwt_token");
        Mockito.when(userService.authenticate(Mockito.any())).thenReturn(mockResponse);
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andExpect(jsonPath("$.jwtToken").value("fake_jwt_token"));
    }
}

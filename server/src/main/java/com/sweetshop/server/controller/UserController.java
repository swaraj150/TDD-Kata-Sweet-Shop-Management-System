package com.sweetshop.server.controller;

import com.sweetshop.server.dto.user.request.CreateUserRequest;
import com.sweetshop.server.dto.user.request.LoginUserRequest;
import com.sweetshop.server.dto.user.response.UserResponse;
import com.sweetshop.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody CreateUserRequest request){
        log.info("Registering user: {}", request.getEmail());
        UserResponse response=userService.createUser(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody LoginUserRequest request){
        log.info("Authenticating user: {}", request.getEmail());
        UserResponse response=userService.authenticate(request);
        return ResponseEntity.ok(response);
    }


}

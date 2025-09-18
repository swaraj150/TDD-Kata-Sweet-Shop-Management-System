package com.sweetshop.server.controller;

import com.sweetshop.server.dto.user.request.CreateUserRequest;
import com.sweetshop.server.dto.user.request.LoginUserRequest;
import com.sweetshop.server.dto.user.response.UserResponse;
import com.sweetshop.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(CreateUserRequest request){
        UserResponse response=userService.createUser(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(LoginUserRequest request){
        UserResponse response=userService.authenticate(request);
        return ResponseEntity.ok(response);
    }


}

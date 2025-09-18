package com.sweetshop.server.dto.user.response;

import com.sweetshop.server.entity.User;
import com.sweetshop.server.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String name;
    private String email;
    private String phoneNumber;
    private String jwtToken;
    private UserRole role;


    public static UserResponse toUserResponse(User user,String jwtToken){
        return UserResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .jwtToken(jwtToken)
                .build();
    }
    public static UserResponse toUserResponse(User user){
        return UserResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .build();
    }
}

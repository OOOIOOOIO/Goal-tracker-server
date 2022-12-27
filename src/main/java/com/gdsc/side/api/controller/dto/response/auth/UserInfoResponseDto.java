package com.gdsc.side.api.controller.dto.response.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponseDto {
    private Long userId;
    private String username;
//    private String email;
    private List<String> roles;
    private String accessToken;
    private String refreshToken;
}

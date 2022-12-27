package com.gdsc.side.api.controller.dto.request.user;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String username;
    private String password;
}

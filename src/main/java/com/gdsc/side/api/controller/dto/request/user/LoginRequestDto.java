package com.gdsc.side.api.controller.dto.request.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequestDto {
//    @Email
    @NotNull
    private String username;
    @NotNull
    private String password;
}

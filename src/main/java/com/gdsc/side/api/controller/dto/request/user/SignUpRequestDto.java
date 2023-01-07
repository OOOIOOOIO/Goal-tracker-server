package com.gdsc.side.api.controller.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
//    @Email
    @NotNull
    private String username;
//    private String email;
    @NotNull
    private String password;
    @NotNull
    private Set<String> role;

}

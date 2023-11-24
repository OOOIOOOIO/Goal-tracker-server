package com.gdsc.side.api.service;

import com.gdsc.side.api.controller.dto.request.user.SignUpRequestDto;

public interface AuthService {

     boolean isUserExist(String username, String password);

     void signUp(SignUpRequestDto signUpRequestDto);
}

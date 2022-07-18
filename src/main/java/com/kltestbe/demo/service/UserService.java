package com.kltestbe.demo.service;

import com.kltestbe.demo.dto.LoginDto;
import com.kltestbe.demo.dto.SignUpDto;

public interface UserService {
    SignUpDto signUp(SignUpDto signUpDto);
    LoginDto login(LoginDto loginRequest);
}

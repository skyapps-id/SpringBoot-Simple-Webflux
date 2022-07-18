package com.kltestbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kltestbe.demo.dto.LoginDto;
import com.kltestbe.demo.dto.SignUpDto;
import com.kltestbe.demo.service.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping(path = "/signup", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SignUpDto> signUp(@RequestBody  SignUpDto signUpRequest) {
		return ResponseEntity.ok(userService.signUp(signUpRequest));
	}

	@PostMapping(path = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<LoginDto> login(@RequestBody  LoginDto loginDto) {
		return ResponseEntity.ok(userService.login(loginDto));
	}

}

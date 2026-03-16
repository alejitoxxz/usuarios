package com.co.eatupapi.controller;

import com.co.eatupapi.dto.UserDtos;
import com.co.eatupapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDtos.UserResponse> register(@Valid @RequestBody UserDtos.CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDtos.LoginResponse> login(@Valid @RequestBody UserDtos.LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }
}

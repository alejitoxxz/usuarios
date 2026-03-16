package com.co.eatupapi.controller;

import com.co.eatupapi.domain.UserStatus;
import com.co.eatupapi.dto.UserDtos;
import com.co.eatupapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDtos.UserSummaryResponse>> getUsers(@RequestParam(required = false) UserStatus status) {
        return ResponseEntity.ok(userService.getUsers(status));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDtos.UserResponse> getUserById(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDtos.UserResponse> updateUser(@PathVariable UUID userId,
                                                            @Valid @RequestBody UserDtos.UpdateUserRequest request) {
        return ResponseEntity.ok(userService.updateUser(userId, request));
    }

    @PatchMapping("/{userId}/status")
    public ResponseEntity<UserDtos.UserResponse> updateStatus(@PathVariable UUID userId,
                                                              @Valid @RequestBody UserDtos.UpdateUserStatusRequest request) {
        return ResponseEntity.ok(userService.updateUserStatus(userId, request));
    }
}

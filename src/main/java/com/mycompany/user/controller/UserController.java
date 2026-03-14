package com.mycompany.user.controller;

import com.mycompany.user.dto.request.CreateUserRequest;
import com.mycompany.user.dto.request.UpdateUserRequest;
import com.mycompany.user.dto.request.UpdateUserStatusRequest;
import com.mycompany.user.dto.response.UserResponse;
import com.mycompany.user.dto.response.UserSummaryResponse;
import com.mycompany.user.enums.UserStatus;
import com.mycompany.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }

    @GetMapping
    public ResponseEntity<List<UserSummaryResponse>> getUsers(@RequestParam(required = false) UserStatus status) {
        if (status != null) {
            return ResponseEntity.ok(userService.getUsersByStatus(status));
        }
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID userId,
                                                   @Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.updateUser(userId, request));
    }

    @PatchMapping("/{userId}/status")
    public ResponseEntity<UserResponse> updateUserStatus(@PathVariable UUID userId,
                                                         @Valid @RequestBody UpdateUserStatusRequest request) {
        return ResponseEntity.ok(userService.updateUserStatus(userId, request));
    }
}

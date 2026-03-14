package com.co.eatupapi.controllers.user.user;

import com.co.eatupapi.domain.user.user.UserStatus;
import com.co.eatupapi.dto.user.user.CreateUserRequest;
import com.co.eatupapi.dto.user.user.UpdateUserRequest;
import com.co.eatupapi.dto.user.user.UpdateUserStatusRequest;
import com.co.eatupapi.dto.user.user.UserResponse;
import com.co.eatupapi.dto.user.user.UserSummaryResponse;
import com.co.eatupapi.services.user.user.UserService;
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
        return ResponseEntity.ok(userService.getUsers(status));
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

package com.co.eatupapi.service;

import com.co.eatupapi.domain.UserStatus;
import com.co.eatupapi.dto.UserDtos;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDtos.UserResponse register(UserDtos.CreateUserRequest request);
    UserDtos.LoginResponse login(UserDtos.LoginRequest request);
    List<UserDtos.UserSummaryResponse> getUsers(UserStatus status);
    UserDtos.UserResponse getUserById(UUID userId);
    UserDtos.UserResponse updateUser(UUID userId, UserDtos.UpdateUserRequest request);
    UserDtos.UserResponse updateUserStatus(UUID userId, UserDtos.UpdateUserStatusRequest request);
}

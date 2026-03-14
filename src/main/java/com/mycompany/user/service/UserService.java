package com.mycompany.user.service;

import com.mycompany.user.dto.request.CreateUserRequest;
import com.mycompany.user.dto.request.UpdateUserRequest;
import com.mycompany.user.dto.request.UpdateUserStatusRequest;
import com.mycompany.user.dto.response.UserResponse;
import com.mycompany.user.dto.response.UserSummaryResponse;
import com.mycompany.user.enums.UserStatus;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    List<UserSummaryResponse> getAllUsers();

    UserResponse getUserById(UUID userId);

    List<UserSummaryResponse> getUsersByStatus(UserStatus status);

    UserResponse updateUser(UUID userId, UpdateUserRequest request);

    UserResponse updateUserStatus(UUID userId, UpdateUserStatusRequest request);
}

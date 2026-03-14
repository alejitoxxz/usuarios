package com.co.eatupapi.services.user.user;

import com.co.eatupapi.domain.user.user.UserStatus;
import com.co.eatupapi.dto.user.user.CreateUserRequest;
import com.co.eatupapi.dto.user.user.UpdateUserRequest;
import com.co.eatupapi.dto.user.user.UpdateUserStatusRequest;
import com.co.eatupapi.dto.user.user.UserResponse;
import com.co.eatupapi.dto.user.user.UserSummaryResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    List<UserSummaryResponse> getUsers(UserStatus status);

    UserResponse getUserById(UUID userId);

    UserResponse updateUser(UUID userId, UpdateUserRequest request);

    UserResponse updateUserStatus(UUID userId, UpdateUserStatusRequest request);
}

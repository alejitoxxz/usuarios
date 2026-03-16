package com.co.eatupapi.dto;

import com.co.eatupapi.domain.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public final class UserDtos {
    private UserDtos() {}

    public record CreateUserRequest(
            @NotBlank String firstName,
            @NotBlank String lastName,
            @NotNull UUID documentTypeId,
            @NotBlank String documentNumber,
            @NotBlank String phone,
            @NotBlank @Email String email,
            @NotBlank String password,
            @NotNull LocalDate birthDate,
            @NotNull UUID departmentId,
            @NotNull UUID cityId,
            @NotBlank String address,
            @NotNull UUID branchId
    ) {}

    public record UpdateUserRequest(
            @NotBlank String firstName,
            @NotBlank String lastName,
            @NotNull UUID documentTypeId,
            @NotBlank String documentNumber,
            @NotBlank String phone,
            @NotBlank @Email String email,
            @NotNull LocalDate birthDate,
            @NotNull UUID departmentId,
            @NotNull UUID cityId,
            @NotBlank String address,
            @NotNull UUID branchId
    ) {}

    public record UpdateUserStatusRequest(@NotNull UserStatus status) {}

    public record UserResponse(
            UUID id,
            String firstName,
            String lastName,
            String documentType,
            String documentNumber,
            String phone,
            String email,
            LocalDate birthDate,
            String department,
            String city,
            String address,
            String branch,
            UserStatus status
    ) {}

    public record UserSummaryResponse(
            UUID id,
            String firstName,
            String lastName,
            String documentNumber,
            String email,
            String phone,
            String branch,
            UserStatus status
    ) {}

    public record LoginRequest(@NotBlank @Email String email, @NotBlank String password) {}

    public record LoginResponse(String token, String tokenType, long expiresInSeconds) {}
}

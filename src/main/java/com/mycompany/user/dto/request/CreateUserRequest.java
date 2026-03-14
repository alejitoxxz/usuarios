package com.mycompany.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CreateUserRequest(
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
) {
}

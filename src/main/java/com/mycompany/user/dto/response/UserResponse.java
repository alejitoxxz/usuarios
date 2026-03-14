package com.mycompany.user.dto.response;

import com.mycompany.user.enums.UserStatus;

import java.time.LocalDate;
import java.util.UUID;

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
) {
}

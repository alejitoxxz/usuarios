package com.mycompany.user.dto.response;

import com.mycompany.user.enums.UserStatus;

import java.util.UUID;

public record UserSummaryResponse(
        UUID id,
        String firstName,
        String lastName,
        String documentNumber,
        String email,
        String phone,
        String branch,
        UserStatus status
) {
}

package com.mycompany.user.dto.response;

import java.util.UUID;

public record DepartmentResponse(
        UUID id,
        String name
) {
}

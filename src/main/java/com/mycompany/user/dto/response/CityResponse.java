package com.mycompany.user.dto.response;

import java.util.UUID;

public record CityResponse(
        UUID id,
        String name,
        UUID departmentId
) {
}

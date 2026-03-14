package com.mycompany.user.dto.response;

import java.util.UUID;

public record DocumentTypeResponse(
        UUID id,
        String code,
        String name
) {
}

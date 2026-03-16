package com.co.eatupapi.dto;

import java.util.UUID;

public final class CatalogDtos {
    private CatalogDtos() {}

    public record DepartmentResponse(UUID id, String name) {}

    public record CityResponse(UUID id, UUID departmentId, String name) {}

    public record DocumentTypeResponse(UUID id, String code, String name) {}
}

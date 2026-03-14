package com.co.eatupapi.domain.user.user;

import java.util.Map;
import java.util.UUID;

public final class BranchMockData {

    public static final UUID DOCUMENT_TYPE_CC_ID = UUID.fromString("11111111-1111-1111-1111-111111111111");
    public static final UUID DEPARTMENT_ANTIOQUIA_ID = UUID.fromString("22222222-2222-2222-2222-222222222222");
    public static final UUID CITY_MEDELLIN_ID = UUID.fromString("33333333-3333-3333-3333-333333333333");
    public static final UUID BRANCH_MEDELLIN_CENTRO_ID = UUID.fromString("44444444-4444-4444-4444-444444444444");
    public static final UUID BRANCH_LAURELES_ID = UUID.fromString("55555555-5555-5555-5555-555555555555");

    private static final Map<UUID, String> DOCUMENT_TYPES = Map.of(
            DOCUMENT_TYPE_CC_ID, "Cedula de ciudadania"
    );

    private static final Map<UUID, String> DEPARTMENTS = Map.of(
            DEPARTMENT_ANTIOQUIA_ID, "Antioquia"
    );

    private static final Map<UUID, String> CITIES = Map.of(
            CITY_MEDELLIN_ID, "Medellin"
    );

    private static final Map<UUID, String> BRANCHES = Map.of(
            BRANCH_MEDELLIN_CENTRO_ID, "Sede Medellin Centro",
            BRANCH_LAURELES_ID, "Sede Medellin Laureles"
    );

    private BranchMockData() {
    }

    public static String getDocumentTypeName(UUID documentTypeId) {
        return DOCUMENT_TYPES.getOrDefault(documentTypeId, "Catalogo no encontrado");
    }

    public static String getDepartmentName(UUID departmentId) {
        return DEPARTMENTS.getOrDefault(departmentId, "Catalogo no encontrado");
    }

    public static String getCityName(UUID cityId) {
        return CITIES.getOrDefault(cityId, "Catalogo no encontrado");
    }

    public static String getBranchName(UUID branchId) {
        return BRANCHES.getOrDefault(branchId, "Sede no encontrada");
    }
}

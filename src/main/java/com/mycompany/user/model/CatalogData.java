package com.mycompany.user.model;

import com.mycompany.user.dto.response.CityResponse;
import com.mycompany.user.dto.response.DepartmentResponse;
import com.mycompany.user.dto.response.DocumentTypeResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class CatalogData {

    public static final UUID DOC_CEDULA_ID = UUID.fromString("11111111-1111-1111-1111-111111111111");
    public static final UUID DOC_NIT_ID = UUID.fromString("22222222-2222-2222-2222-222222222222");

    public static final UUID DEPT_ANTIOQUIA_ID = UUID.fromString("33333333-3333-3333-3333-333333333333");
    public static final UUID DEPT_CUNDINAMARCA_ID = UUID.fromString("44444444-4444-4444-4444-444444444444");

    public static final UUID CITY_MEDELLIN_ID = UUID.fromString("55555555-5555-5555-5555-555555555555");
    public static final UUID CITY_RIONEGRO_ID = UUID.fromString("66666666-6666-6666-6666-666666666666");
    public static final UUID CITY_BOGOTA_ID = UUID.fromString("77777777-7777-7777-7777-777777777777");

    public static final List<DocumentTypeResponse> DOCUMENT_TYPES = List.of(
            new DocumentTypeResponse(DOC_CEDULA_ID, "CEDULA", "Cedula de ciudadania"),
            new DocumentTypeResponse(DOC_NIT_ID, "NIT", "Numero de Identificacion Tributaria")
    );

    public static final List<DepartmentResponse> DEPARTMENTS = List.of(
            new DepartmentResponse(DEPT_ANTIOQUIA_ID, "Antioquia"),
            new DepartmentResponse(DEPT_CUNDINAMARCA_ID, "Cundinamarca")
    );

    public static final List<CityResponse> CITIES = List.of(
            new CityResponse(CITY_MEDELLIN_ID, "Medellin", DEPT_ANTIOQUIA_ID),
            new CityResponse(CITY_RIONEGRO_ID, "Rionegro", DEPT_ANTIOQUIA_ID),
            new CityResponse(CITY_BOGOTA_ID, "Bogota", DEPT_CUNDINAMARCA_ID)
    );

    public static final Map<UUID, DocumentTypeResponse> DOCUMENT_TYPES_BY_ID = DOCUMENT_TYPES.stream()
            .collect(Collectors.toUnmodifiableMap(DocumentTypeResponse::id, Function.identity()));

    public static final Map<UUID, DepartmentResponse> DEPARTMENTS_BY_ID = DEPARTMENTS.stream()
            .collect(Collectors.toUnmodifiableMap(DepartmentResponse::id, Function.identity()));

    public static final Map<UUID, CityResponse> CITIES_BY_ID = CITIES.stream()
            .collect(Collectors.toUnmodifiableMap(CityResponse::id, Function.identity()));

    private CatalogData() {
    }
}

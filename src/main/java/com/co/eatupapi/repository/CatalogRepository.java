package com.co.eatupapi.repository;

import com.co.eatupapi.domain.City;
import com.co.eatupapi.domain.Department;
import com.co.eatupapi.domain.DocumentType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CatalogRepository {

    public static final UUID DOC_CC_ID = UUID.fromString("11111111-1111-1111-1111-111111111111");
    public static final UUID DEPT_ANT_ID = UUID.fromString("22222222-2222-2222-2222-222222222222");
    public static final UUID CITY_MED_ID = UUID.fromString("33333333-3333-3333-3333-333333333333");

    private final List<DocumentType> documentTypes = List.of(
            new DocumentType(DOC_CC_ID, "CC", "Cedula de ciudadania")
    );

    private final List<Department> departments = List.of(
            new Department(DEPT_ANT_ID, "Antioquia")
    );

    private final List<City> cities = List.of(
            new City(CITY_MED_ID, DEPT_ANT_ID, "Medellin")
    );

    public List<DocumentType> findDocumentTypes() { return documentTypes; }
    public List<Department> findDepartments() { return departments; }
    public List<City> findCities() { return cities; }
}

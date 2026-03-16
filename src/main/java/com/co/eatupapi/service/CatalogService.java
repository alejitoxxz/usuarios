package com.co.eatupapi.service;

import com.co.eatupapi.dto.CatalogDtos;

import java.util.List;

public interface CatalogService {
    List<CatalogDtos.DepartmentResponse> getDepartments();
    List<CatalogDtos.CityResponse> getCities();
    List<CatalogDtos.DocumentTypeResponse> getDocumentTypes();
}

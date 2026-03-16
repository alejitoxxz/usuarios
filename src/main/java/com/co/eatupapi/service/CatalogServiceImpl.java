package com.co.eatupapi.service;

import com.co.eatupapi.dto.CatalogDtos;
import com.co.eatupapi.repository.CatalogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public List<CatalogDtos.DepartmentResponse> getDepartments() {
        return catalogRepository.findDepartments().stream()
                .map(d -> new CatalogDtos.DepartmentResponse(d.id(), d.name()))
                .toList();
    }

    @Override
    public List<CatalogDtos.CityResponse> getCities() {
        return catalogRepository.findCities().stream()
                .map(c -> new CatalogDtos.CityResponse(c.id(), c.departmentId(), c.name()))
                .toList();
    }

    @Override
    public List<CatalogDtos.DocumentTypeResponse> getDocumentTypes() {
        return catalogRepository.findDocumentTypes().stream()
                .map(d -> new CatalogDtos.DocumentTypeResponse(d.id(), d.code(), d.name()))
                .toList();
    }
}

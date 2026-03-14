package com.mycompany.user.service.impl;

import com.mycompany.user.dto.response.CityResponse;
import com.mycompany.user.dto.response.DepartmentResponse;
import com.mycompany.user.dto.response.DocumentTypeResponse;
import com.mycompany.user.service.UserCatalogService;
import com.mycompany.user.model.CatalogData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserCatalogServiceImpl implements UserCatalogService {

    @Override
    public List<DocumentTypeResponse> getDocumentTypes() {
        return CatalogData.DOCUMENT_TYPES;
    }

    @Override
    public List<DepartmentResponse> getDepartments() {
        return CatalogData.DEPARTMENTS;
    }

    @Override
    public List<CityResponse> getCitiesByDepartment(UUID departmentId) {
        return CatalogData.CITIES.stream()
                .filter(city -> city.departmentId().equals(departmentId))
                .toList();
    }
}

package com.mycompany.user.service;

import com.mycompany.user.dto.response.CityResponse;
import com.mycompany.user.dto.response.DepartmentResponse;
import com.mycompany.user.dto.response.DocumentTypeResponse;

import java.util.List;
import java.util.UUID;

public interface UserCatalogService {

    List<DocumentTypeResponse> getDocumentTypes();

    List<DepartmentResponse> getDepartments();

    List<CityResponse> getCitiesByDepartment(UUID departmentId);
}

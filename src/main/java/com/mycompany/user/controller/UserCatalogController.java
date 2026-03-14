package com.mycompany.user.controller;

import com.mycompany.user.dto.response.CityResponse;
import com.mycompany.user.dto.response.DepartmentResponse;
import com.mycompany.user.dto.response.DocumentTypeResponse;
import com.mycompany.user.service.UserCatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user/api/v1")
public class UserCatalogController {

    private final UserCatalogService userCatalogService;

    public UserCatalogController(UserCatalogService userCatalogService) {
        this.userCatalogService = userCatalogService;
    }

    @GetMapping("/document-types")
    public ResponseEntity<List<DocumentTypeResponse>> getDocumentTypes() {
        return ResponseEntity.ok(userCatalogService.getDocumentTypes());
    }

    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentResponse>> getDepartments() {
        return ResponseEntity.ok(userCatalogService.getDepartments());
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityResponse>> getCitiesByDepartment(@RequestParam UUID departmentId) {
        return ResponseEntity.ok(userCatalogService.getCitiesByDepartment(departmentId));
    }
}

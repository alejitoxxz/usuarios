package com.co.eatupapi.controller;

import com.co.eatupapi.dto.CatalogDtos;
import com.co.eatupapi.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user-module/api/v1")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/departments")
    public ResponseEntity<List<CatalogDtos.DepartmentResponse>> getDepartments() {
        return ResponseEntity.ok(catalogService.getDepartments());
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CatalogDtos.CityResponse>> getCities(@RequestParam java.util.UUID departmentId) {
        return ResponseEntity.ok(catalogService.getCities().stream()
                .filter(city -> city.departmentId().equals(departmentId))
                .toList());
    }

    @GetMapping("/document-types")
    public ResponseEntity<List<CatalogDtos.DocumentTypeResponse>> getDocumentTypes() {
        return ResponseEntity.ok(catalogService.getDocumentTypes());
    }
}

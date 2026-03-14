package com.mycompany.user.service.impl;

import com.mycompany.user.dto.request.CreateUserRequest;
import com.mycompany.user.dto.request.UpdateUserRequest;
import com.mycompany.user.dto.request.UpdateUserStatusRequest;
import com.mycompany.user.dto.response.CityResponse;
import com.mycompany.user.dto.response.UserResponse;
import com.mycompany.user.dto.response.UserSummaryResponse;
import com.mycompany.user.enums.UserStatus;
import com.mycompany.user.exception.BusinessException;
import com.mycompany.user.exception.ResourceNotFoundException;
import com.mycompany.user.model.CatalogData;
import com.mycompany.user.model.UserRecord;
import com.mycompany.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {

    private final Map<UUID, UserRecord> users = new ConcurrentHashMap<>();

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        validateEmailUnique(request.email(), null);
        validateCityDepartmentConsistency(request.departmentId(), request.cityId());

        UserRecord user = new UserRecord();
        user.setId(UUID.randomUUID());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setDocumentTypeId(request.documentTypeId());
        user.setDocumentNumber(request.documentNumber());
        user.setPhone(request.phone());
        user.setEmail(request.email());
        user.setBirthDate(request.birthDate());
        user.setDepartmentId(request.departmentId());
        user.setCityId(request.cityId());
        user.setAddress(request.address());
        user.setBranchId(request.branchId());
        user.setStatus(UserStatus.ACTIVE);

        users.put(user.getId(), user);
        return toUserResponse(user);
    }

    @Override
    public List<UserSummaryResponse> getAllUsers() {
        return users.values().stream()
                .map(this::toSummaryResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(UUID userId) {
        UserRecord user = getExistingUser(userId);
        return toUserResponse(user);
    }

    @Override
    public List<UserSummaryResponse> getUsersByStatus(UserStatus status) {
        return users.values().stream()
                .filter(user -> user.getStatus() == status)
                .map(this::toSummaryResponse)
                .toList();
    }

    @Override
    public UserResponse updateUser(UUID userId, UpdateUserRequest request) {
        UserRecord user = getExistingUser(userId);

        // TODO: Mantener esta validación si en el futuro se habilitan payloads con email.
        validateEmailImmutable(user.getEmail(), user.getEmail());
        validateCityDepartmentConsistency(request.departmentId(), request.cityId());

        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setDocumentTypeId(request.documentTypeId());
        user.setDocumentNumber(request.documentNumber());
        user.setPhone(request.phone());
        user.setBirthDate(request.birthDate());
        user.setDepartmentId(request.departmentId());
        user.setCityId(request.cityId());
        user.setAddress(request.address());
        user.setBranchId(request.branchId());

        return toUserResponse(user);
    }

    @Override
    public UserResponse updateUserStatus(UUID userId, UpdateUserStatusRequest request) {
        UserRecord user = getExistingUser(userId);
        if (request.status() != UserStatus.ACTIVE && request.status() != UserStatus.INACTIVE) {
            throw new BusinessException("Estado de usuario no soportado");
        }
        user.setStatus(request.status());
        return toUserResponse(user);
    }

    private void validateEmailUnique(String email, UUID currentUserId) {
        boolean emailExists = users.values().stream()
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(email)
                        && (currentUserId == null || !user.getId().equals(currentUserId)));

        if (emailExists) {
            throw new BusinessException("El email ya se encuentra registrado");
        }
    }

    private void validateEmailImmutable(String currentEmail, String attemptedEmail) {
        if (!currentEmail.equalsIgnoreCase(attemptedEmail)) {
            throw new BusinessException("El email no puede modificarse");
        }
    }

    private void validateCityDepartmentConsistency(UUID departmentId, UUID cityId) {
        CityResponse city = CatalogData.CITIES_BY_ID.get(cityId);
        if (city == null) {
            throw new BusinessException("La ciudad enviada no existe en el catalogo");
        }

        if (!city.departmentId().equals(departmentId)) {
            throw new BusinessException("La ciudad no corresponde al departamento enviado");
        }
    }

    private UserRecord getExistingUser(UUID userId) {
        UserRecord user = users.get(userId);
        if (user == null) {
            throw new ResourceNotFoundException("Usuario no encontrado: " + userId);
        }
        return user;
    }

    private UserResponse toUserResponse(UserRecord user) {
        String documentTypeName = CatalogData.DOCUMENT_TYPES_BY_ID.containsKey(user.getDocumentTypeId())
                ? CatalogData.DOCUMENT_TYPES_BY_ID.get(user.getDocumentTypeId()).name()
                : "Desconocido";

        String departmentName = CatalogData.DEPARTMENTS_BY_ID.containsKey(user.getDepartmentId())
                ? CatalogData.DEPARTMENTS_BY_ID.get(user.getDepartmentId()).name()
                : "Desconocido";

        String cityName = CatalogData.CITIES_BY_ID.containsKey(user.getCityId())
                ? CatalogData.CITIES_BY_ID.get(user.getCityId()).name()
                : "Desconocido";

        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                documentTypeName,
                user.getDocumentNumber(),
                user.getPhone(),
                user.getEmail(),
                user.getBirthDate(),
                departmentName,
                cityName,
                user.getAddress(),
                resolveBranchName(user.getBranchId()),
                user.getStatus()
        );
    }

    private UserSummaryResponse toSummaryResponse(UserRecord user) {
        return new UserSummaryResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDocumentNumber(),
                user.getEmail(),
                user.getPhone(),
                resolveBranchName(user.getBranchId()),
                user.getStatus()
        );
    }

    private String resolveBranchName(UUID branchId) {
        // TODO: Reemplazar con integración real al Branch Module.
        return "Branch-" + branchId;
    }
}

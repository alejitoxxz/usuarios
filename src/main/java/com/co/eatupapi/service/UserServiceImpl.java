package com.co.eatupapi.service;

import com.co.eatupapi.client.BranchClient;
import com.co.eatupapi.domain.City;
import com.co.eatupapi.domain.Department;
import com.co.eatupapi.domain.DocumentType;
import com.co.eatupapi.domain.User;
import com.co.eatupapi.domain.UserStatus;
import com.co.eatupapi.dto.UserDtos;
import com.co.eatupapi.repository.CatalogRepository;
import com.co.eatupapi.repository.UserRepository;
import com.co.eatupapi.security.JwtService;
import com.co.eatupapi.utils.exception.BusinessException;
import com.co.eatupapi.utils.exception.ResourceNotFoundException;
import com.co.eatupapi.utils.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CatalogRepository catalogRepository;
    private final BranchClient branchClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository,
                           CatalogRepository catalogRepository,
                           BranchClient branchClient,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService) {
        this.userRepository = userRepository;
        this.catalogRepository = catalogRepository;
        this.branchClient = branchClient;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public UserDtos.UserResponse register(UserDtos.CreateUserRequest request) {
        userRepository.findByEmail(request.email())
                .ifPresent(existing -> { throw new BusinessException("El email ya se encuentra registrado"); });

        validateCatalogs(request.documentTypeId(), request.departmentId(), request.cityId());
        branchClient.validateBranchExists(request.branchId());

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setDocumentTypeId(request.documentTypeId());
        user.setDocumentNumber(request.documentNumber());
        user.setPhone(request.phone());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setBirthDate(request.birthDate());
        user.setDepartmentId(request.departmentId());
        user.setCityId(request.cityId());
        user.setAddress(request.address());
        user.setBranchId(request.branchId());
        user.setStatus(UserStatus.ACTIVE);

        return toResponse(userRepository.save(user));
    }

    @Override
    public UserDtos.LoginResponse login(UserDtos.LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new BusinessException("Credenciales invalidas"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BusinessException("Credenciales invalidas");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new UserDtos.LoginResponse(token, "Bearer", jwtService.getExpirationSeconds());
    }

    @Override
    public List<UserDtos.UserSummaryResponse> getUsers(UserStatus status) {
        List<User> users = status == null ? userRepository.findAll() : userRepository.findByStatus(status);
        return users.stream().map(user -> UserMapper.toSummary(user, "Sede " + user.getBranchId())).toList();
    }

    @Override
    public UserDtos.UserResponse getUserById(UUID userId) {
        return toResponse(getExistingUser(userId));
    }

    @Override
    public UserDtos.UserResponse updateUser(UUID userId, UserDtos.UpdateUserRequest request) {
        User user = getExistingUser(userId);

        if (!user.getEmail().equalsIgnoreCase(request.email())) {
            throw new BusinessException("El email no puede modificarse");
        }

        validateCatalogs(request.documentTypeId(), request.departmentId(), request.cityId());
        branchClient.validateBranchExists(request.branchId());

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

        return toResponse(userRepository.save(user));
    }

    @Override
    public UserDtos.UserResponse updateUserStatus(UUID userId, UserDtos.UpdateUserStatusRequest request) {
        User user = getExistingUser(userId);
        user.setStatus(request.status());
        return toResponse(userRepository.save(user));
    }

    private User getExistingUser(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado: " + userId));
    }

    private void validateCatalogs(UUID documentTypeId, UUID departmentId, UUID cityId) {
        catalogRepository.findDocumentTypes().stream().filter(it -> it.id().equals(documentTypeId)).findFirst()
                .orElseThrow(() -> new BusinessException("Tipo de documento invalido"));
        catalogRepository.findDepartments().stream().filter(it -> it.id().equals(departmentId)).findFirst()
                .orElseThrow(() -> new BusinessException("Departamento invalido"));
        catalogRepository.findCities().stream().filter(it -> it.id().equals(cityId)).findFirst()
                .orElseThrow(() -> new BusinessException("Ciudad invalida"));
    }

    private UserDtos.UserResponse toResponse(User user) {
        DocumentType documentType = catalogRepository.findDocumentTypes().stream()
                .filter(dt -> dt.id().equals(user.getDocumentTypeId())).findFirst()
                .orElseThrow(() -> new BusinessException("Tipo de documento invalido"));
        Department department = catalogRepository.findDepartments().stream()
                .filter(dept -> dept.id().equals(user.getDepartmentId())).findFirst()
                .orElseThrow(() -> new BusinessException("Departamento invalido"));
        City city = catalogRepository.findCities().stream()
                .filter(item -> item.id().equals(user.getCityId())).findFirst()
                .orElseThrow(() -> new BusinessException("Ciudad invalida"));
        return UserMapper.toResponse(user, documentType, department, city, "Sede " + user.getBranchId());
    }
}

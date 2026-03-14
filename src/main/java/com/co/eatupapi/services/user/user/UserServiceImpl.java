package com.co.eatupapi.services.user.user;

import com.co.eatupapi.domain.user.user.BranchMockData;
import com.co.eatupapi.domain.user.user.User;
import com.co.eatupapi.domain.user.user.UserStatus;
import com.co.eatupapi.dto.user.user.CreateUserRequest;
import com.co.eatupapi.dto.user.user.UpdateUserRequest;
import com.co.eatupapi.dto.user.user.UpdateUserStatusRequest;
import com.co.eatupapi.dto.user.user.UserResponse;
import com.co.eatupapi.dto.user.user.UserSummaryResponse;
import com.co.eatupapi.exceptions.BusinessException;
import com.co.eatupapi.exceptions.ResourceNotFoundException;
import com.co.eatupapi.repositories.user.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        validateEmailUnique(request.getEmail());

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDocumentTypeId(request.getDocumentTypeId());
        user.setDocumentNumber(request.getDocumentNumber());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setBirthDate(request.getBirthDate());
        user.setDepartmentId(request.getDepartmentId());
        user.setCityId(request.getCityId());
        user.setAddress(request.getAddress());
        user.setBranchId(request.getBranchId());
        user.setStatus(UserStatus.ACTIVE);

        return toUserResponse(userRepository.save(user));
    }

    @Override
    public List<UserSummaryResponse> getUsers(UserStatus status) {
        List<User> users = status == null ? userRepository.findAll() : userRepository.findByStatus(status);
        return users.stream().map(this::toSummaryResponse).toList();
    }

    @Override
    public UserResponse getUserById(UUID userId) {
        return toUserResponse(getExistingUser(userId));
    }

    @Override
    public UserResponse updateUser(UUID userId, UpdateUserRequest request) {
        User user = getExistingUser(userId);

        if (!user.getEmail().equalsIgnoreCase(request.getEmail())) {
            throw new BusinessException("El email no puede modificarse");
        }

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDocumentTypeId(request.getDocumentTypeId());
        user.setDocumentNumber(request.getDocumentNumber());
        user.setPhone(request.getPhone());
        user.setBirthDate(request.getBirthDate());
        user.setDepartmentId(request.getDepartmentId());
        user.setCityId(request.getCityId());
        user.setAddress(request.getAddress());
        user.setBranchId(request.getBranchId());

        return toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUserStatus(UUID userId, UpdateUserStatusRequest request) {
        User user = getExistingUser(userId);
        user.setStatus(request.getStatus());
        return toUserResponse(userRepository.save(user));
    }

    private User getExistingUser(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado: " + userId));
    }

    private void validateEmailUnique(String email) {
        userRepository.findByEmail(email).ifPresent(existing -> {
            throw new BusinessException("El email ya se encuentra registrado");
        });
    }

    private UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                BranchMockData.getDocumentTypeName(user.getDocumentTypeId()),
                user.getDocumentNumber(),
                user.getPhone(),
                user.getEmail(),
                user.getBirthDate(),
                BranchMockData.getDepartmentName(user.getDepartmentId()),
                BranchMockData.getCityName(user.getCityId()),
                user.getAddress(),
                BranchMockData.getBranchName(user.getBranchId()),
                user.getStatus()
        );
    }

    private UserSummaryResponse toSummaryResponse(User user) {
        return new UserSummaryResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDocumentNumber(),
                user.getEmail(),
                user.getPhone(),
                BranchMockData.getBranchName(user.getBranchId()),
                user.getStatus()
        );
    }
}

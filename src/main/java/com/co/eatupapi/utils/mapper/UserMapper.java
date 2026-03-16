package com.co.eatupapi.utils.mapper;

import com.co.eatupapi.domain.City;
import com.co.eatupapi.domain.Department;
import com.co.eatupapi.domain.DocumentType;
import com.co.eatupapi.domain.User;
import com.co.eatupapi.dto.UserDtos;

public final class UserMapper {

    private UserMapper() {
    }

    public static UserDtos.UserSummaryResponse toSummary(User user, String branchName) {
        return new UserDtos.UserSummaryResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDocumentNumber(),
                user.getEmail(),
                user.getPhone(),
                branchName,
                user.getStatus()
        );
    }

    public static UserDtos.UserResponse toResponse(User user, DocumentType documentType, Department department,
                                                   City city, String branchName) {
        return new UserDtos.UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                documentType.name(),
                user.getDocumentNumber(),
                user.getPhone(),
                user.getEmail(),
                user.getBirthDate(),
                department.name(),
                city.name(),
                user.getAddress(),
                branchName,
                user.getStatus()
        );
    }
}

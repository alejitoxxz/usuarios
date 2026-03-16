package com.co.eatupapi.repository;

import com.co.eatupapi.domain.User;
import com.co.eatupapi.domain.UserStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(UUID userId);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    List<User> findByStatus(UserStatus status);
}

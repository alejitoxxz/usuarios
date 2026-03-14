package com.co.eatupapi.repositories.user.user;

import com.co.eatupapi.domain.user.user.User;
import com.co.eatupapi.domain.user.user.UserStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(UUID userId);

    List<User> findAll();

    List<User> findByStatus(UserStatus status);

    Optional<User> findByEmail(String email);
}

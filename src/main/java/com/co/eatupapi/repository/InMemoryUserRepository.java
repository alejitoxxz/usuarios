package com.co.eatupapi.repository;

import com.co.eatupapi.domain.User;
import com.co.eatupapi.domain.UserStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final Map<UUID, User> users = new ConcurrentHashMap<>();

    @Override
    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(UUID userId) {
        return Optional.ofNullable(users.get(userId));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.values().stream().filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst();
    }

    @Override
    public List<User> findAll() {
        return users.values().stream().toList();
    }

    @Override
    public List<User> findByStatus(UserStatus status) {
        return users.values().stream().filter(user -> user.getStatus() == status).toList();
    }
}

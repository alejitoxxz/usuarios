package com.co.eatupapi.repositories.user.user;

import com.co.eatupapi.domain.user.user.BranchMockData;
import com.co.eatupapi.domain.user.user.User;
import com.co.eatupapi.domain.user.user.UserStatus;
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

    public InMemoryUserRepository() {
        User seedUser = new User();
        seedUser.setId(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"));
        seedUser.setFirstName("Juan");
        seedUser.setLastName("Perez");
        seedUser.setDocumentTypeId(BranchMockData.DOCUMENT_TYPE_CC_ID);
        seedUser.setDocumentNumber("1234567890");
        seedUser.setPhone("3004567890");
        seedUser.setEmail("juan.perez@correo.com");
        seedUser.setBirthDate(LocalDate.of(1998, 5, 14));
        seedUser.setDepartmentId(BranchMockData.DEPARTMENT_ANTIOQUIA_ID);
        seedUser.setCityId(BranchMockData.CITY_MEDELLIN_ID);
        seedUser.setAddress("Cra 33A #32-12 Barrio El Poblado");
        seedUser.setBranchId(BranchMockData.BRANCH_MEDELLIN_CENTRO_ID);
        seedUser.setStatus(UserStatus.ACTIVE);
        users.put(seedUser.getId(), seedUser);
    }

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
    public List<User> findAll() {
        return users.values().stream().toList();
    }

    @Override
    public List<User> findByStatus(UserStatus status) {
        return users.values().stream()
                .filter(user -> user.getStatus() == status)
                .toList();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }
}

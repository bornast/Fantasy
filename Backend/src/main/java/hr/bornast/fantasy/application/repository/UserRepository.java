package hr.bornast.fantasy.application.repository;

import hr.bornast.fantasy.infrastructure.persistence.entity.UserEntity;

public interface UserRepository {
    void create(UserEntity user);
    UserEntity findByUsername(String username);
}

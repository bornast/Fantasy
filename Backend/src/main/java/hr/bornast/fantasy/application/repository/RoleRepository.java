package hr.bornast.fantasy.application.repository;

import hr.bornast.fantasy.infrastructure.persistence.entity.RoleEntity;

public interface RoleRepository {
    RoleEntity create(RoleEntity role);
    RoleEntity findById(int id);
    RoleEntity findByName(String name);
}

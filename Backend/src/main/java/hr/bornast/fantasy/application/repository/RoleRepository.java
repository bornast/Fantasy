package hr.bornast.fantasy.application.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.domain.model.Role;

public interface RoleRepository {
    List<Role> findAll();
    Optional<Role> findById(int id);
    Optional<Role> findByName(String name);
    Role create(Role role);
    Role update(Role role);
    void delete(int id);

}

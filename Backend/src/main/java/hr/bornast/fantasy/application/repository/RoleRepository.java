package hr.bornast.fantasy.application.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.domain.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleRepository {
    Page<Role> findAll(Pageable paging);
    Page<Role> findByName(String name, Pageable paging);
    Optional<Role> findById(int id);
    Optional<Role> findByName(String name);
    List<Role> findByIds(List<Integer> ids);
    Role create(Role role);
    Role update(Role role);
    void delete(int id);

}

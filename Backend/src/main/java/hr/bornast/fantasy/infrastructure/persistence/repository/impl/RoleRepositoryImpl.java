package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.application.repository.RoleRepository;
import hr.bornast.fantasy.domain.model.Role;
import hr.bornast.fantasy.infrastructure.persistence.entity.RoleEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.RoleEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

    private final RoleEntityRepository roleRepository;
    private final ModelMapper mapper;

    @Override
    public Page<Role> findAll(Pageable paging) {
        return roleRepository.findAll(paging).map(x -> mapper.map(x, Role.class));
    }

    @Override
    public Page<Role> findByName(String name, Pageable pageable) {
        return roleRepository.findByNameContainingIgnoreCase(name, pageable)
            .map(x -> mapper.map(x, Role.class));
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll().stream().map(x -> mapper.map(x, Role.class)).toList();
    }

    @Override
    public Optional<Role> findById(int id) {
        return roleRepository.findById(id).map(x -> mapper.map(x, Role.class));
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name)
            .map(x -> mapper.map(x, Role.class));
    }

    @Override
    public List<Role> findByIds(List<Integer> ids) {
        return roleRepository.findByIdIn(ids).stream().map(x -> mapper.map(x, Role.class)).toList();
    }

    @Override
    public Role create(Role role) {
        return mapper.map(roleRepository.save(mapper.map(role, RoleEntity.class)), Role.class);
    }

    @Override
    public void delete(int id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role update(Role role) {
        return mapper.map(roleRepository.save(mapper.map(role, RoleEntity.class)), Role.class);
    }

}

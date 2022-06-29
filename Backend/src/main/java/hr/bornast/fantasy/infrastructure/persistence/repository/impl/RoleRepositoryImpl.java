package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import hr.bornast.fantasy.application.repository.RoleRepository;
import hr.bornast.fantasy.infrastructure.persistence.entity.RoleEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.RoleEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

    private final RoleEntityRepository roleRepository;
    private final ModelMapper mapper;

    @Override
    public RoleEntity create(RoleEntity role) {
        roleRepository.findByName(role.getName())
            .ifPresent(r -> {throw new RuntimeException("role " + role.getName() + " already exists");});

        return roleRepository.save(role);
    }

    @Override
    public RoleEntity findById(int id) {
        // TODO: use custom exception
        return roleRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public RoleEntity findByName(String name) {
        // TODO: use custom exception
        return roleRepository.findByName(name).orElseThrow(RuntimeException::new);
    }
}

package hr.bornast.fantasy.application.service.impl;

import hr.bornast.fantasy.application.command.role.CreateRoleCommand;
import hr.bornast.fantasy.application.repository.RoleRepository;
import hr.bornast.fantasy.application.service.RoleService;
import hr.bornast.fantasy.infrastructure.persistence.entity.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper mapper;

    public void create(CreateRoleCommand command) {
        roleRepository.create(mapper.map(command, RoleEntity.class));
    }

}

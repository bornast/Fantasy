package hr.bornast.fantasy.application.service.impl;

import java.util.List;

import hr.bornast.fantasy.application.command.role.CreateRoleCommand;
import hr.bornast.fantasy.application.command.role.UpdateRoleCommand;
import hr.bornast.fantasy.application.dto.role.RoleDto;
import hr.bornast.fantasy.application.repository.RoleRepository;
import hr.bornast.fantasy.application.service.RoleService;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.common.exception.ValidationException;
import hr.bornast.fantasy.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper mapper;

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream().map(x -> mapper.map(x, RoleDto.class)).toList();
    }

    @Override
    public RoleDto findOne(int id) {
        return roleRepository.findById(id)
            .map(x -> mapper.map(x, RoleDto.class))
            .orElseThrow(EntityNotFoundException::new);
    }

    public RoleDto create(CreateRoleCommand command) {
        roleRepository.findByName(command.getName())
            .ifPresent(r -> {throw new ValidationException("Role", "role " + command.getName() + " already exists");});

        return mapper.map(roleRepository.create(mapper.map(command, Role.class)), RoleDto.class);
    }

    @Override
    public RoleDto update(int id, UpdateRoleCommand command) {
        var role = roleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        mapper.map(command, role);
        return mapper.map(roleRepository.update(role), RoleDto.class);
    }

    @Override
    public void delete(int id) {
        roleRepository.delete(id);
    }

}

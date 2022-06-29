package hr.bornast.fantasy.application.service.impl;

import java.util.List;

import hr.bornast.fantasy.application.command.role.CreateRoleCommand;
import hr.bornast.fantasy.application.command.role.UpdateRoleCommand;
import hr.bornast.fantasy.application.dto.role.RoleDto;
import hr.bornast.fantasy.application.repository.RoleRepository;
import hr.bornast.fantasy.application.service.RoleService;
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
        // TODO: custom exception
        return roleRepository.findById(id)
            .map(x -> mapper.map(x, RoleDto.class))
            .orElseThrow(RuntimeException::new);
    }

    public void create(CreateRoleCommand command) {
        roleRepository.findByName(command.getName())
            .ifPresent(r -> {throw new RuntimeException("role " + command.getName() + " already exists");});

        roleRepository.create(mapper.map(command, Role.class));
    }

    @Override
    public RoleDto update(int id, UpdateRoleCommand command) {
        var role = roleRepository.findById(id).orElseThrow(RuntimeException::new);
        mapper.map(command, role);
        return mapper.map(roleRepository.update(role), RoleDto.class);
    }

    @Override
    public void delete(int id) {
        roleRepository.delete(id);
    }

}

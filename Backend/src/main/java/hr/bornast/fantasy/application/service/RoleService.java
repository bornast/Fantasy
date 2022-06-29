package hr.bornast.fantasy.application.service;

import java.util.List;

import hr.bornast.fantasy.application.command.role.CreateRoleCommand;
import hr.bornast.fantasy.application.command.role.UpdateRoleCommand;
import hr.bornast.fantasy.application.dto.role.RoleDto;

public interface RoleService {
    List<RoleDto> findAll();
    RoleDto findOne(int id);
    RoleDto create(CreateRoleCommand role);
    RoleDto update(int id, UpdateRoleCommand command);
    void delete(int id);
}

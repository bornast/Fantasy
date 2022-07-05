package hr.bornast.fantasy.application.service;

import hr.bornast.fantasy.application.command.role.CreateRoleCommand;
import hr.bornast.fantasy.application.command.role.UpdateRoleCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.role.RoleDto;
import org.springframework.data.domain.Pageable;

public interface RoleService {
    PagedListDto<RoleDto> findAll(Pageable pageable, String name);
    RoleDto findOne(int id);
    RoleDto create(CreateRoleCommand role);
    RoleDto update(int id, UpdateRoleCommand command);
    void delete(int id);
}

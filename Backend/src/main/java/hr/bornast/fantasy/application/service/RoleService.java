package hr.bornast.fantasy.application.service;

import java.util.List;

import hr.bornast.fantasy.application.command.role.CreateRoleCommand;
import hr.bornast.fantasy.application.command.role.UpdateRoleCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.role.RoleDto;
import org.springframework.data.domain.Pageable;

public interface RoleService {
    PagedListDto<RoleDto> findAll(Pageable pageable, String name);
    List<RecordNameDto> findAllRecordNames();
    RoleDto findOne(int id);
    RoleDto create(CreateRoleCommand role);
    RoleDto update(int id, UpdateRoleCommand command);
    void delete(int id);
}

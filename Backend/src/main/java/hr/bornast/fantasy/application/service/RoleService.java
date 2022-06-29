package hr.bornast.fantasy.application.service;

import hr.bornast.fantasy.application.command.role.CreateRoleCommand;

public interface RoleService {
    void create(CreateRoleCommand role);
}

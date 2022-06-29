package hr.bornast.fantasy.application.service;

import hr.bornast.fantasy.application.command.auth.RegisterCommand;

public interface UserService {
    void create(RegisterCommand command);
    void initRolesAndUser();
}

package hr.bornast.fantasy.application.service;

import java.util.List;

import hr.bornast.fantasy.application.command.auth.RegisterCommand;
import hr.bornast.fantasy.application.command.user.UpdateUserCommand;
import hr.bornast.fantasy.application.dto.user.UserDto;

public interface UserService {
    List<UserDto> findAll();
    UserDto findOne(int id);
    UserDto create(RegisterCommand command);
    UserDto update(int id, UpdateUserCommand command);
    void delete(int id);
    void initRolesAndUser();
}

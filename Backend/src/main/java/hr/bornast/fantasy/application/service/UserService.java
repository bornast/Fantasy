package hr.bornast.fantasy.application.service;

import hr.bornast.fantasy.application.command.auth.RegisterCommand;
import hr.bornast.fantasy.application.command.user.UpdateUserCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.user.UserDto;
import org.springframework.data.domain.Pageable;

public interface UserService {
    PagedListDto<UserDto> findAll(Pageable pageable, String username);
    UserDto findOne(int id);
    UserDto create(RegisterCommand command);
    UserDto update(int id, UpdateUserCommand command);
    void delete(int id);
    void initRolesAndUser();
}

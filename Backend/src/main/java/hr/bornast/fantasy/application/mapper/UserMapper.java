package hr.bornast.fantasy.application.mapper;

import hr.bornast.fantasy.application.command.auth.RegisterCommand;
import hr.bornast.fantasy.application.command.user.UpdateUserCommand;
import hr.bornast.fantasy.application.dto.user.UserDto;
import hr.bornast.fantasy.domain.model.User;

public interface UserMapper {
    UserDto map(User user);
    void map(UpdateUserCommand command, User user);
    User map(RegisterCommand command);
}

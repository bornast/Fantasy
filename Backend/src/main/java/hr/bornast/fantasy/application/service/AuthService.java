package hr.bornast.fantasy.application.service;

import hr.bornast.fantasy.application.command.auth.LoginCommand;
import hr.bornast.fantasy.application.dto.auth.TokenDto;

public interface AuthService {
    TokenDto login(LoginCommand command);
}

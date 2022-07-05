package hr.bornast.fantasy.api.controller;

import javax.validation.Valid;

import hr.bornast.fantasy.application.command.auth.LoginCommand;
import hr.bornast.fantasy.application.command.auth.RegisterCommand;
import hr.bornast.fantasy.application.dto.auth.TokenDto;
import hr.bornast.fantasy.application.service.AuthService;
import hr.bornast.fantasy.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping({"/login"})
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginCommand command) {
        return ok(authService.login(command));
    }

    @PostMapping({"/register"})
    public void register(@Valid @RequestBody RegisterCommand command) {
        userService.create(command);
    }

}

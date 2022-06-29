package hr.bornast.fantasy.application.command.auth;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginCommand {
    @NotNull
    private String username;
    @NotNull
    private String password;
}

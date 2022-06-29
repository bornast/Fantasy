package hr.bornast.fantasy.application.command.auth;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RegisterCommand {
    @NotNull
    private String username;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String password;
}

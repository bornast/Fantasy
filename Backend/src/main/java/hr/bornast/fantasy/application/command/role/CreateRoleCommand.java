package hr.bornast.fantasy.application.command.role;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateRoleCommand {
    @NotNull
    private String name;
}

package hr.bornast.fantasy.application.command.formation;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateFormationCommand {
    @NotNull
    private String name;
}

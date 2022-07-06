package hr.bornast.fantasy.application.command.position;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreatePositionCommand {
    @NotNull
    private String name;
}

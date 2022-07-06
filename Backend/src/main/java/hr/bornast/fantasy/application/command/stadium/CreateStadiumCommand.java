package hr.bornast.fantasy.application.command.stadium;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateStadiumCommand {
    @NotNull
    private String name;
}

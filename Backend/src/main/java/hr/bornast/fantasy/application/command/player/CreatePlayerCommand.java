package hr.bornast.fantasy.application.command.player;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreatePlayerCommand {
    @NotEmpty
    private String name;
    @NotNull
    private Date dateOfBirth;
    @NotNull
    private int positionId;
}

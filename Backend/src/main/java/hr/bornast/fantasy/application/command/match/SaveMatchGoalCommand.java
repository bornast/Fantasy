package hr.bornast.fantasy.application.command.match;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SaveMatchGoalCommand {
    @NotNull
    private int playerId;

    @NotNull
    private int minute;
}

package hr.bornast.fantasy.application.command.match;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SaveMatchSubstitutionsCommand {
    @NotNull
    private int lineupPlayerId;
    @NotNull
    private int substitutePlayerId;
    @NotNull
    private int minute;
}

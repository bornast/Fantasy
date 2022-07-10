package hr.bornast.fantasy.application.command.match;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SaveMatchCardCommand {
    @NotNull
    private int playerId;

    @NotNull
    private int cardId;

    @NotNull
    private int minute;
}

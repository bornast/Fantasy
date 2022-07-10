package hr.bornast.fantasy.application.command.match;

import java.time.OffsetDateTime;
import java.util.Set;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SaveMatchCommand {
    @NotNull
    private SaveMatchTeamCommand homeTeam;

    @NotNull
    private SaveMatchTeamCommand awayTeam;

    private Set<SaveMatchGoalCommand> goals;

    private Set<SaveMatchCardCommand> cards;

    @NotNull
    private int leagueId;

    @NotNull
    private OffsetDateTime matchDate;

    @NotNull
    private int refereeId;

    @NotNull
    private int stadiumId;

    @NotNull
    private int spectatorCount;

}

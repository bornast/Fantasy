package hr.bornast.fantasy.application.command.match;

import java.util.Set;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SaveMatchTeamCommand {
    @NotNull
    private int teamId;
    @NotNull
    private int formationId;
    @NotNull
    private Set<Integer> lineupPlayerIds;
    @NotNull
    private Set<Integer> substitutePlayerIds;
    private Set<SaveMatchSubstitutionsCommand> substitutions;
}

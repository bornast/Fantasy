package hr.bornast.fantasy.application.command.league;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SaveLeagueCommand {
    @NotEmpty
    private String name;
    @NotNull
    private int seasonId;
    @NotNull
    private Set<Integer> teamIds;
}

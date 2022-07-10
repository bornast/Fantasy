package hr.bornast.fantasy.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MatchTeam {
    private Team team;
    private Formation formation;
    private List<Player> lineupPlayers = new ArrayList<>();
    private List<Player> substitutePlayers = new ArrayList<>();
    private List<MatchSubstitution> substitutions = new ArrayList<>();

    public void addLineupPlayer(Player player) {
        lineupPlayers.add(player);
    }

    public void addSubstitutePlayer(Player player) {
        substitutePlayers.add(player);
    }

    public void addSubstitutions(MatchSubstitution substitution) {
        substitutions.add(substitution);
    }
}

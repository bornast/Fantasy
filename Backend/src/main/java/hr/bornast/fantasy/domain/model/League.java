package hr.bornast.fantasy.domain.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class League {
    private int id;
    private String name;
    private Season season;
    private Set<Team> teams = new HashSet<>();

    public void addTeam(Team team) {
        teams.add(team);
    }
}

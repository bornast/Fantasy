package hr.bornast.fantasy.domain.model;

import java.util.Set;

import lombok.Data;

@Data
public class Match {
    private int id;
    private Team homeTeam;
    private Formation homeTeamFormation;
    private Set<Player> homeTeamLineupPlayers;
    private Set<Player> homeTeamSubstitutePlayers;
//    private String name;
//    private Date dateOfEstablishment;
//    private President president;
//    private Coach coach;
//    private Stadium stadium;
}

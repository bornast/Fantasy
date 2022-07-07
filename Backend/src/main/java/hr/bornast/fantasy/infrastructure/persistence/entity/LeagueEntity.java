package hr.bornast.fantasy.infrastructure.persistence.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "leagues")
@Data
public class LeagueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", name = "season_id")
    private SeasonEntity season;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "league_teams",
               joinColumns = { @JoinColumn(name = "league_id") },
               inverseJoinColumns = { @JoinColumn(name = "team_id") })
    private Set<TeamEntity> teams;

}

package hr.bornast.fantasy.infrastructure.persistence.entity;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "matches")
@Data
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "match",
               cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<MatchTeamEntity> teams = new HashSet<>();

    public void addMatchTeam(MatchTeamEntity matchTeam) {
        teams.add(matchTeam);
        matchTeam.setMatch(this);
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "match",
               cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<MatchPlayerEntity> players = new HashSet<>();

    public void addMatchPlayer(MatchPlayerEntity matchPlayer) {
        players.add(matchPlayer);
        matchPlayer.setMatch(this);
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "match",
               cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<MatchSubstitutionEntity> substitutions = new HashSet<>();

    public void addMatchSubstitution(MatchSubstitutionEntity matchSubstitution) {
        substitutions.add(matchSubstitution);
        matchSubstitution.setMatch(this);
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "match",
               cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<MatchCardEntity> cards = new HashSet<>();

    public void addMatchCard(MatchCardEntity matchCard) {
        cards.add(matchCard);
        matchCard.setMatch(this);
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "match",
               cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<MatchGoalEntity> goals = new HashSet<>();

    public void addMatchGoal(MatchGoalEntity matchGoal) {
        goals.add(matchGoal);
        matchGoal.setMatch(this);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", name = "league_id")
    private LeagueEntity league;

    private OffsetDateTime matchDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", name = "referee_id")
    private RefereeEntity referee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", name = "stadium_id")
    private StadiumEntity stadium;

    private int spectatorCount;

}

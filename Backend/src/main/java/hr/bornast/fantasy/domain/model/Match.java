package hr.bornast.fantasy.domain.model;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Match {
    private int id;
    private MatchTeam homeTeam;
    private MatchTeam awayTeam;
    private Set<MatchGoal> goals = new HashSet<>();
    private Set<MatchCard> cards = new HashSet<>();
    private League league;
    private OffsetDateTime matchDate;
    private Referee referee;
    private Stadium stadium;
    private int spectatorCount;

    public void addGoal(MatchGoal goal) {
        goals.add(goal);
    }

    public void addCard(MatchCard card) {
        cards.add(card);
    }
}

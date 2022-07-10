package hr.bornast.fantasy.domain.model;

import lombok.Data;

@Data
public class MatchSubstitution {
    private Player lineupPlayer;
    private Player substitutePlayer;
    private int minute;
}

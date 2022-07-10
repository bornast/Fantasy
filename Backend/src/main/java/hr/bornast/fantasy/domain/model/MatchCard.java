package hr.bornast.fantasy.domain.model;

import lombok.Data;

@Data
public class MatchCard {
    private Player player;
    private Card card;
    private int minute;
}

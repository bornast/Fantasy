package hr.bornast.fantasy.domain.model;

import lombok.Data;

@Data
public class MatchPlayerRate {
    private int id;
    private RecordName match;
    private Player player;
    private User user;
    private int rate;
}

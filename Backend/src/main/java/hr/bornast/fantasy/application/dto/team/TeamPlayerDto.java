package hr.bornast.fantasy.application.dto.team;

import lombok.Data;

@Data
public class TeamPlayerDto {
    private int id;
    private String name;
    private String image;
    private int matchesPlayed;
    private int goals;
    private int yellowCards;
    private int redCards;
    private double rate;
}

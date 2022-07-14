package hr.bornast.fantasy.application.dto.team;

import lombok.Data;

@Data
public class TeamTableDto {
    private int sequence;
    private String teamName;
    private int gameCount;
    private int winCount;
    private int drawCount;
    private int loseCount;
    private String goalRatio;
    private int points;
}

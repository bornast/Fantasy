package hr.bornast.fantasy.application.dto.team;

import lombok.Data;

@Data
public class TeamResultDto {
    private String matchDate;
    private String homeTeamName;
    private String homeTeamImage;
    private String awayTeamName;
    private String awayTeamImage;
    private String result;
    private String stadium;
    private String league;
    private String season;
}

package hr.bornast.fantasy.application.dto.team;

import lombok.Data;

@Data
public class TeamTransferDto {
    private int playerId;
    private String transferDate;
    private String playerName;
    private String playerImage;
    private String fromTeamName;
    private String fromTeamImage;
    private String toTeamName;
    private String toTeamImage;
    private double rate;
}

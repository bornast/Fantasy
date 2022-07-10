package hr.bornast.fantasy.application.dto.match;

import java.util.Set;

import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import lombok.Data;

@Data
public class MatchDto {
    private int id;
    private MatchTeamDto homeTeam;
    private MatchTeamDto awayTeam;
    private Set<MatchGoalDto> goals;
    private Set<MatchCardDto> cards;
    private RecordNameDto league;
    private String matchDate;
    private RecordNameDto referee;
    private RecordNameDto stadium;
    private int spectatorCount;

}

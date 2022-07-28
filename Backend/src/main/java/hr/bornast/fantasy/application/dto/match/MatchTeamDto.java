package hr.bornast.fantasy.application.dto.match;

import java.util.Set;

import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import lombok.Data;

@Data
public class MatchTeamDto {
    private RecordNameDto team;
    private RecordNameDto formation;
    private Set<MatchPlayerDto> lineupPlayers;
    private Set<MatchPlayerDto> substitutePlayers;
    private Set<MatchSubstitutionDto> substitutions;
    private RecordNameDto coach;
}

package hr.bornast.fantasy.application.dto.match;

import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import lombok.Data;

@Data
public class MatchSubstitutionDto {
    private RecordNameDto lineupPlayer;
    private RecordNameDto substitutePlayer;
    private int minute;
}

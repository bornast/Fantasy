package hr.bornast.fantasy.application.dto.match;

import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import lombok.Data;

@Data
public class MatchGoalDto {
    private RecordNameDto player;
    private int minute;
}

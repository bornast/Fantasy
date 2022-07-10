package hr.bornast.fantasy.application.dto.match;

import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import lombok.Data;

@Data
public class MatchCardDto {
    private RecordNameDto player;
    private RecordNameDto card;
    private int minute;
}

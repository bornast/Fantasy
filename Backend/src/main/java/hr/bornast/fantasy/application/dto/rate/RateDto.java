package hr.bornast.fantasy.application.dto.rate;

import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import lombok.Data;

@Data
public class RateDto {
    private int id;
    private RecordNameDto match;
    private RecordNameDto player;
    private int rate;
}

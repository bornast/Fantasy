package hr.bornast.fantasy.application.dto.league;

import java.util.Set;

import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import lombok.Data;

@Data
public class LeagueDto {
    private int id;
    private String name;
    private RecordNameDto season;
    private Set<RecordNameDto> teams;
}

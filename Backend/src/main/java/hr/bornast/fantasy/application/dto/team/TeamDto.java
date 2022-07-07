package hr.bornast.fantasy.application.dto.team;

import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.media.EntityMediaDto;
import lombok.Data;

@Data
public class TeamDto {
    private int id;
    private String name;
    private String dateOfEstablishment;
    private RecordNameDto president;
    private RecordNameDto coach;
    private RecordNameDto stadium;
    private EntityMediaDto media;
}

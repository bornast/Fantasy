package hr.bornast.fantasy.application.dto.player;

import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.media.EntityMediaDto;
import lombok.Data;

@Data
public class PlayerDto {
    private int id;
    private String name;
    private String dateOfBirth;
    private RecordNameDto position;
    private EntityMediaDto media;
}

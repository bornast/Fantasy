package hr.bornast.fantasy.application.dto.transfer;

import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import lombok.Data;

@Data
public class TransferDto {
    private int id;
    private RecordNameDto player;
    private RecordNameDto fromTeam;
    private RecordNameDto toTeam;
    private String transferDate;
}

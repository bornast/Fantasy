package hr.bornast.fantasy.application.dto.chat;

import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import lombok.Data;

@Data
public class MessageDto {
    private RecordNameDto user;
    private String content;
    private String time;
}

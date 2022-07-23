package hr.bornast.fantasy.application.dto.chat;

import java.util.List;

import lombok.Data;

@Data
public class ChatDto {
    private int id;
    private String name;
    private List<MessageDto> messages;
}

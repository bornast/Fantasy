package hr.bornast.fantasy.application.command.chat;

import lombok.Data;

@Data
public class CreateChatCommand {
    private String name;
    private int teamId;
}

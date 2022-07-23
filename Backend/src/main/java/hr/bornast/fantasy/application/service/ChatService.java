package hr.bornast.fantasy.application.service;

import hr.bornast.fantasy.application.command.chat.CreateChatCommand;
import hr.bornast.fantasy.application.dto.chat.ChatDto;
import hr.bornast.fantasy.application.dto.chat.MessageDto;

public interface ChatService {
    ChatDto findOne(int teamId);
    ChatDto create(CreateChatCommand command);
    MessageDto addMessage(int teamId, String msg, String token);
}

package hr.bornast.fantasy.application.service.impl;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

import hr.bornast.fantasy.application.command.chat.CreateChatCommand;
import hr.bornast.fantasy.application.dto.chat.ChatDto;
import hr.bornast.fantasy.application.dto.chat.MessageDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.repository.ChatRepository;
import hr.bornast.fantasy.application.repository.MessageRepository;
import hr.bornast.fantasy.application.repository.TeamRepository;
import hr.bornast.fantasy.application.repository.UserRepository;
import hr.bornast.fantasy.application.service.ChatService;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.common.util.JwtUtil;
import hr.bornast.fantasy.domain.model.Chat;
import hr.bornast.fantasy.domain.model.Message;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final ModelMapper mapper;

    @Override
    public ChatDto findOne(int teamId) {
        var chat = chatRepository.findByTeamId(teamId).orElseThrow(EntityNotFoundException::new);
        chat.getMessages().sort(Comparator.comparing(Message::getTime));
        var result = new ChatDto();
        result.setId(chat.getId());
        result.setName(chat.getName());
        result.setMessages(new ArrayList<>());
        for (var message : chat.getMessages()) {
            var msg = new MessageDto();
            msg.setUser(new RecordNameDto(message.getUser().getId(), message.getUser().getUsername()));
            msg.setContent(message.getContent());
            msg.setTime(message.getTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
            result.getMessages().add(msg);
        }
        return result;
    }

    @Override
    public ChatDto create(CreateChatCommand command) {
        var team = teamRepository.findById(command.getTeamId())
            .orElseThrow(EntityNotFoundException::new);
        var chat = new Chat();
        chat.setName(team.getName());
        chat.setTeam(team);
        return mapper.map(chatRepository.create(chat), ChatDto.class);
    }

    @Override
    public MessageDto addMessage(int teamId, String msg, String token) {
        var chat = chatRepository.findByTeamId(teamId)
            .orElseThrow(RuntimeException::new);

        var username = jwtUtil.getUserNameFromToken(token);
        if (username == null) {
            throw new InternalAuthenticationServiceException("Unauthorized");
        }

        var user = userRepository.findByUsername(username)
            .orElseThrow(() -> new InternalAuthenticationServiceException("Unauthorized"));

        var message = new Message();
        var msgTime = OffsetDateTime.now();
        message.setChat(chat);
        message.setContent(msg);
        message.setUser(user);
        message.setTime(msgTime);

        var createdMessage = messageRepository.create(message);

        var result = new MessageDto();
        result.setUser(new RecordNameDto(user.getId(), user.getUsername()));
        result.setContent(msg);
        result.setTime(msgTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        return result;
    }
}

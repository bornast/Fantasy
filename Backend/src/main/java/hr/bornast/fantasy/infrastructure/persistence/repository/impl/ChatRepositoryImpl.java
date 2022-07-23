package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.Optional;

import hr.bornast.fantasy.application.repository.ChatRepository;
import hr.bornast.fantasy.domain.model.Chat;
import hr.bornast.fantasy.infrastructure.persistence.entity.ChatEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.ChatEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatRepositoryImpl implements ChatRepository {

    private final ChatEntityRepository chatRepository;
    private final ModelMapper mapper;

    @Override
    public Optional<Chat> findByTeamId(int teamId) {
        return chatRepository.findByTeamId(teamId).map(x -> mapper.map(x, Chat.class));
    }

    @Override
    public Chat create(Chat chat) {
        return mapper.map(chatRepository.save(mapper.map(chat, ChatEntity.class)), Chat.class);
    }

}

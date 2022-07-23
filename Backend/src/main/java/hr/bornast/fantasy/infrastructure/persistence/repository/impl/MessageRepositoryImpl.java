package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import hr.bornast.fantasy.application.repository.MessageRepository;
import hr.bornast.fantasy.domain.model.Message;
import hr.bornast.fantasy.infrastructure.persistence.entity.MessageEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.MessageEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepository {

    private final MessageEntityRepository messageRepository;
    private final ModelMapper mapper;

    @Override
    public Message create(Message message) {
        return mapper.map(messageRepository.saveAndFlush(mapper.map(message, MessageEntity.class)), Message.class);
    }

}

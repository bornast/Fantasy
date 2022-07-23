package hr.bornast.fantasy.application.repository;

import hr.bornast.fantasy.domain.model.Message;

public interface MessageRepository {
    Message create(Message message);
}

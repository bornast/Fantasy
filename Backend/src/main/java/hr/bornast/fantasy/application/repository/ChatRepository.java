package hr.bornast.fantasy.application.repository;

import java.util.Optional;

import hr.bornast.fantasy.domain.model.Chat;

public interface ChatRepository {
    Optional<Chat> findByTeamId(int teamId);
    Chat create(Chat chat);
}

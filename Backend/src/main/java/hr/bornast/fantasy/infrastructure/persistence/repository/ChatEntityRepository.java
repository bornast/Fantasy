package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.Optional;

import hr.bornast.fantasy.infrastructure.persistence.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatEntityRepository extends JpaRepository<ChatEntity, Integer> {
    Optional<ChatEntity> findByTeamId(int teamId);
}

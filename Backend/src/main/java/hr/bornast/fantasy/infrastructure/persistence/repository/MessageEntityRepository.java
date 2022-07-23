package hr.bornast.fantasy.infrastructure.persistence.repository;

import hr.bornast.fantasy.infrastructure.persistence.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageEntityRepository extends JpaRepository<MessageEntity, Integer> {
}

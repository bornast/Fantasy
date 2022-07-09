package hr.bornast.fantasy.infrastructure.persistence.repository;

import hr.bornast.fantasy.infrastructure.persistence.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardEntityRepository extends JpaRepository<CardEntity, Integer> {
}
